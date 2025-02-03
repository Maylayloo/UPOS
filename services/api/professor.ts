// (GET) all professors
export const fetchProfessors = async () => {
    try {
        const response = await fetch('http://localhost:8080/professors/', {
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