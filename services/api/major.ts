// (GET) all majors
export const fetchMajors = async () => {
    try {
        const response = await fetch('http://localhost:8080/admins/majors', {
            method: 'GET',
            credentials: "include",
        });
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        return await response.json();
    } catch (err) {
        console.log("problem, ", err)
        return null;
    }
}