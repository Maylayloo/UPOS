import Image from "next/image";

interface loginButtonProps {
    text: string,
    type: "button" | "submit" | "reset",
    image: any,
    alt: string,
    className: string,
}


const LoginButton = ({text, type, image, alt, className}: loginButtonProps) => {
    return (
        <button type={type} className={`${className} font-roboto text-white flex px-6 py-[0.65rem] gap-3 rounded-lg items-center`}>
            <Image src={image} alt={alt} width={18} height={18}/>
            { text }
        </button>
    );
};

export default LoginButton;