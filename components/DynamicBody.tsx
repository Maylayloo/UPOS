import React from 'react';

const DynamicBody = ({children}: {children: React.ReactNode }) => {

    // TODO: zrób fajnie gradienty w zależności od path.

    return (
        <body className="mx-[157px] max-xl:mx-12 max-sm:mx-8 mt-14 bg-bg text-white overflow-x-hidden">
            {children}
        </body>
    );
};

export default DynamicBody;