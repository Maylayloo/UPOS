"use client"

import Image from "next/image";
import aghIMG from "@/public/icons/agh.png"
import userIMG from "@/public/icons/user.png"
import passIMG from "@/public/icons/padlock.png"
import logIMG from "@/public/icons/log-in.png"
import infoIMG from "@/public/icons/info.png"
import LoginInput from "@/components/utils/auth/LoginInput";
import LoginButton from "@/components/utils/auth/LoginButton";

import {FormEvent} from 'react'
import {useRouter} from "next/navigation";
import Link from "next/link";
import {useUser} from "@/app/(context)/UserContext";
import {login} from "@/services/api/auth";
import {fetchUserData} from "@/services/api/user";
import {fetchLoggedProfData} from "@/services/api/professor";

const LoginPage = () => {

    const router = useRouter()
    // useUser() hook
    const { setUser }: any = useUser();


    // function for handling form, fetching data and log in user
    async function handleSubmit(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();

        // get data from form
        const formData = new FormData(event.currentTarget);
        const email = formData.get('email');
        const password = formData.get('password');

        // notification pushed when form isn't filled
        if (!email || !password) {
            alert('Wprowadź email i hasło.');
            return;
        }

        // login and fetch data
        try {
            const loggedIn = await login(email.toString(), password.toString())
            if (!loggedIn) return;

            const user = await fetchUserData();
            if (!user) return;

            // save user data to localStorage
            localStorage.setItem('upos_user', JSON.stringify(user));
            localStorage.setItem('upos_user_role', JSON.stringify(user.role.toLowerCase()))

            if (user.role.toLowerCase() === "professor") {
                const profData = await fetchLoggedProfData()
                    if (profData) {
                        localStorage.setItem('upos_prof_title', JSON.stringify(profData.title));
                    }
                }
            if (user.role.toLowerCase() !== "admin") {
                const response = await fetch("http://localhost:8080/courses/loggedIn", {
                    method: "GET",
                    credentials: "include"
                })
                if (!response.ok) throw new Error("Błąd podczas pobierania danych")

                const coursesData = await response.json();
                localStorage.setItem('upos_courses', JSON.stringify(coursesData));
            }

                // push logged user to /dashboard
                router.push('/dashboard');

        } catch (error) {
            console.error('Błąd logowania:', error);
            alert('Błąd logowania');
        }
    }
    return (
        <div className='h-[85svh] text-black font-outfit'>
            <div className='absolute inset-0 bg-lgbg '>
                <div className='bg-black w-full h-22 px-[125px] max-xl:px-12 max-sm:px-8 flex items-center gap-4'>
                    <Image alt='AGH' src={aghIMG}/>
                    <h1 className='text-white font-roboto font-[500] text-xl'>
                        Logowanie do Systemów AGH
                    </h1>
                </div>
                <div className='w-full h-full px-[80px] max-xl:px-12 max-sm:px-8'>
                    <p className='font-roboto text-center py-6 text-[0.95rem]'>
                        SSO AGH (Single Sign-On) to mechanizm upraszczający procedury logowania w systemach
                        informatycznych.
                        Pozwala użytkownikowi, po jednokrotnym uwierzytelnieniu, na dostęp do wielu usług i aplikacji
                        obsługiwanych przez CRI
                        oraz inne jednostki AGH. Usługi, do których uwierzytelnienie następuje przez SSO AGH to m.in.
                        UPOS, Microsoft 365, USOS, UPeL,
                        Chmura AGH, Overleaf.
                    </p>

                    <div
                        className='bg-[#FFF3CD] w-full h-14 border-[#FFEEBA] border-2 rounded-[0.25rem] px-6 flex items-center mb-6'>
                        <p className='font-roboto text-[#B87804]'>
                            Wymagane uwierzytelnienie - zaloguj się używając adresu e-mail oraz hasła z Poczty AGH.
                        </p>
                    </div>
                    <div
                        className='w-full h-64 bg-[#FDFDFD] flex justify-center border-2 border-[#DDDDDD] rounded-[0.25rem] items-center flex-col mb-6'>
                        <form onSubmit={handleSubmit} className='flex flex-col items-center'>
                            <LoginInput name="email" type="email" image={userIMG} alt="Username" placeholder="Nazwa użytkownika (email)"/>
                            <LoginInput name="password" type="password" image={passIMG} alt="Password" placeholder="Hasło"/>
                            <LoginButton type='submit' text="Zaloguj się" image={logIMG} alt="login"
                                         className='mb-4 bg-[#28A745]'/>
                        </form>
                        <div className='flex gap-2'>
                            <Link href=''>
                                <LoginButton type='button' text="Zresetuj hasło" image={infoIMG} alt="login"
                                             className='bg-[#6C757D]'/>
                            </Link>
                            <LoginButton type='button' text="Pierwsze logowanie" image={infoIMG} alt="login"
                                         className='bg-[#6C757D]'/>
                        </div>
                    </div>

                    <div
                        className='bg-[#D1ECF1] w-full h-14 border-[#BEE5EB] border-2 rounded-[0.25rem] px-6 flex items-center mb-6'>
                        <p className='font-roboto text-[#0C5460]'>
                            Zwiększ bezpieczeństwo swojego konta aktywują uwierzytelnianie dwuskładnikowe
                        </p>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default LoginPage;