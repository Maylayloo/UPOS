// (POST) create brand-new group as admin
export const postGroup = async (courseId: Number, type: string, groupNumber: Number,
                                dayOfTheWeek: string, startHour: string, endHour: string, place: string, groupSize: Number, professorId: Number, studentsIds: Number[]) => {
    try {
        const response = await fetch('http://localhost:8080/admins/groups', {
            method: 'POST',
            credentials: "include",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                courseId: courseId,
                type: type,
                numberOfGroup: groupNumber,
                dayOfTheWeek: dayOfTheWeek,
                startOfLesson: startHour,
                endOfLesson: endHour,
                place: place,
                maxStudentAmount: groupSize,
                professorId: professorId,
                studentsIds: studentsIds,
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

// GET group data by group id
export const getGroupById = async (groupId: number) => {
    try {
        const response = await fetch(`http://localhost:8080/groups/${groupId}`, {
            method: 'GET',
            credentials: "include",
        })

        if (!response.ok) {
            throw new Error (`HTTP error! status: ${response.status}`);
        }

        return await response.json();
    } catch (err) {
        console.log(err)
        return null;
    }
}

// get all groups from specific course (by courseId) in which logged-in user is a member
export const getGroupsByCourseId = async (courseId: string) => {
    try {
        const response = await fetch(`http://localhost:8080/groups/loggedIn/${courseId}`, {
            method: 'GET',
            credentials: 'include',
        })

        if (!response.ok) {
            throw new Error (`HTTP error! status: ${response.status}`);
        }

        return await response.json();

    } catch (err) {
        console.log("Problem: ", err)
        return null;
    }

}