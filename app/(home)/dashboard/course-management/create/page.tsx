'use client'

import React, {useEffect, useState} from 'react';
import CourseInput from "@/components/sections/admin-courses/CourseInput";
import {fetchMajors} from "@/services/api/major";
import CustomSelect from "@/components/sections/admin-courses/CustomSelect";
import {fetchProfessors} from "@/services/api/professor";
import {postCourse} from "@/services/api/course";
import {useRouter} from "next/navigation";
import Loading from "@/components/layout/Loading";

const CreateCoursePage = () => {

    const [majors, setMajors] = useState([])
    const [professors, setProfessors] = useState([])
    const [loading, setLoading] = useState(true)
    const semesters = ["1", "2", "3", "4", "5", "6", "7"];
    const [selectedMajor, setSelectedMajor] = useState("");
    const [selectedProfId, setSelectedProfId] = useState("");
    const [selectedSemester, setSelectedSemester] = useState(semesters[0]);

    const router = useRouter()

    interface Professor {
        professorId: number,
        title: string,
        name: string,
        surname: string,
    }

    const createCourse = async (e: React.FormEvent) => {
        e.preventDefault()

        const formData = new FormData(e.target as HTMLFormElement);
        const courseName = formData.get("courseName") as string;
        const ects = formData.get("ects") as string;

        const submitCourse = await postCourse(courseName, Number(ects), Number(selectedProfId), Number(selectedSemester), selectedMajor);

        if (submitCourse) {
            router.push('/dashboard/course-management/create/success')
        } else {
            alert("Coś poszło nie tak")
        }
    }

    useEffect(() => {
        const getData = async () => {
            // fetch all majors
            const fetchedMajors = await fetchMajors()
            // fetch all professors
            const fetchedProfessors = await fetchProfessors()

            if (fetchedMajors && fetchedProfessors) {
                setMajors(fetchedMajors);
                // set 'default' value as first element of fetched majors
                setSelectedMajor(fetchedMajors[0])

                setProfessors(fetchedProfessors);
                // analogously for profId
                setSelectedProfId(fetchedProfessors[0].professorId)

            }
            setLoading(false)
        };
        getData();
    }, []);

    if (loading) return(
        <Loading/>
    );

    return (
        <form
            className='w-full flex flex-col items-center gap-4'
            onSubmit={createCourse}
        >
            <div
                className='w-full flex justify-center gap-8'

            >
                <CourseInput
                    title='Nazwa przedmiotu'
                    placeholder='Algebra'
                    width="40%"
                    name="courseName"
                />
                <CourseInput
                    title='Liczba punktów ECTS'
                    placeholder='6'
                    name="ects"
                />
            </div>
            <h2 className='text-lg font-roboto'>
                Wybierz kierunek studiów i semestr:
            </h2>
            <div className='w-full flex justify-center gap-8'>
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
            <h2 className='text-lg font-roboto'>
                Wybierz koordynatora przedmiotu:
            </h2>
            <select
                className='text-black w-[40%] py-1 px-3 focus:outline-none'
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

            <button
                className='mt-4 font-roboto text-xl font-[500] tracking-widest hover:bg-[#3b9ead]
                 px-6 py-2 rounded-lg border-2 border-[#3b9ead] text-[#3b9ead] hover:text-white
                  transition-colors duration-300 delay-75'
                type="submit"
            >
                ZATWIERDŹ
            </button>
        </form>
    );
};

export default CreateCoursePage;