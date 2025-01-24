import React from 'react';

interface infoI {
    title: string,
    content: string
}

const GroupInfoContainer = ({title, content}: infoI) => {
    return (
        <div className='w-full flex justify-center border-b border-b-[#DBE3D4] py-4 flex-col items-center text-roboto'>
            <div className='text-xl font-roboto tracking-wide'>
                {title}
            </div>
            <div className=''>
                {content}
            </div>
        </div>
    );
};

export default GroupInfoContainer;