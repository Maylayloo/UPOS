import {dashboardContent} from "@/constants/dashboardTiles";
import DashboardTile from "@/components/subcomponents/DashboardTile";
import React from "react";

const Page = () => {

    const role = "student"

    return (
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
    );
};

export default Page;