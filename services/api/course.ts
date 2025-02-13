// (POST) create brand-new course as admin
export const postCourse = async (courseName: string, ects: number, profId: number, semester: number, major: string) => {
    try {
        const response = await fetch('http://localhost:8080/admins/courses', {
            method: 'POST',
            credentials: "include",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                name: courseName,
                ects: ects,
                professorId: profId,
                semester: semester,
                major: major
            }),
        });

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return 1;

    } catch (error) {
        console.error("Error grading student: ", error);
        return null;
    }
};

// (POST) get all courses by major and semester
export const fetchCoursesByMajorSemester = async (major: string, semester: string) => {
    try {
        const response = await fetch('http://localhost:8080/courses/majorsAndSemesters', {
            method: 'POST',
            credentials: "include",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                major: major,
                semester: semester
            }),
        });

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return await response.json();

    } catch (error) {
        console.error("Error grading student: ", error);
        return null;
    }
};