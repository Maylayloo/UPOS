import Image from "next/image";
import pawelIMG from "@/public/images/pawel.png";

const Onboarding = () => {
    return (
        <section className='flex mx-10 max-sm:mx-2 max-lg:flex-col lg:items-center mb-24'>
            <div className="font-outfit flex-[2]">
                <h3 className='h3-root max-sm:hidden'>
                    Kolejny warunek na horyzoncie?
                </h3>
                <h3 className='h3-root sm:hidden'>
                    Kolejny warunek?
                </h3>
                <h2 className='h2-root lg:whitespace-nowrap mb-6'>
                    Z nami to nie problem!
                </h2>
                <h3 className='h3-root'>
                    Ale jak zacząć?
                </h3>
                <p className='p-root'>
                    Nasza platforma dedykowana jest dla studentów i pracowników Akademii Górniczo-Hutniczej w Krakowie.
                    Jeśli Twoje konto nie zostało automatycznie utworzone,
                    zwróć się do dziekanatu za pomocą poczty. Jeśli nie jesteś studentem - nic straconego! Powodzenia w
                    rekrutacji!
                </p>
            </div>
            <div className='flex-1 max-lg:flex max-lg:justify-center'>
                <Image className='mix-blend-luminosity w-[90%] 3xl:w-[75%]'
                       src={pawelIMG}
                       alt="Witamy!"
                />

            </div>
        </section>
    );
};

export default Onboarding;