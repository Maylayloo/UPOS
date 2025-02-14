'use client'

import {dashboardContent} from "@/constants/dashboardTiles";
import DashboardTile from "@/components/sections/dashboard/DashboardTile";

const Page = () => {

    const role = JSON.parse(localStorage.getItem('upos_user_role') || '')

    return (
        <div className="flex flex-wrap justify-center gap-8">
            {
                // mapping over dashboardContent - table from constants dir, creating tiles using DashboardTile component
                dashboardContent.map((tile) => (
                    <DashboardTile
                        key={tile.title}
                        href={tile.href}
                        icon={tile.icon}
                        altIcon={tile.altIcon}
                        title={tile.title}
                        role={role}
                        allowedRoles={tile.allowedRoles}
                        leadingColor={tile.leadingColor}
                    />
                ))
            }
        </div>
    );

};

export default Page;