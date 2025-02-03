import React from "react";

interface props {
    arr: any[],
    value?: any,
    additionalText?: string,
    width: string,
    onChange?: (e: React.ChangeEvent<HTMLSelectElement>) => void;
}
const CustomSelect = ({arr, value, additionalText, width, onChange}: props) => {
    return (
        <select
            className={`text-black w-[${width}] py-1 px-3 focus:outline-none`}
            name=""
            onChange={onChange}
        >
            {
                arr.map((item) => (
                    <option
                        className='font-roboto'
                        key={item}
                        value={value ? value : item}
                    >
                        {item}{additionalText}
                    </option>
                ))
            }
        </select>
    );
};

export default CustomSelect;