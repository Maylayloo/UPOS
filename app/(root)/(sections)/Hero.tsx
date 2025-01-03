import Image from "next/image";
import dawidIMG from "@/public/images/dawid.png"

const Hero = () => {
    return (
        <section className='flex mx-10 max-lg:flex-col lg:items-center'>
            <div className="font-outfit flex-[2]">
                <h2 className='text-6xl max-md:text-4xl max-lg:w-full font-bold leading-[5rem] w-[75%]'>
                    Witamy w Serwisie UPOS!
                </h2>
                <p className='text-xl max-md:text-lg w-[50%] mt-4 max-lg:w-full max-xl:mb-8'>
                    Weź studia w swoje ręce! Zarządzaj planem zajęć, sprawdzaj oceny,
                    zapisuj się na przedmioty, obstawiaj punkty ECTS, przekupuj prowadzących, pnij się w rankingach!
                </p>
            </div>
            <div className='flex-1'>
                <Image className='w-full mix-blend-luminosity'
                    src={dawidIMG}
                    alt="Witamy!"
                />

            </div>
        </section>
    );
};

export default Hero;