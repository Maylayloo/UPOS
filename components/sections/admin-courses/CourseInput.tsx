interface props {
    title: string,
    placeholder: string,
    width?: string,
}

const CourseInput = ({title, placeholder, width}: props) => {
    return (
        <div className={`flex flex-col items-center w-[${width}]`}>
            <h2 className='mb-1 font-roboto '>
                {title}
            </h2>
            <input
                className='text-black text-lg focus:outline-none px-5 py-2 rounded-xl w-full focus:bg-blue-100 placeholder:text-base'
                type="text"
                placeholder={`np. ${placeholder}`}
            />
        </div>
    );
};

export default CourseInput;