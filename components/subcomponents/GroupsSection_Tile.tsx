import React, {useEffect} from "react";
import GroupContainer from "@/components/subcomponents/GroupContainer";

interface groupTileInterface {
    name: string,
    ects: number,
    courseId: string,
}

interface Groups {
    groupId: number,
    courseId: number,
    type: string,
    numberOfGroup: number,
    dayOfTheWeek: string,
    maxStudentAmount: number,
    startOfLesson: string,
    endOfLesson: string,
    studentsIds: number[],
    place: string,
}

const GroupsSection_Tile = ({name, ects, courseId }: groupTileInterface) => {

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch(`http://localhost:8080/groups/loggedIn/${courseId}`, {
                    method: "GET",
                    credentials: "include",
                });
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                const data = await response.json();
                localStorage.setItem(`group${courseId}`, JSON.stringify(data));
            } catch (err) {
                console.error("Error fetching data:", err);
            }
        };

        fetchData();
    }, []);

    const storedGroup = JSON.parse(localStorage.getItem(`group${courseId}`) || '{}');

    console.log(storedGroup)

    return (
        <div className='border-b border-b-[#DBE3D4] w-full px-8 py-5'>
            <h2 className='font-roboto font-semibold text-lg tracking-wide'>
                {name} <span className='font-[300] text-base'>
                    [punkty ECTS: {ects}]
                </span>
            </h2>
            <h3>
                Semestr zimowy 2024/2025
            </h3>

            {
                storedGroup.map((group: Groups) => (
                    <GroupContainer
                        key={group.groupId}
                        type={group.type}
                        no={group.numberOfGroup}
                        dotw={group.dayOfTheWeek}
                        startHour={group.startOfLesson}
                        endHour={group.endOfLesson}
                    />
                ))
            }



        </div>
    );
};

export default GroupsSection_Tile;