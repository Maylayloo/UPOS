import Image from "next/image";
import dawidIMG from "@/public/images/dawid.png"

const Hero = () => {
    return (
        <section className='flex mx-10 max-sm:mx-2 max-lg:flex-col lg:items-center'>
            <div className="font-outfit flex-[2]">
                <h2 className='h2-root'>
                    Witamy w Serwisie UPOS!
                </h2>
                <p className='p-root'>
                    Weź studia w swoje ręce! Zarządzaj planem zajęć, sprawdzaj oceny,
                    zapisuj się na przedmioty, obstawiaj punkty ECTS, przekupuj prowadzących, pnij się w rankingach!
                </p>
            </div>
            <div className='flex-1 max-lg:flex max-lg:justify-center'>
                <Image className='mix-blend-luminosity'
                    src={dawidIMG}
                    alt="Witamy!"
                />

            </div>
        </section>
    );
};

export default Hero;