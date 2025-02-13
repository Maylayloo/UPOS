import React from "react";

interface props {
    onClick?: () => void,
    children: React.ReactNode;
}

const CustomButton = ({onClick, children}: props) => {
    return (
        <button
            className='px-6 py-2 font-roboto text-lg tracking-wider border font-[400] rounded-lg hover:bg-gray-100 hover:text-bg transition-colors duration-300 '
            onClick={onClick}
        >
            {children}
        </button>
    );
};

export default CustomButton;