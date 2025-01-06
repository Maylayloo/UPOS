import user from "@/public/icons/profile.png"
import schedule from "@/public/icons/schedule.png"
import stats from "@/public/icons/stats.png"
import grades from "@/public/icons/grade.png"
import groups from "@/public/icons/groups.png"
import roulette from "@/public/icons/roulette.png"

export const dashboardContent = [
    {
        leadingColor: "#377504",
        icon: user,
        title: "moje dane",
        href: "/",
        allowedRoles: ["student", "prof"],
    },
    {
        leadingColor: "#077298",
        icon: schedule,
        title: "plan zajęć",
        href: "/",
        allowedRoles: ["student", "prof"],
    },
    {
        leadingColor: "#969B00",
        icon: stats,
        title: "statystyki",
        href: "/",
        allowedRoles: ["student", "prof"],
    },
    {
        leadingColor: "#8C0BA2",
        icon: grades,
        title: "oceny",
        href: "/",
        allowedRoles: ["student"],
    },
    {
        leadingColor: "#B0BEC4",
        icon: groups,
        title: "grupy",
        href: "/",
        allowedRoles: ["student", "prof"],
    },
    {
        leadingColor: "#951818",
        icon: roulette,
        title: "ruletka",
        href: "/",
        allowedRoles: ["student"],
    },

]