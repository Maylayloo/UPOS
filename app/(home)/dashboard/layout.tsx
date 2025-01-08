import React from 'react';
//
// const DashboardLayout = ({student, prof, }: {
//     student: React.ReactNode;
//     prof: React.ReactNode;
// }) => {

    //get role from backend
    // const role = "student"

const DashboardLayout = ({children,}: {
    children: React.ReactNode;
}) => {

    return (
        <div className='flex flex-col items-center'>
            <h2 className="text-3xl uppercase mt-4 mb-10 tracking-widest font-outfit">
                panel
            </h2>
            <div className='flex flex-wrap justify-center gap-8'>
                {
                    children
                }
            </div>
        </div>

    );
};

export default DashboardLayout;