import {apiFetch} from "@/services/api/http";

// (POST) create brand-new course as admin
export const postCourse = async (courseName: string, ects: number, profId: number, semester: number, major: string) => {
        return await apiFetch('/admins/courses', {
            method: 'POST',
            body: {
                name: courseName,
                ects: ects,
                professorId: profId,
                semester: semester,
                major: major
            }
        })
};

// (POST) get all courses by major and semester
export const fetchCoursesByMajorSemester = async (major: string, semester: string) => {
        return await apiFetch('/courses/majorsAndSemesters', {
            method: 'POST',
            body: {
                major: major,
                semester: semester
            }
        });
};