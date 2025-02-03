import Link from "next/link";
import Image from "next/image";

interface dashboardTileProps {
    leadingColor: string,
    icon: any,
    altIcon: any,
    title: string,
    href: string,
    role: string,
    allowedRoles: string[],
}


const DashboardTile = ({leadingColor, icon, altIcon, title, href, role, allowedRoles}: dashboardTileProps) => {

    if (!allowedRoles.includes(role)) {
        return null;
    }

    return (

            <div className={`min-h-56 min-w-56 h-56 w-56 shrink-0 border-2 rounded-2xl`} style={{borderColor: leadingColor}} >
                <Link href={href}>
                    <div className='h-full w-full flex justify-center items-center flex-col gap-8 pb-8'>
                        <Image
                            src={altIcon && role === "professor" ? altIcon : icon}
                            alt={title}
                            width={80}
                            height={80}/>
                        <h2 className={`uppercase text-2xl font-outfit`} style={{color: leadingColor}}>
                            {title}
                        </h2>
                    </div>
                 </Link>
            </div>



    );
};

export default DashboardTile;