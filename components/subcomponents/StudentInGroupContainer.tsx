import { useState } from "react";

interface Props {
    index: number;
    name: string;
    groupId: number;
    studentId: number;
}

const StudentInGroupContainer = ({ index, name, groupId, studentId}: Props) => {
    const storedUser = JSON.parse(localStorage.getItem("upos_user") || "{}");
    const role = storedUser.role.toLowerCase();
    const grades = ["2.0", "3.0", "3.5", "4.0", "4.5", "5.0"];

    const [activeGrade, setActiveGrade] = useState(false);
    const [selectedGrade, setSelectedGrade] = useState(grades[0]);
    const [successfullyGraded, setSuccessfullyGraded] = useState(false);

    // Funkcja do wysyłania POST
    const handleSubmit = async () => {
        try {
            const response = await fetch("urlapi", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    studentId: studentId,
                    groupId: groupId,
                    value: selectedGrade,
                    isPartial: false,
                }),
            });

            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            console.log("Ocena wysłana pomyślnie!");
            setActiveGrade(false); // Ukryj select po zatwierdzeniu
            setSuccessfullyGraded(true)
        } catch (error) {
            console.error("Błąd podczas wysyłania oceny:", error);
        }
    };

    if (role === "student") {
        return (
            <div className="border-b border-b-[#DBE3D4] w-full font-roboto tracking-wide font-[300] py-1 flex justify-center">
                <div className="h-full w-full flex-[4]"></div>
                <h2 className="flex-[5]">
                    {index}. {name}
                </h2>
            </div>
        );
    }

    return (
        <div className="border-b border-b-[#DBE3D4] w-full font-roboto tracking-wide font-[300] py-1 flex justify-center gap-16 items-center">
            <h2>
                {index}. {name}
            </h2>
            <div>
                {!activeGrade && (
                    <button
                        onClick={() => setActiveGrade(true)}
                        className="bg-[#DBE3D4] text-bg font-[500] font-roboto my-1 px-3 rounded-xl hover:bg-bg hover:text-[#DBE3D4] border-2 border-bg hover:border-[#DBE3D4]"
                    >
                        OCEŃ
                    </button>
                )}

                {activeGrade && (
                    <div className="flex items-center gap-4">
                        <select
                            name="grade"
                            className="text-black font-roboto px-2 font-[400]"
                            value={selectedGrade}
                            onChange={(e) => setSelectedGrade(e.target.value)} // Aktualizuj stan
                        >
                            {grades.map((grade) => (
                                <option key={grade} value={grade}>
                                    {grade}
                                </option>
                            ))}
                        </select>
                        <button
                            onClick={handleSubmit}
                            className="bg-[#DBE3D4] text-bg font-[500] font-roboto my-1 px-3 rounded-xl hover:bg-bg hover:text-[#DBE3D4] border-2 border-bg hover:border-[#DBE3D4]"
                        >
                            ZATWIERDŹ
                        </button>
                    </div>
                )}
                {
                    successfullyGraded && (
                        <h1 className="text-lg text-green-400">
                            Oceniono pomyślnie
                        </h1>
                    )
                }
            </div>
        </div>
    );
};

export default StudentInGroupContainer;
