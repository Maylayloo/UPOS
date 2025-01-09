'use client'

import React from 'react';
import {usePathname} from "next/navigation";
//
// const DashboardLayout = ({student, prof, }: {
//     student: React.ReactNode;
//     prof: React.ReactNode;
// }) => {

    //get role from backend
    // const role = "student"


const DashboardLayout = ({children,}: {
    children: React.ReactNode;
}) => {

    const path = usePathname();

    const titles: Record<string, string> = {
        "/dashboard/profile": "Jan Kowalski", // tu imię i nazwisko zalogowanego użytkownika
        "/dashboard/schedule": "Plan Zajęć",
        "/dashboard/statistics": "Statystyki",
        "/dashboard/grades": "oceny",
        "/dashboard/groups": "grupy zajęciowe",
        "/dashboard/roulette": "ruletka",
    }

    const displayTitle = titles[path] || "panel";



    return (
        <div className='flex flex-col items-center'>
            <h2 className="text-3xl uppercase mt-4 mb-10 tracking-widest font-outfit">
                {displayTitle}
            </h2>
            <div className='w-full h-full'>
                {
                    children


                }
            </div>
        </div>

    );
};

export default DashboardLayout;