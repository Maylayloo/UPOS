'use client'

import NavLink from "@/components/subcomponents/NavLink";
import Image from "next/image";
import hamburger from "@/public/icons/hamburger.png"
import close from "@/public/icons/close.png"
import {useState} from "react";
import {useUser} from "@/app/(context)/UserContext";

const Navbar = () => {

    // change localstorage to session-storage
    const storedUser = JSON.parse(localStorage.getItem("upos_user") || '{}');

    // get user's role
    const role = storedUser?.role?.toLowerCase();

    // get user's name and surname
    const name = storedUser?.name + " " + storedUser?.surname;

    // useState for mobile Navbar, describing if it is opened
    const [active, setActive] = useState(false)

    return (
        <nav className="absolute inset-x-0 top-0 h-[5.5rem] flex items-end font-outfit max-sm:items-center">
            <div className='absolute right-0 top-0 text-sm max-sm:hidden'>
                <h4 className='font-roboto'>
                    Zalogowano jako:
                    <span className='px-3'>
                        {name}
                    </span>

                    {/*TODO: make logout button work*/}
                    <button className='mr-4 px-6 rounded-lg py-1 my-2 border border-[#9AD6D6] text-[#9AD6D6]'>
                        WYLOGUJ
                    </button>
                </h4>
            </div>
            <div className="flex-1 px-16 max-sm:px-8">
                <h1 className='text-[36px] font-[500]'>
                    UPOS
                </h1>
            </div>
            <div className='flex-[2] flex items-center justify-evenly px-12 max-lg:gap-6 max-sm:hidden'>
                <NavLink href='/dashboard' content='MÓJ UPOS'></NavLink>
                <NavLink href='//' content={`${role == "student" ? "DLA STUDENTA" : "DLA PRACOWNIKA"}`}/>
                <NavLink href='//' content='DLA WSZYSTKICH'></NavLink>
            </div>
            <div className='sm:hidden flex-1 px-12'>
                <button className='float-end' onClick={() => setActive(!active)}>
                    <Image src={active ? close : hamburger} alt='Opcje'/>
                </button>
            </div>


            {/*render opened mobile NavBar if (active)*/}
            { active && (
                <div
                    className='bg-gradient-to-t from-[#2c5252] via-[#1f4242] to-bg absolute inset-x-0 h-screen
                  z-50 top-20 border-b-8 border-bg flex flex-col items-center pt-16 gap-12'
                >
                    <NavLink href='/dashboard' content='MÓJ UPOS'></NavLink>
                    <NavLink href='//' content={`${role == "student" ? "DLA STUDENTA" : "DLA PRACOWNIKA"}`}/>
                    <NavLink href='//' content='DLA WSZYSTKICH'></NavLink>

                    // TODO: make logout button work
                    <button className='px-10 rounded-lg py-3 border-2 border-[#9AD6D6] text-[#9AD6D6]'>
                        WYLOGUJ
                    </button>
                </div>

            )}

        </nav>
    );
};

export default Navbar;