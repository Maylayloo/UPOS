import React from "react";

interface props {
    className?: string,
    children: React.ReactNode,
}

const CustomTitle = ({className, children}: props) => {
    return (
        <h2 className={`text-lg font-roboto text-center ${className}`}>
            {children}
        </h2>
    );
};

export default CustomTitle;