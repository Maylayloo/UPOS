import {footerContent} from "@/constants";
import FooterDiv from "@/components/subcomponents/FooterDiv";

const Footer = () => {
    return (
        <footer className="bg-[#20201F] text-white pt-12 absolute left-0 right-0">
            <div className="container mx-auto px-6 grid grid-cols-1 md:grid-cols-3">
                {
                    footerContent.map((item, index) => (
                        <FooterDiv
                            key={index}
                            title={item.title}
                            content={item.content}
                        />
                    ))
                }


            </div>
            <div className="border-t border-gray-700 mt-8 pt-4 text-center text-gray-500 text-sm">
                © {new Date().getFullYear()} UPOS. Wszelkie prawa zastrzeżone.
            </div>
            <div className='w-full h-2 bg-red-50 mt-4 bg-gradient-to-r from-[#183420] to-[#3C3D1F]'/>
        </footer>

    );
}

export default Footer;