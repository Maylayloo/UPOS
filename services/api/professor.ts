import {apiFetch} from "@/services/api/http";

// (GET) all professors
export const fetchProfessors = async () => {
    return await apiFetch('/professors/')
}

// get prof's full name and scientific title by id
export const fetchProfData = async (profId: Number) => {
    return await apiFetch(`/professors/${profId}/nameAndSurnameAndTitle`)
}
// get logged in prof's full name and title
export const fetchLoggedProfData = async () => {
    return await apiFetch('/professors/loggedIn/nameAndSurnameAndTitle')
}
