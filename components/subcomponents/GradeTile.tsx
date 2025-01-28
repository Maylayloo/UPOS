import GradeContainer from "@/components/subcomponents/GradeContainer";

const GradeTile = () => {
    return (
        <div className='font-roboto px-6 pt-4 pb-8 w-[27.5%] border rounded-xl'>
            <h1 className='text-lg'>
                Metody Numeryczne
            </h1>
            <span className='font-[300]'>
                punkty ECTS: 4.00
            </span>
            <div className='flex flex-col gap-4 mt-2'>
                <GradeContainer type='Przedmiot' grade='3.5'/>
                <GradeContainer type='wykład' grade='3'/>
                <GradeContainer type='ćwiczenia projektowe' grade='-'/>

            </div>
        </div>
    );
};

export default GradeTile;