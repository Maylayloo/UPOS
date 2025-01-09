import React from 'react';
import ProfileField from "@/components/subcomponents/ProfileField";
import {profileData} from "@/constants/userProfileData";

const ProfilePage = () => {
    return (
        <div className='w-full flex flex-col items-center gap-12'>
            {
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