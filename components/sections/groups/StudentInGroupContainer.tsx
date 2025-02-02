import React, {useEffect, useState} from "react";
import Loading from "@/components/layout/Loading";
import ManagingStudentButton from "@/components/utils/ManagingStudentButton";
import {fetchGrade, gradeStudent} from "@/services/api/grade";

interface Props {
    index: number;
    name: string;
    groupId: number;
    studentId: number;
}

const StudentInGroupContainer = ({ index, name, groupId, studentId}: Props) => {
    const storedUser = JSON.parse(localStorage.getItem("upos_user") || "{}");
    const role = storedUser.role.toLowerCase();
    const grades = ["2.0", "3.0", "3.5", "4.0", "4.5", "5.0"];


    const [activeGrade, setActiveGrade] = useState(false);
    const [selectedGrade, setSelectedGrade] = useState(grades[0]);
    const [successfullyGraded, setSuccessfullyGraded] = useState(false);
    const [alreadyGraded, setAlreadyGraded] = useState(false);
    const [loading, setLoading] = useState(true);
    let [currentGrade, setCurrentGrade] = useState("");
    const [currentGradeId, setCurrentGradeId] = useState("");

    useEffect(() => {
        const loadGrade = async () => {
            const fetchedGrade = await fetchGrade(studentId, groupId);
            if (fetchedGrade) {
                setCurrentGrade(fetchedGrade.value)
                setCurrentGradeId(fetchedGrade.gradeId)
                setAlreadyGraded(true)
            }
            setLoading(false)
        }

        loadGrade();
    })

    // grade student
    const postGrade = async (method: string) => {
        const submitGrade = await gradeStudent(studentId, groupId, selectedGrade, currentGradeId, method);

            if (submitGrade && method === 'POST') {
                setActiveGrade(false);
                setSuccessfullyGraded(true)
            }
            if (submitGrade && method === 'PUT') {
                setActiveGrade(false);
                setAlreadyGraded(false);
                setSuccessfullyGraded(true)
            }
        }

    if (loading) {
     return (
         <Loading/>
     )
    }

    if (role === "student") {
        return (
            <div className="border-b border-b-[#DBE3D4] w-full font-roboto tracking-wide font-[300] py-1 flex justify-center">
                <div className="h-full w-full flex-[4]"></div>
                <h2 className="flex-[5]">
                    {index}. {name}
                </h2>
            </div>
        );
    }
    // if you're prof or admin
    return (
        <div className="border-b border-b-[#DBE3D4] w-full font-roboto tracking-wide font-[300] py-1 flex justify-center gap-16 items-center">
            <h2>
                {index}. {name}
            </h2>
            <div className='flex items-center relative'>
                { alreadyGraded && !activeGrade && (
                    <div className='flex items-center gap-4 -ml-6 font-roboto font-[400]'>
                        {currentGrade}
                        <ManagingStudentButton
                            onClick={() => {
                                setActiveGrade(true);
                                setSuccessfullyGraded(false);
                            }}
                            content="ZMIEŃ"/>
                    </div>

                )

                }

                {!activeGrade && !successfullyGraded && !alreadyGraded && (
                    <ManagingStudentButton
                        onClick={() => setActiveGrade(true)}
                        content="OCEŃ"
                    />
                )}

                {activeGrade && (
                    <div className="flex items-center gap-4">
                        <select
                            name="grade"
                            className="text-black font-roboto px-2 font-[400]"
                            value={selectedGrade}
                            onChange={(e) => setSelectedGrade(e.target.value)} // Aktualizuj stan
                        >
                            {grades.map((grade) => (
                                <option key={grade} value={grade}>
                                    {grade}
                                </option>
                            ))}
                        </select>
                        { alreadyGraded && (
                            // for editing grade
                            <ManagingStudentButton
                                onClick={() => postGrade("PUT")}
                                content='ZATWIERDŹ'
                            />
                        )}
                        {
                            // grading for first time
                            !alreadyGraded && (
                                <ManagingStudentButton
                                    onClick={() => postGrade("POST")}
                                    content='ZATWIERDŹ'
                                />
                            )
                        }

                        <ManagingStudentButton
                            onClick={() => {setActiveGrade(false)}}
                            content="ANULUJ"
                        />
                    </div>
                )}
                {
                    successfullyGraded && (
                        <h1 className="text-lg font-roboto text-green-400 ml-2">
                            Oceniono pomyślnie
                        </h1>
                    )
                }
            </div>
        </div>
    );
};

export default StudentInGroupContainer;
