import Hero from "@/app/(root)/(sections)/Hero";
import Login from "@/app/(root)/(sections)/Login";
import Onboarding from "@/app/(root)/(sections)/Onboarding";

const Root = () => {
    return (
        <section className=''>
            <h1 className='text-[36px] font-outfit font-semibold'>
                UPOS
            </h1>

            <section className='w-full mt-[5rem] font-outfit'>
                <Hero/>
            </section>

            <section className='w-full font-outfit mt-[7rem]'>
                <Login/>
            </section>

            <section className='w-full mt-16'>
                <Onboarding/>
            </section>


        </section>
    )
}

export default Root;