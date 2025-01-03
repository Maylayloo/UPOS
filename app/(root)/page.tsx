import Hero from "@/app/(root)/(sections)/Hero";
import Login from "@/app/(root)/(sections)/Login";
import Onboarding from "@/app/(root)/(sections)/Onboarding";

const Root = () => {
    return (
        <section className=''>
            <h1 className='text-[36px] font-outfit'>
                UPOS
            </h1>

            <Hero/>
            <Login/>
            <Onboarding/>


        </section>
    )
}

export default Root;