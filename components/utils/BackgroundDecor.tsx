import React from 'react';

interface bgdProps {
    top: number,
    left: number,
    background: string,
    hidden: boolean,
}

const BackgroundDecor = ({top, left, background, hidden}: bgdProps) => {
    return (
        <div className={`w-[600px] h-[600px] rounded-full absolute -z-10 opacity-60 blur-[100px] ${hidden ? "max-xl:hidden" : ""}` }
        style={{
            top: top,
            left: left,
            background: background,
        }}>
            
        </div>
    );
};

export default BackgroundDecor;