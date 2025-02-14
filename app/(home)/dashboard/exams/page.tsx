'use client'

import ExamTile from "@/components/sections/exams/ExamTile";
import React, {useEffect, useState} from "react";
import Loading from "@/components/layout/Loading";
import {fetchExams} from "@/services/api/exam";

const Page = () => {

    interface Exam {
        examId: number,
        courseId: number,
        professorId: number,
        date: string,
        place: string,
        attempt: string,
        startOfExam: string,
    }

    const [loading, setLoading] = useState(true);
    const [exams, setExams] = useState<Exam[]>([]);
    const role = JSON.parse(localStorage.getItem('upos_user_role') || '')

    useEffect(() => {
        const loadExams = async () => {
            const fetchedExams = await fetchExams(role);
            if (fetchedExams) {
                setExams(fetchedExams);
            }
            setLoading(false);
        }

        loadExams();
    }, [])

    if (loading) {
        return (
           <Loading/>
        )
    }
    if (exams) {
    return (
        <div className='flex justify-center gap-8 flex-wrap'>
            {
                exams.map((exam: Exam) => (
                    <ExamTile
                        key={exam.examId}
                        courseId={exam.courseId}
                        attempt={exam.attempt}
                        date={exam.date}
                        startHour={exam.startOfExam.slice(0, 5)}
                        place={exam.place}
                    />
                ))
            }
        </div>
    );
    }

};

export default Page;