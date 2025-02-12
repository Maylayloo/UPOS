import ChoiceButton from "@/components/sections/admin-courses/ChoiceButton";
import createIcon from "@/public/icons/create.png"
import previousIcon from "@/public/icons/previous.png"

const Page = () => {
    return (
        <div className="text-center flex items-center flex-col">
            <h1 className='text-xl font-roboto'>
                Udało Ci się pomyślnie utworzyć nowy przedmiot!
            </h1>
            <div className="w-full flex flex-col items-center gap-4 mt-8">
                <ChoiceButton
                    title="Tworzę kolejny"
                    icon={createIcon}
                    alt="Stwórz"
                    href='.'
                    theme="#3b9ead"
                />
                <ChoiceButton
                    title="Powrót"
                    icon={previousIcon}
                    alt="Powrót"
                    href='/dashboard'
                    theme="#3b9ead"
                />
            </div>
        </div>
    );
};

export default Page;