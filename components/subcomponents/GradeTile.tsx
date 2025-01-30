import GradeContainer from "@/components/subcomponents/GradeContainer";
import {useEffect} from "react";

interface props {
    courseName: string,
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

const GradeTile = ({courseName, ects, courseId}: props) => {

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
                console.log('xD', data)
            } catch (err) {
                console.error("Error fetching data:", err);
            }
        };

        fetchData();
    }, []);


    const storedGroup = JSON.parse(localStorage.getItem(`upos_group${courseId}`) || '[]');


    return (
        <div className='font-roboto px-6 pt-4 pb-8 w-[27.5%] border rounded-xl min-w-max'>
            <h1 className='text-lg'>
                {courseName}
            </h1>
            <span className='font-[300]'>
                punkty ECTS: {ects}.00
            </span>
            <div className='flex flex-col gap-4 mt-2'>
                <GradeContainer type='Przedmiot'/>

                {
                    storedGroup.map((group: Groups) => (
                        <GradeContainer
                            key={group.groupId}
                            type={group.type}
                            groupId={group.groupId}
                        />
                    ))

                }

            </div>
        </div>
    );
};

export default GradeTile;