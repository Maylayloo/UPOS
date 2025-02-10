import React from "react";

interface props {
    title: string,
    placeholder: string,
    width?: string,
    name: string,
    onChange?: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

const CourseInput = ({title, placeholder, width, name, onChange}: props) => {
    return (
        <div className={`flex flex-col items-center w-[${width}]`}>
            <h2 className='mb-1 font-roboto text-lg'>
                {title}
            </h2>
            <input
                className='text-black text-lg focus:outline-none px-5 py-2 rounded-xl w-full focus:bg-blue-100 placeholder:text-base'
                type="text"
                placeholder={`np. ${placeholder}`}
                name={name}
                autoComplete="off"
                onChange={onChange}
            />
        </div>
    );
};

export default CourseInput;