// (GET) fetch existing final grade by studentId and groupId
export const fetchGrade = async (studentId: number, groupId: number) => {
    try {
        const response = await fetch(`http://localhost:8080/students/${studentId}/grades/NonPartial/${groupId}`, {
            method: 'GET',
            credentials: "include",
        });
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
       return await response.json();
    } catch (err) {
        console.log("No grade for studentId", studentId)
        return null;
    }
}

// method = POST: grade student in the specific group for the first time
// method = PUT: edit student's grade int the specific group
export const gradeStudent = async (studentId: number, groupId: number, selectedGrade: string, gradeId: string, method: string) => {
    console.log("odebrano metode w gradeStudent:", method)
    const urls: Record<string, string> = {
        "POST": "http://localhost:8080/professors/loggedIn/grades",
        "PUT": `http://localhost:8080/professors/loggedIn/grades/${gradeId}`
    }
    const url = urls[method];
    console.log("Skoro metoda to", method, "url to", url)
    try {
        const response = await fetch(url, {
            method: method,
            credentials: "include",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                studentId: studentId,
                groupId: groupId,
                value: selectedGrade,
                isPartial: false,
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