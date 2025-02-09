import user from "@/public/icons/profile.png"
import schedule from "@/public/icons/schedule.png"
import stats from "@/public/icons/stats.png"
import grades from "@/public/icons/grade.png"
import groups from "@/public/icons/groups.png"
import roulette from "@/public/icons/roulette.png"
import exams from "@/public/icons/exams.png"
import altExams from "@/public/icons/altExams.png"
import courses from "@/public/icons/courses.png"
import manageGroups from "@/public/icons/network.png"

export const dashboardContent = [
    {
        leadingColor: "#377504",
        icon: user,
        title: "moje dane",
        href: "dashboard/profile",
        allowedRoles: ["student", "professor"],
    },
    {
        leadingColor: "#077298",
        icon: schedule,
        title: "plan zajęć",
        href: "dashboard/schedule",
        allowedRoles: ["student", "professor"],
    },
    {
        leadingColor: "#969B00",
        icon: stats,
        title: "statystyki",
        href: "dashboard/statistics",
        allowedRoles: ["student", "professor"],
    },
    {
        leadingColor: "#8C0BA2",
        icon: grades,
        title: "oceny",
        href: "dashboard/grades",
        allowedRoles: ["student"],
    },
    {
        leadingColor: "#B0BEC4",
        icon: groups,
        title: "grupy",
        href: "dashboard/groups",
        allowedRoles: ["student", "professor"],
    },
    {
        leadingColor: "#951818",
        icon: roulette,
        title: "ruletka",
        href: "dashboard/roulette",
        allowedRoles: ["student"],
    },
    {
        leadingColor: "#ee7430",
        icon: exams,
        altIcon: altExams,
        title: "Egzaminy",
        href: "dashboard/exams",
        allowedRoles: ["student", "professor"],
    },
    {
        leadingColor: "#3b9ead",
        icon: courses,
        title: "Przedmioty",
        href: "dashboard/course-management",
        allowedRoles: ["admin"],

    },
    {
        leadingColor: "#2ab86c",
        icon: manageGroups,
        title: "Grupy",
        href: "dashboard/group-management",
        allowedRoles: ["admin"],

    },


]