interface groupsInterface {
    type: string,
    no: number, // group number
    dotw: string, // day of the week
    startHour: string,
    endHour: string,
}

const GroupContainer = ({type, no, dotw, startHour, endHour}: groupsInterface) => {
    return (
        <div className='px-4 flex flex-col py-1 mt-1'>
            <div className='mb-3'>

                <h2 className='font-roboto font-[500]'>
                    &#x2022; {type}, <span className='font-[300]'>
                    grupa nr {no}
                </span>
                </h2>
                <h3 className='px-2.5'>
                    {dotw}, {startHour} - {endHour}
                </h3>
            </div>
        </div>
    );
};

export default GroupContainer;