import React, { useState } from "react";

interface Props {
    name: string;
    surname: string;
    studentId: Number;
    onCheck: (studentId: Number, isChecked: boolean) => void;
}

const StudentCheckBox = ({ name, surname, studentId, onCheck }: Props) => {
    const [isChecked, setIsChecked] = useState(false);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const checked = e.target.checked;
        setIsChecked(checked);
        onCheck(studentId, checked); // Notify parent component
    };

    return (
        <div className='flex gap-4 items-center'>
            <h3 className='text-lg tracking-wide font-roboto'>
                {name} {surname}
            </h3>
            <input
                type="checkbox"
                className='appearance-none w-4 h-4 border-2 checked:bg-green-500 rounded cursor-pointer'
                checked={isChecked}
                onChange={handleChange}
            />
        </div>
    );
};

export default StudentCheckBox;
