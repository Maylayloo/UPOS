// (POST) get all students by major and semester
export const fetchStudentsByMajorSemester = async (semester: Number, major: string) => {
    try {
        const response = await fetch('http://localhost:8080/students/', {
            method: 'POST',
            credentials: "include",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                semester: semester,
                major: major
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