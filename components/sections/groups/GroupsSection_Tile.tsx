import React, {useEffect, useState} from "react";
import GroupContainer from "@/components/sections/groups/GroupContainer";
import Loading from "@/components/layout/Loading";

interface props {
    name: string,
    ects: number,
    courseId: string,
    major: string,
    semester: number
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

const daysTranslation = {
    MONDAY: "Każdy poniedziałek",
    TUESDAY: "Każdy wtorek",
    WEDNESDAY: "Każda środa",
    THURSDAY: "Każdy czwartek",
    FRIDAY: "Każdy piątek",
    SATURDAY: "Każda sobota",
    SUNDAY: "Każda niedziela",
};

const GroupsSection_Tile = ({name, ects, courseId, major, semester}: props) => {
    const [groups, setGroups] = useState<Groups[] | null>(null);
    const [loading, setLoading] = useState<boolean>(true);

    const storedUser = JSON.parse(localStorage.getItem("upos_user") || '{}');
    const role = storedUser.role.toLowerCase();

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
                localStorage.setItem(`upos_group${courseId}`, JSON.stringify(data));
                setGroups(data)
            } catch (err) {
                console.error("Error fetching data:", err);
            } finally {
                setLoading(false);
            }
        };

        fetchData();
    }, [courseId]);

    const storedGroup = JSON.parse(localStorage.getItem(`upos_group${courseId}`) || '[]');

    const translateDay = (day: keyof typeof daysTranslation) => daysTranslation[day] || "Nieznany dzień";


    if (loading) {
        return (
            <Loading/>
        );
    }

    return (
        <div className={`border-b border-b-[#DBE3D4] w-full px-8 py-5 ${groups?.length === 0 ? 'hidden' : ''}`}>
            <h2 className='font-roboto font-[500] text-lg tracking-wide'>
                {name} {role === 'professor' ? `- ${major}` : ""}
                <span className='font-[300] text-base'>
                   {
                       role === 'student' ? ` [punkty ECTS: ${ects}]` : ` - ${semester} semestr`
                   }

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
                        dotw={translateDay(group.dayOfTheWeek as keyof typeof daysTranslation)}
                        startHour={group.startOfLesson.slice(0, 5)}
                        endHour={group.endOfLesson.slice(0, 5)}
                        courseName={name}
                        groupId={group.groupId}
                    />
                ))
            }



        </div>
    );
};

export default GroupsSection_Tile;