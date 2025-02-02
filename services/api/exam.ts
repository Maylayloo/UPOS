export const fetchExams = async (role: string) => {

    const urls: Record<string, string> = {
        "student": "http://localhost:8080/students/exams",
        "professor": "http://localhost:8080/professors/exams"
    }

    const url = urls[role];

    try {
        const response = await fetch(url, {
            method: "GET",
            credentials: "include",
        });
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        return await response.json();

    } catch (err) {
        console.error("Error fetching data:", err);
        return null;
    }
};