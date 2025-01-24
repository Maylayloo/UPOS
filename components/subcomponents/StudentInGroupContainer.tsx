interface dataI {
    index: number,
    name: string,
}



const StudentInGroupContainer = ({index, name}: dataI) => {
    return (
        <div className='border-b border-b-[#DBE3D4] w-full font-roboto tracking-wide font-[300] py-1 flex justify-center'>
            <div className='h-full w-full flex-[4]'></div>
            <h2 className='flex-[5]'>
                {index}. {name}
            </h2>
        </div>
    );
};

export default StudentInGroupContainer;