import Link from "next/link";
import Image from "next/image";

interface props {
    href: string,
    icon: any,
    alt: string,
    title: string,
    color: string,
}

const ChoiceButton = ({href, icon, alt, title, color}: props) => {
    return (
        <Link href={href} className='cursor-pointer'>
            <div
                className="border-2 flex items-center px-5 py-3 rounded-2xl gap-4 hover:px-10 hover:shadow-[#3b9ead] shadow-lg"
                style={{borderColor: color, color: color}}
            >
                <Image
                    src={icon}
                    alt={alt}
                    width={48}
                />
                <h1 className='font-roboto text-xl'>
                    {title}
                </h1>
           </div>
        </Link>
    );
};

export default ChoiceButton;