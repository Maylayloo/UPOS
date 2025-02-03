import Image from "next/image";

interface loginInputProps {
    type: string,
    name: string,
    image: any,
    alt: string,
    placeholder: string,
}

const LoginInput = ({type, name, image, alt, placeholder}: loginInputProps) => {
    return (
        <div className='border border-[#CED4DA] flex rounded-[0.5rem] mb-4 w-[125%]'>
            <div className='flex items-center justify-between border-[#CED4DA] border-r p-[0.8rem] bg-[#E9ECEF] rounded-l-[0.45rem]'>
                <Image src={image} alt={alt} width={16} height={16}/>
            </div>
            <input type={type} name={name} placeholder={placeholder}
                   className='w-full px-4 rounded-r-[0.5rem] text-[#19191a] focus:outline-none focus:border-cyan-300 focus:border focus:shadow-2xl'
            />
        </div>
    );
};

export default LoginInput;