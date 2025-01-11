import miloszIMG from "@/public/images/milosz.png"
import Image from "next/image";
import Link from "next/link";

const Login = () => {
    return (
        <section className="flex mx-2 max-sm:mx-2 max-lg:flex-col-reverse 3xl:justify-center 3xl:relative 3xl:left-32">
            <div className='max-lg:self-center'>
                <Image className='mix-blend-luminosity 2xl:w-[90%]'
                       src={miloszIMG}
                       alt="Gotowy?"
                />
            </div>
            <div className='lg:relative lg:left-32 max-lg:text-center 3xl:left-16'>
                <h3 className='h3-root'>
                    ZACZNIJ DZIAŁAĆ
                </h3>
                <h2 className='h2-root'>
                    Sprawdź jakie korzyści Cię czekają!
                </h2>
                <div className='mt-6 max-lg:flex max-lg:justify-center max-sm:flex-col max-sm:items-center max-sm:gap-6 max-sm:my-10 mb-8'>
                    <Link href="/login">
                        <button className='button-root bg-[#41748C] mr-10'>
                            ZALOGUJ SIĘ
                        </button>
                    </Link>
                    <button className='button-root border-2 border-[#9AD6D6]'>
                        CZYTAJ DALEJ
                    </button>
                </div>
            </div>
        </section>
    );
};

export default Login;