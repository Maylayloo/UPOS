import React, {useEffect, useState} from "react";
import Image from "next/image";
import dawidIMG from "@/public/images/dawid.png";

interface props {
    type: string,
    groupId?: number,
}

const GradeContainer = ({type, groupId}: props) => {

    const [loading, setLoading] = useState<boolean>(true);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch(`http://localhost:8080/students/loggedIn/grades/NonPartial/${groupId}`, {
                    method: "GET",
                    credentials: "include",
                });

                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }

                // Sprawdzamy, czy odpowiedź zawiera treść, zanim wywołamy .json()
                const text = await response.text();
                const data = text ? JSON.parse(text) : null;

                localStorage.setItem(`upos_grade${groupId}`, JSON.stringify(data));
            } catch (err) {
                console.error("Error fetching data:", err);
            } finally {
                setLoading(false)
            }
        };

        if (groupId !== undefined) {
            fetchData();
        } else {
            setLoading(false)
        }
    }, [groupId]);


    const storedGrade = JSON.parse(localStorage.getItem(`upos_grade${groupId}`) || '[]');
    const grade = storedGrade?.value === undefined ? "-" : storedGrade.value;

    if (loading) {
        return (
            <div>
                <Image className=''
                       src={dawidIMG}
                       alt="LOADING"
                />
            </div>
        )
    }

    return (
        <div className="flex justify-between items-center ">
            <h2>
                {type}
            </h2>
            <div className={`rounded-full w-[3rem] h-[3rem] relative shadow-md flex justify-center items-center ${grade === "-" ? 'text-2xl' : 'bg-[#67095F]'}`}>
                <h3 className=''>
                    {grade}
                </h3>
            </div>
        </div>
    );
};

export default GradeContainer;