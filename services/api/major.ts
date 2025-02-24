// (GET) all majors
import {apiFetch} from "@/services/api/http";

export const fetchMajors = async () => {
   return await apiFetch("admins/majors")
}