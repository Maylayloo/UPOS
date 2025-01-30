'use client'

import {dashboardContent} from "@/constants/dashboardTiles";
import DashboardTile from "@/components/subcomponents/DashboardTile";
import React from "react";
import {useUser} from "@/app/(context)/UserContext";

const Page = () => {


    // TODO: zmien localstorage na session storage
    const storedUser = JSON.parse(localStorage.getItem("upos_user") || '{}');
    const role = storedUser.role.toLowerCase();

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