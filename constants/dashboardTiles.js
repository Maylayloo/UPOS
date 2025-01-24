import user from "@/public/icons/profile.png"
import schedule from "@/public/icons/schedule.png"
import stats from "@/public/icons/stats.png"
import grades from "@/public/icons/grade.png"
import groups from "@/public/icons/groups.png"
import roulette from "@/public/icons/roulette.png"
import exams from "@/public/icons/exams.png"

export const dashboardContent = [
    {
        leadingColor: "#377504",
        icon: user,
        title: "moje dane",
        href: "dashboard/profile",
        allowedRoles: ["student", "prof"],
    },
    {
        leadingColor: "#077298",
        icon: schedule,
        title: "plan zajęć",
        href: "dashboard/schedule",
        allowedRoles: ["student", "prof"],
    },
    {
        leadingColor: "#969B00",
        icon: stats,
        title: "statystyki",
        href: "dashboard/statistics",
        allowedRoles: ["student", "prof"],
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
        allowedRoles: ["student", "prof"],
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
        title: "Egzaminy",
        href: "dashboard/exams",
        allowedRoles: ["student"],
    },

]