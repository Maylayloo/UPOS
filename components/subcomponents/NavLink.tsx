"use client"

import Link from "next/link";
import {usePathname} from "next/navigation";

interface navLinkProps {
    href: string,
    content: string
}

const NavLink = ({href, content}: navLinkProps) => {

    const path = usePathname();

    return (
        <Link href={href} className={`navLink ${path.startsWith(href) ? " font-bold underline text-[#9AD6D6] underline-[#9AD6D6]" : "font-[300]"}`}>
            {content}
        </Link>
    );
};

export default NavLink;