interface profileFieldProps {
    changeable?: boolean,
}

const ProfileField = ({changeable}: profileFieldProps) => {

    return (
        <div className="w-3/4 border-2 border-[#C1C1C1] rounded-xl relative font-roboto">
            <div className="absolute left-12 -top-3 border-2 border-[#C1C1C1] px-12 bg-bg rounded-lg text-sm">
                Email
            </div>
            <input
                type="text"
                value="jkowalski@student.agh.edu.pl"
                className='bg-bg w-full px-8 py-[1.2rem] text-lg tracking-wide rounded-xl font-[200]'
                readOnly
            />

            {changeable && (
                <div className='uppercase tracking-wider absolute rounded-xl text-bg bg-[#C1C1C1] px-12 py-1 font-bold right-8 -bottom-4 cursor-pointer font-outfit'>
                    Zmień
                </div>
            )}
        </div>
    );
};

export default ProfileField;