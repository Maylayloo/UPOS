'use client'

import ExamTile from "@/components/subcomponents/ExamTile";
import {useEffect} from "react";

const Page = () => {

    interface Exam {
        examId: number,
        courseId: number,
        professorId: number,
        date: string,
        place: string,
        attempt: string,
    }


    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch(`http://localhost:8080/students/exams`, {
                    method: "GET",
                    credentials: "include",
                });
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                const data = await response.json();

                localStorage.setItem(`exams`, JSON.stringify(data));
                console.log('xD', data)
            } catch (err) {
                console.error("Error fetching data:", err);
            }
        };
        fetchData();
    }, []);

    const exams = JSON.parse(localStorage.getItem(`exams`) || '[]');

    return (
        <div className='flex justify-center gap-8 flex-wrap'>
            {
                exams.map((exam: Exam) => (
                    <ExamTile
                        key={exam.examId}
                    />
                ))
            }
        </div>
    );
};

export default Page;