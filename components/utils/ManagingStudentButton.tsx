import React from "react";

interface props {
    content: string,
    onClick?: React.MouseEventHandler<HTMLButtonElement> ,
}


const ManagingStudentButton = ({content, onClick}: props) => {
    return (
        <button
            onClick={onClick}
            className="bg-[#DBE3D4] text-bg font-[500] font-roboto my-1 px-3 rounded-xl hover:bg-bg hover:text-[#DBE3D4] border-2 border-bg hover:border-[#DBE3D4]"
        >
            {content}
        </button>
    );
};

export default ManagingStudentButton;