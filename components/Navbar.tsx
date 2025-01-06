import Link from "next/link";

const Navbar = () => {

    // add get_role in the future
    const role = "student"
    // add get_name in the future
    const name = "Jan Kowalski"

    return (
        <nav className="absolute inset-x-0 top-0 h-[5.5rem] flex items-end font-outfit">
            <div className='absolute right-0 top-0 text-sm'>
                <h4 className='font-roboto'>
                    Zalogowano jako:
                    <span className='px-3'>
                        {name}
                    </span>
                    <button className='mr-4 px-6 rounded-lg py-1 my-2 border border-[#9AD6D6] text-[#9AD6D6]'>
                        WYLOGUJ
                    </button>
                </h4>
            </div>
            <div className="flex-1 px-16">
                <h1 className='text-[36px] font-[500]'>
                    UPOS
                </h1>
            </div>
            <div className='flex-[2] flex items-center justify-evenly px-12'>
                <Link href='/dashboard' className='navLink'>
                    MÓJ UPOS
                </Link>
                <Link href='/' className='navLink'>
                    { role === "student" ? "DLA STUDENTA" : "DLA PRACOWNIKA"}
                </Link>
                <Link href='/' className='navLink'>
                    DLA WSZYSTKICH
                </Link>
            </div>

        </nav>
    );
};

export default Navbar;