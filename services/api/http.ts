const API_URL = "http://localhost:8080";

interface FetchOptions {
    method?: "GET" | "POST" | "PUT" | "DELETE";
    body?: any;
}

export const apiFetch = async <T>(
    endpoint: string,
    { method = "GET", body }: FetchOptions = {}
): Promise<T | null> => {
    try {
        const response = await fetch(`${API_URL}${endpoint}`, {
            method,
            credentials: "include",
            headers: {
                "Content-Type": "application/json",
            },
            body: body ? JSON.stringify(body) : undefined,
        });

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        return await response.json();
    } catch (err) {
        console.error(`API Error (${method} ${endpoint}):`, err);
        return null;
    }
};
