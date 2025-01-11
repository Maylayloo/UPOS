'use client'

import {useState} from 'react'

interface profileFieldProps {
    changeable?: boolean,
    title: string,
    value: string,
    id: string,
}

const ProfileField = ({changeable, title, value, id}: profileFieldProps) => {

    const [editable, setEditable] = useState(false);
    const [saveButton, setSaveButton] = useState(false);

    const edit = () => {

        const inp = document.getElementById(id) as HTMLInputElement;
        setEditable(!editable);
        setSaveButton(!saveButton);

        inp.focus();
        inp.value = ""

    }

    const save = () => {
        setEditable(!editable)
        setSaveButton(!saveButton);


        // push to backend there
        // and probably change something to form, and types, you know
    }


    return (
        <div className="w-3/4 border-2 border-[#C1C1C1] rounded-xl relative font-roboto">
            <div className="absolute left-12 -top-3 border-2 border-[#C1C1C1] px-12 bg-bg rounded-lg text-sm">
                { title }
            </div>

            {
                changeable ? (
                    <input
                        id={id}
                        type="text"
                        defaultValue={value}
                        className='bg-bg w-full px-8 py-[1.2rem] text-lg tracking-wide rounded-xl font-[200]'
                        readOnly={!editable}
                    />
                ) : (
                    <input
                        type="text"
                        defaultValue={value}
                        className='bg-bg w-full px-8 py-[1.2rem] text-lg tracking-wide rounded-xl font-[200]'
                        readOnly
                    />
                )
            }


            {changeable && (
                saveButton ? (
                    <button onClick={save}
                            className='uppercase tracking-wider absolute rounded-xl text-bg bg-[#C1C1C1] px-12 py-1 font-bold right-8 -bottom-4 font-outfit'
                    >
                        Zapisz
                    </button>
                ) : (
                    <button onClick={edit}
                            className='uppercase tracking-wider absolute rounded-xl text-bg bg-[#C1C1C1] px-12 py-1 font-bold right-8 -bottom-4 font-outfit'
                    >
                        Zmień
                    </button>
                )

            )}
        </div>
    );
};

export default ProfileField;