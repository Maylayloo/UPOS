'use client'

import React from 'react';
import {usePathname, useRouter} from "next/navigation";
import Image from "next/image";
import backArrow from '@/public/icons/backArrow.png'

const DashboardLayout = ({children,}: {
    children: React.ReactNode;
}) => {

    const path = usePathname();
    const storedUser = JSON.parse(localStorage.getItem("upos_user") || '{}');
    const router = useRouter()

        const titles: Record<string, string> = {
            "/dashboard/profile": storedUser.name + " " + storedUser.surname, // tu imię i nazwisko zalogowanego użytkownika
            "/dashboard/schedule": "Plan Zajęć",
            "/dashboard/statistics": "Statystyki",
            "/dashboard/grades": "oceny",
            "/dashboard/groups": "grupy zajęciowe",
            "/dashboard/roulette": "ruletka",
            "/dashboard/exams": "egzaminy",
            "/dashboard/course-management": "przedmioty",
            "/dashboard/course-management/create": "stwórz przedmiot",
            "/dashboard/course-management/manage": "zarządzaj",
            "/dashboard/course-management/create/success": "Gratulacje!",
        }

        const displayTitle =
            path.startsWith("/dashboard/groups/") ? "grupy zajęciowe" : titles[path] || "panel";

    return (
        <div className='flex flex-col items-center relative'>
            <h2 className="text-3xl uppercase mt-4 mb-10 tracking-widest font-outfit">
                {displayTitle}
            </h2>

                <Image
                    className={` absolute left-0 cursor-pointer
                    ${path === '/dashboard' ? "hidden" : ""}`
                }
                    // router.back() pushes user to previous page
                    onClick={ () => {router.back()}}
                    src={backArrow}
                    alt="back"
                    width={36}
                />

            <div className='w-full h-full'>
                {
                    children


                }
            </div>
        </div>

    );
};

export default DashboardLayout;