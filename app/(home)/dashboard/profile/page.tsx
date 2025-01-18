'use client'

import React from 'react';
import ProfileField from "@/components/subcomponents/ProfileField";
import {useUser} from "@/app/(context)/UserContext";

const ProfilePage = () => {

    // change localstorage to session storage
    const storedUser = JSON.parse(localStorage.getItem("user") || '{}');

    // table with user Data provided by { user } from backend
    const profileData = [
        {
            title: "Email",
            value: storedUser.email
        },
        {
            title: "Numer Albumu",
            value: storedUser.userId
        },
        {
            title: "Numer Telefonu",
            value: "+48 420 213 769",
            changeable: true,
        },
        {
            title: "Adres",
            value: "31-056 Kraków, ul. Kupa 4 m. 21",
            changeable: true,
        },
        {
            title: "Konto Bankowe",
            value: "69122137721142001123435555",
            changeable: true,
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
                    />
                )))
            }
        </div>
    );
};

export default ProfilePage;