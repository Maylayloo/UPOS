// (POST) create brand-new group as admin
import {apiFetch} from "@/services/api/http";

export const postGroup = async (courseId: Number, type: string, groupNumber: Number,
                                dayOfTheWeek: string, startHour: string, endHour: string, place: string, groupSize: Number, professorId: Number, studentsIds: Number[]) => {

        return await apiFetch('/admins/groups', {
            method: 'POST',
            body: {
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
            }
        });
};

// GET group data by group id
export const getGroupById = async (groupId: number) => {
    return await apiFetch(`/groups/${groupId}`);
}

// get all groups from specific course (by courseId) in which logged-in user is a member
export const getGroupsByCourseId = async (courseId: string) => {
    return await apiFetch(`/groups/loggedIn/${courseId}`)
}