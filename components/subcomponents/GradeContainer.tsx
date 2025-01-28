interface props {
    type: string,
}

const GradeContainer = ({type}: props) => {
    const grade = "-"

    return (
        <div className="flex justify-between items-center ">
            <h2>
                {type}
            </h2>
            <div className={`rounded-full w-[3rem] h-[3rem] relative shadow-md flex justify-center items-center ${grade === "-" ? 'text-2xl' : 'bg-[#67095F]'}`}>
                <h3 className=''>
                    {grade}
                </h3>
            </div>
        </div>
    );
};

export default GradeContainer;