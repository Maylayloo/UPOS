import {dashboardContent} from "@/constants/dashboardTiles";
import DashboardTile from "@/components/subcomponents/DashboardTile";
import React from "react";

const Page = () => {

    const role = "student"

    return (
        <div className="flex flex-wrap justify-center gap-8">
            {
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