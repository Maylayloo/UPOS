import {apiFetch} from "@/services/api/http";

export const fetchUserData = async () => {
    return await apiFetch('/users/loggedIn')
}