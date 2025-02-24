// (GET) fetch existing final grade by studentId and groupId
import {apiFetch} from "@/services/api/http";

export const fetchGrade = async (studentId: number, groupId: number) => {
   return await apiFetch(`/students/${studentId}/grades/NonPartial/${groupId}`)
}

// method = POST: grade student in the specific group for the first time
// method = PUT: edit student's grade in the specific group
export const gradeStudent = async (
    studentId: number, groupId: number, selectedGrade: string, gradeId: string, method: "POST" | "PUT") => {

    const urls: Record<string, string> = {
        "POST": "/professors/loggedIn/grades",
        "PUT": `/professors/loggedIn/grades/${gradeId}`
    }
    const url = urls[method];

    return await apiFetch(url, {
        method: method,
        body: {
            studentId: studentId,
            groupId: groupId,
            value: selectedGrade,
            isPartial: false,
            }
        });
};