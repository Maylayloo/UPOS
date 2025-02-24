import {apiFetch} from "@/services/api/http";

export const login = async (email: string, password: string) => {
    return await apiFetch("/login", {
        method: "POST",
        body: {
            username: email,
            password: password,
        }
    })
}