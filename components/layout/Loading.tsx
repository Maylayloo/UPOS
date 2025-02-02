import React from 'react';
import Image from "next/image";
import dawidIMG from "@/public/images/dawid.png";

const Loading = () => {
    return (
        <div>
            <Image src={dawidIMG} alt="LOADING" />
        </div>
    );
};

export default Loading;