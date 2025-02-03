import Link from "next/link";
import Image from "next/image";

interface props {
    href: string,
    icon: any,
    alt: string,
    title: string,
}

const ChoiceButton = ({href, icon, alt, title}: props) => {
    return (
        <Link href={href} className='cursor-pointer'>
            <div className='border-2 border-[#3b9ead] text-[#3b9ead] flex items-center px-5 py-3 rounded-2xl gap-4 hover:px-10 hover:shadow-[#3b9ead] shadow-lg'>
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