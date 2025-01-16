'use client'

import {dashboardContent} from "@/constants/dashboardTiles";
import DashboardTile from "@/components/subcomponents/DashboardTile";
import React from "react";
import {useUser} from "@/app/(context)/UserContext";

const Page = () => {

    // useUser() hook
    const {user} = useUser()

    // get user's role
    const role = user.role.toLowerCase();

    return (
        <div className="flex flex-wrap justify-center gap-8">
            {
                // mapping over dashboardContent - table from constants dir, creating tiles using DashboardTile component
                dashboardContent.map((tile) => (
                    <DashboardTile
                        key={tile.title}
                        href={tile.href}
                        icon={tile.icon}
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