import React from "react";
import GroupContainer from "@/components/subcomponents/GroupContainer";

const GroupsSection_Tile = () => {
    return (
        <div className='border-b border-b-[#DBE3D4] w-full px-8 py-5'>
            <h2 className='font-roboto font-semibold text-lg tracking-wide'>
                Metody Numeryczne <span className='font-[300] text-base'>
                    [punkty ECTS: 7]
                </span>
            </h2>
            <h3>
                Semestr zimowy 2024/2025
            </h3>


            <GroupContainer/>


        </div>
    );
};

export default GroupsSection_Tile;