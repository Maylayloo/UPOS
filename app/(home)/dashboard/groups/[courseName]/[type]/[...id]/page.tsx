'use client'

import React, { useState, useEffect } from "react";
import GroupInfoContainer from "@/components/sections/groups/GroupInfoContainer";
import StudentInGroupContainer from "@/components/sections/groups/StudentInGroupContainer";
import Loading from "@/components/layout/Loading";
import {fetchProfData} from "@/services/api/professor";
import {getGroupById} from "@/services/api/group";
import {getNamesByIds} from "@/services/api/student";

const Page = () => {

    interface Professor {
        name: string,
        surname: string,
        title: string,
    }

    const [loading, setLoading] = useState(true);
    const [currentGroup, setCurrentGroup] = useState<any>(null);
    const [students, setStudents] = useState<any[]>([]); // 🆕 Nowy stan na studentów
    const [profData, setProfData] = useState<Professor>();

    const currentGroupId = localStorage.getItem('upos_current_group');
    const currentCourseName = localStorage.getItem('upos_current_course');

    useEffect(() => {
        const fetchGroupData = async () => {
            const fetchedGroup = await getGroupById(Number(currentGroupId))
            if (fetchedGroup) {
                setCurrentGroup(fetchedGroup);

                const [fetchedProfessor, fetchedStudentsNames] = await Promise.all(
                    [fetchProfData(fetchedGroup.professorId), getNamesByIds(fetchedGroup.studentsIds)]
                )

                if (fetchedStudentsNames && fetchedProfessor) {
                    setStudents(fetchedStudentsNames);
                    setProfData(fetchedProfessor);
                }
            }
            setLoading(false);
        };
        fetchGroupData();
    }, [currentGroupId]);

    if (loading) {
        return <Loading/>
    }

    const groupData = [
        {
            title: "Prowadzący",
            content: `${profData?.title} ${profData?.name} ${profData?.surname}`
        },
        {
            title: "Termin",
            content: `Każdy ${currentGroup.dayOfTheWeek}, ${currentGroup.startOfLesson.slice(0, 5)} - ${currentGroup.endOfLesson.slice(0, 5)}`,
        },
        {
            title: "Miejsce",
            content: currentGroup.place
        },
        {
            title: "Liczba osób w grupie",
            content: String(currentGroup.studentsIds.length)
        },
        {
            title: "Limit miejsc",
            content: String(currentGroup.maxStudentAmount)
        },
    ];
    return (
        <div className='w-full flex justify-center flex-col items-center gap-8'>
            <div className='w-3/4 border-[#DBE3D4] border flex flex-col items-center rounded-xl pb-8'>
                <div className='w-full bg-[#D9D9D9] rounded-t-xl text-center'>
                    <h3 className='text-bg text-2xl tracking-wider font-outfit py-3'>
                        {currentCourseName} <br />
                        <span className='text-[1rem]'>
                            {currentGroup.type.toUpperCase()}, GRUPA NR {currentGroup.numberOfGroup}
                        </span>
                        <br />
                        <span className='text-[0.85rem]'>Semestr zimowy 2024/2025</span>
                    </h3>
                </div>

                {groupData.map((group) => (
                    <GroupInfoContainer
                        key={group.title}
                        title={group.title}
                        content={group.content} />
                ))}
            </div>

            <div className={`w-3/4 border-[#DBE3D4] border flex flex-col items-center rounded-xl pb-8 ${students.length > 0 ? "" : "hidden"}`}>
                <div className='w-full bg-[#D9D9D9] rounded-t-xl text-center'>
                    <h3 className='text-bg text-2xl tracking-wider font-outfit py-3'>LISTA STUDENTÓW</h3>
                </div>

                {
                    students.map((student, index) => (
                    <StudentInGroupContainer
                        key={index}
                        index={index + 1}
                        name={`${student.name} ${student.surname}`}
                        groupId={Number(currentGroupId)}
                        studentId={Number(student.studentId)}
                    />
                ))}
            </div>
        </div>
    );
};

export default Page;
