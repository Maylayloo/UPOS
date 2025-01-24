'use client'

import React, { useState, useEffect } from "react";
import GroupInfoContainer from "@/components/subcomponents/GroupInfoContainer";
import StudentInGroupContainer from "@/components/subcomponents/StudentInGroupContainer";

import Image from "next/image";
import dawidIMG from "@/public/images/dawid.png"

const Page = () => {

    const [isLoading, setIsLoading] = useState(true);
    const [currentGroup, setCurrentGroup] = useState<any>(null);

    const currentGroupId = localStorage.getItem('current_group');
    const currentCourseName = localStorage.getItem('current_course');

    // wait for currentGroupId, then start fetching data
    useEffect(() => {
        if (!currentGroupId) {
            setIsLoading(false);
            return;
        }

        const fetchData = async () => {
            try {
                const response = await fetch(`http://localhost:8080/groups/${currentGroupId}`, {
                    method: "GET",
                    credentials: "include",
                });
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                const data = await response.json();
                localStorage.setItem(`current_group_data`, JSON.stringify(data));
                setCurrentGroup(data);  // Zapisz dane grupy w stanie
                setIsLoading(false);  // Ustaw stan ładowania na false
            } catch (err) {
                console.error("Error fetching data:", err);
                setIsLoading(false);  // Zakończ ładowanie w przypadku błędu
            }
        };

        fetchData();
    }, [currentGroupId]);

    // checking if data is saved in localStorage
    useEffect(() => {
        const storedData = localStorage.getItem('current_group_data');
        if (storedData) {
            setCurrentGroup(JSON.parse(storedData));
            setIsLoading(false);
        }
    }, []);

    // Until data isn't fetched, don't load the component. Returning this instead:
    if (isLoading || !currentGroup) {
        return <div>
            <Image className=''
                   src={dawidIMG}
                   alt="LOADING"
            />
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


    const students = [
        {
            name: "Drillb Aby"
        },
        {
            name: "Pedo Phile"
        },
        {
            name: "Mr Beast"
        },
        {
            name: "Coco Anus"
        },
        {
            name: "Dominik Afgańczynski"
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
                        <span className='text-[0.85rem]'>
                            Semestr zimowy 2024/2025
                        </span>
                    </h3>
                </div>

                {
                    groupData.map((group) => (
                        <GroupInfoContainer
                            key={group.title}
                            title={group.title}
                            content={group.content}
                        />
                    ))
                }

            </div>
            <div className='w-3/4 border-[#DBE3D4] border flex flex-col items-center rounded-xl pb-8'>
                <div className='w-full bg-[#D9D9D9] rounded-t-xl text-center'>
                    <h3 className='text-bg text-2xl tracking-wider font-outfit py-3'>
                        LISTA STUDENTÓW
                    </h3>
                </div>

                {
                    students.map((student, index) => (
                        <StudentInGroupContainer
                            key={index}
                            index={index + 1}
                            name={student.name}
                        />
                    ))
                }

            </div>
        </div>
    );
};

export default Page;
