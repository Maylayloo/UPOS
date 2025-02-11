'use client'

import React, {useEffect, useState} from 'react';
import {fetchMajors} from "@/services/api/major";
import Loading from "@/components/layout/Loading";
import CustomSelect from "@/components/sections/admin-courses/CustomSelect";
import {fetchProfessors} from "@/services/api/professor";
import CourseInput from "@/components/sections/admin-courses/CourseInput";
import {fetchStudentsByMajorSemester} from "@/services/api/student";
import {fetchCoursesByMajorSemester} from "@/services/api/course";
import StudentCheckBox from "@/components/sections/admin-groups/StudentCheckBox";
import {postGroup} from "@/services/api/group";
import {useRouter} from "next/navigation";

const Page = () => {
    const semesters = ["1", "2", "3", "4", "5", "6", "7"];
    const groupTypes = ["wykład", "ćwiczenia audytoryjne", "ćwiczenia laboratoryjne", "ćwiczenia projektowe"]
    const days = [
        {day: "poniedziałek", value: "MONDAY"},
        {day: "wtorek", value: "TUESDAY"},
        {day: "środa", value: "WEDNESDAY"},
        {day: "czwartek", value: "THURSDAY"},
        {day: "piątek", value: "FRIDAY"}]
    const groupNumbers = ["1", "2", "3", "4", "5", "6", "7", "8", "9"];

    const [loading, setLoading] = useState(false);
    const [majors, setMajors] = useState([]);
    const [step, setStep] = useState(1);
    const [selectedMajor, setSelectedMajor] = useState("");
    const [selectedSemester, setSelectedSemester] = useState(semesters[0]);
    const [selectedCourse, setSelectedCourse] = useState("")
    const [courses, setCourses] = useState([]);
    const [selectedGroupType, setSelectedGroupType] = useState(groupTypes[0]);
    const [selectedGroupNumber, setSelectedGroupNumber] = useState(groupNumbers[0])
    const [professors, setProfessors] = useState([])
    const [selectedProfId, setSelectedProfId] = useState("");
    const [selectedDay, setSelectedDay] = useState(days[0].value)
    const [students, setStudents] = useState([])
    const [selectedStartHour, setSelectedStartHour] = useState("")
    const [selectedEndHour, setSelectedEndHour] = useState("")
    const [place, setPlace] = useState("")
    const [maxGroupSize, setMaxGroupSize] = useState(30)
    const [studentsIds, setStudentsIds] = useState<number[]>([]);
    const router = useRouter()

    interface Course {
        courseId: number,
        name: string,
        ects: number,
        professorId: number,
        semester: string,
        major: string,
        exams: string[],
        studentsIds: number[],
    }

    interface Professor {
        professorId: number,
        title: string,
        name: string,
        surname: string,
    }

    interface Day {
        day: string,
        value: string,
    }

    interface Student {
        studentId: number,
        name: string,
        surname: string,
    }

    const handleCheck = (studentId: number, isChecked: boolean) => {
        setStudentsIds((prev) =>
            isChecked ? [...prev, studentId] : prev.filter((id) => id !== studentId)
        );
    };

    useEffect(() => {
        const fetchData = async () => {
            const [fetchedMajors, fetchedProfessors] = await Promise.all([
                fetchMajors(),
                fetchProfessors()
            ])

            if (fetchedMajors && fetchedProfessors) {
                setMajors(fetchedMajors)
                setProfessors(fetchedProfessors)

                // set default values
                setSelectedMajor(fetchedMajors[0])
                setSelectedProfId(fetchedProfessors[0].professorId)

            }

            setLoading(false)
        }
        fetchData()
    }, [])
    const nextStep = () => {
        if (step === 1) firstSubmit();
        if (step === 2) secondSubmit();
        if (step === 3) thirdSubmit();
        if (step === 4) fourthSubmit()
    }
    const previousStep = () => {
        setStep(step - 1)
    }

    const firstSubmit = async () => {
            const fetchedStudents = await fetchStudentsByMajorSemester(Number(selectedSemester), selectedMajor);
            const fetchedCourses = await fetchCoursesByMajorSemester(selectedMajor, selectedSemester);

            if (fetchedStudents && fetchedCourses) {
                setStudents(fetchedStudents)
                setCourses(fetchedCourses)
                setSelectedCourse(fetchedCourses[0].courseId)
            }

        setStep(step + 1)

    }
    const secondSubmit = () => {
        setStep(step + 1)
    }
    const thirdSubmit = () => {
        setStep(step + 1)
    }
    const fourthSubmit = async () => {
        const postedGroup = await postGroup(
            Number(selectedCourse), selectedGroupType, Number(selectedGroupNumber),
            selectedDay, selectedStartHour, selectedEndHour, place, Number(maxGroupSize), Number(selectedProfId), studentsIds
        )

        if (postedGroup) {
            router.push('/dashboard')
        } else {
            alert("Coś poszło nie tak")
        }

    }

    if (loading) {
        return <Loading/>
    }

    return (
        <div className='flex flex-col items-center -mt-4 border p-6 border-gray-400 rounded-2xl'>
            <div>
                <h1 className='text-center text-2xl mb-6 font-roboto '>
                    Krok {step}/4
                </h1>
                {
                    step === 1 && (
                    <div className='flex flex-col items-center gap-4'>
                        <h2 className='text-lg font-roboto'>
                            Wybierz kierunek i semestr:
                        </h2>
                        <div className='w-full flex justify-center gap-8 mb-12'>
                            <CustomSelect
                                arr={majors}
                                additionalText=", 1. stopień"
                                width="30%"
                                onChange={(e) => setSelectedMajor(e.target.value)}
                            />
                            <CustomSelect
                                arr={semesters}
                                width="10%"
                                onChange={(e) => setSelectedSemester(e.target.value)}
                            />
                        </div>
                    </div>
                )}

                {
                    step === 2 && (
                        <div className='flex flex-col items-center gap-4'>
                            <div>
                                <h2 className='text-lg text-center font-roboto mb-1'>
                                    Wybierz przedmiot:
                                </h2>
                                <select
                                    className='text-black py-1 px-3 focus:outline-none w-full'
                                    onChange={(e) => setSelectedCourse(e.target.value)}
                                >
                                    {
                                        courses.map((course: Course) => (
                                            <option
                                                className='font-roboto'
                                                key={course.courseId}
                                                value={course.courseId}
                                            >
                                                {course.name}
                                            </option>
                                        ))
                                    }
                                </select>
                            </div>
                            <div>
                                <h2 className='text-lg text-center mb-1 font-roboto'>
                                    Wybierz typ i numer grupy:
                                </h2>
                                <div className='w-full flex justify-center gap-2'>
                                    <CustomSelect
                                        arr={groupTypes}
                                        width="30%"
                                        onChange={(e) => setSelectedGroupType(e.target.value)}
                                    />
                                    <CustomSelect
                                        arr={groupNumbers}
                                        width="30%"
                                        onChange={(e) => setSelectedGroupNumber(e.target.value)}
                                    />
                                </div>

                            </div>
                            <div className='mb-8'>
                                <h2 className='text-lg font-roboto'>
                                    Wybierz prowadzącego grupy:
                                </h2>
                                <select
                                    className='text-black w-[40%] py-1 px-3 focus:outline-none w-full'
                                    onChange={(e) => setSelectedProfId(e.target.value)}
                                >
                                    {
                                        professors.map((prof: Professor) => (
                                            <option
                                                className='font-roboto'
                                                key={prof.professorId}
                                                value={Number(prof.professorId)}
                                            >
                                                {prof.title} {prof.name} {prof.surname}
                                            </option>
                                        ))
                                    }
                                </select>
                            </div>
                        </div>
                    )
                }
                {
                    step === 3 && (
                        <div className='flex flex-col items-center gap-4'>
                            <div>
                                <h2 className='text-lg font-roboto mb-1'>
                                    Wybierz dzień tygodnia:
                                </h2>
                                <select
                                    className='text-black w-[40%] py-1 px-3 focus:outline-none w-full'
                                    onChange={(e) => setSelectedDay(e.target.value)}
                                >
                                    {
                                        days.map((day: Day) => (
                                            <option
                                                className='font-roboto'
                                                key={day.day}
                                                value={day.value}
                                            >
                                                {day.day}
                                            </option>
                                        ))
                                    }
                                </select>
                            </div>
                            <div className='flex flex-col items-center gap-1'>
                                <h2 className='text-lg font-roboto'>
                                    Wybierz godzinę rozpoczęcia i zakończenia zajęć:
                                </h2>
                                <div className='flex gap-8'>
                                    <input
                                        className='font-roboto text-black focus:outline-none py-1 px-4'
                                        type="time"
                                        onChange={(e) => setSelectedStartHour(e.target.value)}
                                    />
                                    <input
                                        className='font-roboto text-black focus:outline-none py-1 px-4'
                                        type="time"
                                        onChange={(e) => setSelectedEndHour(e.target.value)}
                                    />
                                </div>
                            </div>
                            <div className='mb-8'>
                                <CourseInput
                                    title='Wprowadź miejsce odbywania się zajęć:'
                                    placeholder='s.200, bud. D6'
                                    width="100%"
                                    name="place"
                                    onChange={(e) => setPlace(e.target.value)}
                                />
                            </div>
                        </div>
                    )
                }
                {
                    step === 4 && (
                        <div className='mb-4'>
                            <CourseInput
                                title='Wprowadź maksymalny rozmiar grupy:'
                                placeholder='24'
                                width="20%"
                                name="size"
                                onChange={(e) => setMaxGroupSize(Number(e.target.value))}
                            />
                            <h2 className='font-roboto mb-2 mt-4 text-lg'>
                                Wybierz studentów którzy będą należeć do grupy:
                            </h2>
                            <div className='border p-4 w-full flex-col rounded-lg'>
                                {
                                    students.map((student: Student) => (
                                            <StudentCheckBox
                                                // key do zmiany
                                                key={student.name}
                                                name={student.name}
                                                surname={student.surname}
                                                studentId={student.studentId}
                                                onCheck={handleCheck}
                                            />
                                    ))
                                }
                            </div>


                        </div>

                    )
                }

            </div>
            <div className='flex gap-4'>
                {
                    step !== 1 && (
                        <button
                            className='px-6 py-2 font-roboto text-lg tracking-wider border font-[400] rounded-lg hover:bg-gray-100 hover:text-bg transition-colors duration-300 '
                            onClick={previousStep}
                        >
                            Powrót
                        </button>
                    )
                }

                <button
                    className='px-6 py-2 font-roboto text-lg tracking-wider border font-[400] rounded-lg hover:bg-gray-100 hover:text-bg transition-colors duration-300 '
                    onClick={nextStep}
                >
                {step === 4 ? "Zatwierdź" : "Kolejny krok"}
                </button>
            </div>

        </div>
    );
};

export default Page;