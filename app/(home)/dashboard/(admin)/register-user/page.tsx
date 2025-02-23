'use client'
import {useEffect, useState} from "react";

const Page = () => {
    const [role, setRole] = useState("STUDENT")

    return (
        <div className='border rounded-xl p-4 flex flex-col items-center gap-8'>
            <div className='flex flex-col items-center gap-3'>
                <h2 className='text-lg font-roboto'>
                    Kogo chcesz zarejestrować?
                </h2>
                <select
                    className='text-black font-roboto px-4 py-1 tracking-wide rounded cursor-pointer focus:outline-none'
                    onChange={(e) => {
                        setRole(e.target.value)
                    }}
                >
                    <option value="STUDENT">
                        Student
                    </option>
                    <option value="PROFESSOR">
                        Profesor
                    </option>
                </select>
            </div>


            <button>
                Dalej
            </button>
        </div>
    );
};

export default Page;