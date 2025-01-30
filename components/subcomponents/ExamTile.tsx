interface props {
    courseId: number,
    attempt: string,
    date: string,
    startHour: string,
    place: string,
}
interface Course {
    courseId: number,
    name: string,
}

const ExamTile = ({courseId, attempt, date, startHour, place}: props) => {

    const currentCourseId = courseId;
    const storedCourses = JSON.parse(localStorage.getItem(`upos_courses`) || '[]');
    let courseName;

    storedCourses.forEach((element: Course) => {
        if (currentCourseId === element.courseId) {
            courseName = element.name;
        }
    })

    return (
        <div className="border font-roboto min-w-max p-4">
            <h1 className='text-lg'>
                {courseName}
            </h1>
            <h2>
                Termin: <span className='font-[300]'>
                {attempt}
            </span>
            </h2>
            <h3>
                Data: <span className='font-[300]'>
                {date} {startHour}
            </span>
                <br/>
                Miejsce: <span className='font-[300]'>
                {place}
            </span>
            </h3>

        </div>
    );
};

export default ExamTile;