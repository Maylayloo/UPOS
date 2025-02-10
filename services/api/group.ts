// (POST) create brand-new group as admin
import {start} from "node:repl";

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