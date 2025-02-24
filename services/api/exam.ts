import {apiFetch} from "@/services/api/http";

export const fetchExams = async (role: string) => {

    const endpoints: Record<string, string> = {
        "student": "/students/exams",
        "professor": "/professors/exams"
    }

    const url = endpoints[role];

    return await apiFetch(url)
};
