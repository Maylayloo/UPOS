'use client'

import React from 'react';
import {usePathname} from "next/navigation";

const DynamicBody = ({children}: {children: React.ReactNode }) => {

    const path = usePathname();

    const bodyThemes: Record<string, string> = {
        "/dashboard": "bg-gradient-to-t from-[#2f6478] to-bg to-[85%]",
        "/dashboard/profile": "bg-gradient-to-t from-[#224406] to-bg to-[85%]",
        "/dashboard/schedule": "bg-gradient-to-t from-[#065F7F] to-bg to-[85%]",
        "/dashboard/statistics": "bg-gradient-to-t from-[#827E09] to-bg to-[85%]",
        "/dashboard/grades": "bg-gradient-to-t from-[#67095F] to-bg to-[85%]",
        "/dashboard/groups": "bg-gradient-to-t from-[#8a8a8a] to-bg to-[85%]",
        "/dashboard/roulette": "bg-gradient-to-t from-[#4D0F10] to-bg to-[85%]",
        "/dashboard/exams": "bg-gradient-to-t from-[#ee7430] to-bg to-[85%]",
        "/dashboard/course-management": "bg-gradient-to-t from-[#3b9ead] to-bg to-[85%]",
        "/dashboard/course-management/create": "bg-gradient-to-t from-[#104291] to-bg to-[85%]",
        "/dashboard/group-management/create": "bg-gradient-to-t from-[#2ab86c] to-bg to-[85%]",
        "/dashboard/group-management": "bg-gradient-to-t from-[#2ab86c] to-bg to-[85%]",
        "/dashboard/course-management/create/success": "bg-gradient-to-t from-[#28e04d] to-bg to-[85%]",
    }

    const bodyColor = bodyThemes[path] || "bg-bg"

    return (
        <body className={`mx-[157px] max-xl:mx-12 max-sm:mx-8 mt-14 text-white overflow-x-hidden ${bodyColor}`}>
            {children}
        </body>
    );
};

export default DynamicBody;