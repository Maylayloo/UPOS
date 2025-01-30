"use client";

import { useRouter } from "next/navigation";

const LogoutButton = () => {
    const router = useRouter();

    const handleLogout = () => {

        Object.keys(localStorage).forEach((key) => {
            if (key.startsWith('upos_')) {
                localStorage.removeItem(key);
            }
        });

        router.push("/login");
    };

    return (
        <button
            onClick={handleLogout}
            className="mr-4 px-6 rounded-lg py-1 my-2 border border-[#9AD6D6] text-[#9AD6D6]"
        >
            WYLOGUJ
        </button>
    );
};

export default LogoutButton;
