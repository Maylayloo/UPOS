import React from 'react';
import ChoiceButton from "@/components/sections/admin-courses/ChoiceButton";
import createIcon from "@/public/icons/createbutgreen.png"
import manageIcon from "@/public/icons/managebutgreen.png"

const AdminCoursesPage = () => {
    return (
        <div className="flex items-center justify-around flex-col gap-4 mt-8">
            <ChoiceButton
                href="group-management/create"
                icon={createIcon}
                alt="Stwórz"
                title="Stwórz nową grupe zajęciową"
                theme="#2ab86c"
            />
            <ChoiceButton
                href="group-management/manage"
                icon={manageIcon}
                alt="Zarządzaj"
                title="Zarządzaj istniejącymi grupami zajęciowymi"
                theme="#2ab86c"
            />
        </div>
    );
};

export default AdminCoursesPage;