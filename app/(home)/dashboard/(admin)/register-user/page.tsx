'use client'
import {useEffect, useState} from "react";

const Page = () => {
    const [role, setRole] = useState("STUDENT")

    return (
        <div className='border rounded-xl p-4 flex flex-col items-center gap-2'>
            <h2 className='text-lg font-roboto'>
                Kogo chcesz zarejestrować?
            </h2>
            <select
                className='text-black font-roboto px-4 py-1 tracking-wide rounded'
                onChange={(e) => {setRole(e.target.value)}}
            >
                <option value="STUDENT">
                    Student
                </option>
                <option value="PROFESSOR">
                    Profesor
                </option>
            </select>
        </div>
    );
};

export default Page;