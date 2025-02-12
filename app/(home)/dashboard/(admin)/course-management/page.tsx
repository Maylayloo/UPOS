import React from 'react';
import ChoiceButton from "@/components/sections/admin-courses/ChoiceButton";
import createIcon from "@/public/icons/create.png"
import manageIcon from "@/public/icons/manage.png"

const AdminCoursesPage = () => {
    return (
        <div className="flex items-center justify-around flex-col gap-4 mt-8">
            <ChoiceButton
                href="course-management/create"
                icon={createIcon}
                alt="Stwórz"
                title="Stwórz nowy przedmiot"
                theme="#3b9ead"
            />
            <ChoiceButton
                href="course-management/manage"
                icon={manageIcon}
                alt="Zarządzaj"
                title="Zarządzaj istniejącymi przedmiotami"
                theme="#3b9ead"
            />
        </div>
    );
};

export default AdminCoursesPage;