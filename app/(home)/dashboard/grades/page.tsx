'use client'

import React from 'react';
import GradeTile from "@/components/subcomponents/GradeTile";

const GradesPage = () => {

    // interface defining data types in courses fetched from database
    interface Course {
        courseId: string,
        name: string,
        ects: number,
        professorId: number,
        semester: number,
        major: string,
        exams: string[]
    }

    // getting courses from localStorage
    const storedCourses: Course[] = JSON.parse(localStorage.getItem("upos_courses") || '[]');
    console.log(storedCourses)


    return (
        <div className='w-full flex justify-around flex-wrap gap-y-8 mt-4'>
            {
                // mapping over courses and generating tiles
                storedCourses.map((course: Course) => (
                    <GradeTile
                        key={course.courseId}
                        courseName={course.name}
                        ects={course.ects}
                        courseId={course.courseId}
                    />
                ))
            }
        </div>
    );
};

export default GradesPage;