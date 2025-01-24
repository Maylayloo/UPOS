import Image from 'next/image'
import statsPNG from '@/public/images/statm.jpg'

const StatsPage = () => {
    return (
        <div className='flex justify-center mb-8'>
            <Image
                className='rounded-xl border border-yellow-500 shadow-yellow-600 shadow-2xl  '
                src={statsPNG}
                alt="Statystyki"
                width={400}
            />
        </div>
    );
};

export default StatsPage;