import {apiFetch} from "@/services/api/http";

// (POST) get all students by major and semester
export const fetchStudentsByMajorSemester = async (semester: Number, major: string) => {
        return await apiFetch('/students/', {
            method: 'POST',
            body: {
                semester: semester,
                major: major
            }
        });
};

// get full names of students by list of ids
export const getNamesByIds = async (ids: string[]) => {
        return await apiFetch('/students/namesAndSurnames', {
            method: 'POST',
            body: {ids: ids}
        })
}