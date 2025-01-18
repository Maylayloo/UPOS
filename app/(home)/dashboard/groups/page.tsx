'use client'

import React from 'react';
import GroupsSection_Tile from "@/components/subcomponents/GroupsSection_Tile";

const GroupsPage = () => {

    interface Course {
        courseId: string,
        name: string,
        ects: number,
        professorId: number,
        semester: number,
        major: string,
        exams: string[]
    }


    const storedCourses: Course[] = JSON.parse(localStorage.getItem("courses") || '[]');
    return (
        <div className='w-full flex justify-center'>
            <div className='w-3/4 border-[#DBE3D4] border flex flex-col items-center rounded-xl'>
                <div className='w-full bg-[#D9D9D9] rounded-t-xl text-center'>
                    <h3 className='text-bg text-2xl tracking-wider font-outfit py-3'>
                        ROK ADAKEMICKI: 2024/2025
                    </h3>
                </div>
                <GroupsSection_Tile/>
                <GroupsSection_Tile/>
                <GroupsSection_Tile/>
                <GroupsSection_Tile/>
                <GroupsSection_Tile/>
                <GroupsSection_Tile/>
                <GroupsSection_Tile/>
                <GroupsSection_Tile/>
            </div>
        </div>

    );
};

export default GroupsPage;