'use client'

import React from 'react';
import ProfileField from "@/components/subcomponents/ProfileField";
import {useUser} from "@/app/(context)/UserContext";

const ProfilePage = () => {

    // change localstorage to session storage
    const storedUser = JSON.parse(localStorage.getItem("upos_user") || '{}');
    const profTitle = JSON.parse(localStorage.getItem("upos_prof_title") as string);

    // table with user Data provided by { user } from backend
    const profileData = [
        {
            title: "Tytuł Naukowy",
            value: profTitle,
            visibility: ["professor"]
        },
        {
            title: "Email",
            value: storedUser.email,
            visibility: ["professor", "student"]
        },
        {
            title: "Numer Albumu",
            value: storedUser.userId,
            visibility: ["student"]
        },
        {
            title: "Numer Telefonu",
            value: storedUser.number,
            changeable: true,
            visibility: ["professor", "student"]
        },
        {
            title: "Adres",
            value: storedUser.address,
            changeable: true,
            visibility: ["professor", "student"]
        },
        {
            title: "Konto Bankowe",
            value: storedUser.bankNumber,
            changeable: true,
            visibility: ["professor", "student"]
        },

    ]

    return (
        <div className='w-full flex flex-col items-center gap-12 pb-8'>
            {
                // mapping over profileData table and generating ProfileField components
                profileData.map(((item, index) => (
                    <ProfileField
                        key={item.title}
                        id={String(index)}
                        title={item.title}
                        value={item.value}
                        {...(item.changeable ? {changeable: true} : {})}
                        visibility={item.visibility}
                    />
                )))
            }
        </div>
    );
};

export default ProfilePage;