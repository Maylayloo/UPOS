import React from 'react';
import GradeTile from "@/components/subcomponents/GradeTile";

const GradesPage = () => {
    return (
        <div className='w-full flex justify-around flex-wrap gap-y-8 mt-4'>
            <GradeTile/>
            <GradeTile/>
            <GradeTile/>
            <GradeTile/>
            <GradeTile/>
            <GradeTile/>
        </div>
    );
};

export default GradesPage;