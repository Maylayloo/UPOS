import Link from "next/link";

interface groupsInterface {
    type: string,
    no: number, // group number
    dotw: string, // day of the week
    startHour: string,
    endHour: string,
    courseName: string,
    groupId: number
}

const GroupContainer = ({type, no, dotw, startHour, endHour, courseName, groupId}: groupsInterface) => {
    return (
        <div className='px-4 flex flex-col py-1 mt-1'>
            <div>
                <Link
                    href={`/dashboard/groups/${courseName}/${type}/nr_grupy=${no}_&id=${groupId}`}
                    className='hover:underline'
                >
                    <h2 className='font-roboto font-[500]'>
                        &#x2022; {type}, <span className='font-[300]'>
                    grupa nr {no}
                </span>
                    </h2>
                </Link>
                <h3 className='px-2.5'>
                    {dotw}, {startHour} - {endHour}
                </h3>
            </div>
        </div>
    );
};

export default GroupContainer;