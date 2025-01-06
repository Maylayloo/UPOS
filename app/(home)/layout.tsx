import React from "react";
import Navbar from "@/components/Navbar";

const HomeLayout = ({children, }: {children: React.ReactNode}) => {

    // czy na pewno tak to miało wyglądać???
    return (

        <div>
            <Navbar/>
             <div className="mb-24 w-full h-1"/>

            <div className="w-full min-h-[75svh] border border-[#9AD6D6] rounded-xl bg-bg p-8 3xl:px-24 mb-16">
                 {children}
             </div>
        </div>
    );
};

export default HomeLayout;