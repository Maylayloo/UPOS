'use client'

import React, { useState, useEffect } from "react";
import GroupInfoContainer from "@/components/subcomponents/GroupInfoContainer";
import StudentInGroupContainer from "@/components/subcomponents/StudentInGroupContainer";

import Image from "next/image";
import dawidIMG from "@/public/images/dawid.png";

const Page = () => {
    const [isLoading, setIsLoading] = useState(true);
    const [currentGroup, setCurrentGroup] = useState<any>(null);
    const [students, setStudents] = useState<any[]>([]); // 🆕 Nowy stan na studentów

    const currentGroupId = localStorage.getItem('upos_current_group');
    const currentCourseName = localStorage.getItem('upos_current_course');

    useEffect(() => {
        if (!currentGroupId) {
            setIsLoading(false);
            return;
        }

        const fetchGroupData = async () => {
            try {
                const response = await fetch(`http://localhost:8080/groups/${currentGroupId}`, {
                    method: "GET",
                    credentials: "include",
                });
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                const data = await response.json();
                localStorage.setItem('upos_current_group_data', JSON.stringify(data));
                setCurrentGroup(data);
                setIsLoading(false);
            } catch (err) {
                console.error("Error fetching group data:", err);
                setIsLoading(false);
            }
        };

        fetchGroupData();
    }, [currentGroupId]);

    // fetching full names list by students' ids belong to specific group
    useEffect(() => {
        if (!currentGroup || !currentGroup.studentsIds) return;

        const fetchStudents = async () => {
            try {
                const response = await fetch(`http://localhost:8080/students/namesAndSurnames`, {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({ ids: currentGroup.studentsIds }),
                    credentials: "include",
                });

                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                const data = await response.json();
                setStudents(data);
            } catch (err) {
                console.error("Error fetching students:", err);
            }
        };

        fetchStudents();
    }, [currentGroup]);


    if (isLoading || !currentGroup) {
        return <div>
            <Image src={dawidIMG} alt="LOADING" />
        </div>;
    }

    const groupData = [
        {
            title: "Prowadzący",
            content: "Sperm o'Yad"
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
                        name={`${student.name} ${student.surname}`} />
                ))}
            </div>
        </div>
    );
};

export default Page;
