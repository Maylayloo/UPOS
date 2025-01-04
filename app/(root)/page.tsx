import Hero from "@/app/(root)/(sections)/Hero";
import Login from "@/app/(root)/(sections)/Login";
import Onboarding from "@/app/(root)/(sections)/Onboarding";
import BackgroundDecor from "@/components/subcomponents/BackgroundDecor";

const Root = () => {
    return (
        <section className=''>
            <h1 className='text-[36px] font-outfit font-semibold'>
                UPOS
            </h1>


            <BackgroundDecor top={-250} left={175} background="#2F7697" hidden={true}/>
            <BackgroundDecor top={200} left={1200} background="#6E437A" hidden={true}/>
            <BackgroundDecor top={600} left={-150} background="#464820" hidden={false}/>
            <BackgroundDecor top={800} left={1000} background="#173B20" hidden={true}/>

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