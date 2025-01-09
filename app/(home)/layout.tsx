'use client'
import React from "react";
import Navbar from "@/components/Navbar";
import {usePathname} from "next/navigation";

const HomeLayout = ({children, }: {children: React.ReactNode}) => {

    const path = usePathname();
    // czy na pewno tak to miało wyglądać???
    const borderColors : Record<string, string> = {
        "/dashboard/profile": "border-[#3A7708]",
        "/dashboard/schedule": "border-[#077298]",
        "/dashboard/statistics": "border-[#969107]",
        "/dashboard/grades": "border-[#9A088E]",
        "/dashboard/groups": "border-[#DBE3D4]",
        "/dashboard/roulette": "border-[#A21C1E]",
    }

    const borderColor = borderColors[path] || "border-[#9AD6D6]"

    return (

        <div>
            <Navbar/>
             <div className="mb-24 w-full h-1"/>

            <div className={`w-full min-h-[75svh] border ${borderColor} rounded-xl bg-bg p-8 3xl:px-24 mb-16`}>
                 {children}
             </div>
        </div>
    );
};

export default HomeLayout;