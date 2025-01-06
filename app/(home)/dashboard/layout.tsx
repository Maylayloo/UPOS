import React from 'react';

const DashboardLayout = ({student, prof, }: {
    student: React.ReactNode;
    prof: React.ReactNode;
}) => {

    const role = "student"

    return (
        <div>
          {
              role === "student" ? student : prof
          }
        </div>
    );
};

export default DashboardLayout;