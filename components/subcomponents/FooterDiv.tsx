interface FooterProps {
    title: string,
    content: string[];
}

const FooterDiv = ({title, content}: FooterProps) => {
    return (
        <div className='text-center'>
            <h3 className="text-lg font-semibold my-4 font-outfit"> {title} </h3>
            <ul className="space-y-2">
                {
                    content.map((item, index) => (
                        <li key={index} className='text-gray-400 font-roboto'> {item} </li>
                    ))
                }
            </ul>
        </div>
    );
};

export default FooterDiv;