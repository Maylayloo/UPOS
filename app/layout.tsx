import "./globals.css";

import type {Metadata} from "next";
import React from "react";
import Footer from "@/components/Footer";

import { Outfit, Roboto } from "next/font/google"

const outfit = Outfit({ subsets: ['latin'], variable: '--font-outfit' });
const roboto = Roboto({
    subsets: ['latin'],
    weight: ['300', '400', '500'],
    variable: '--font-roboto',
});

export const metadata: Metadata = {
    title: "UPOS",
    description: "Big Bang Gang",
};

export default function RootLayout({
                                       children,
                                   }: Readonly<{
    children: React.ReactNode;
}>) {
    return (
        <html lang="en" className={`${outfit.variable} ${roboto.variable}`}>
            <body className='mx-[157px] max-xl:mx-12 max-sm:mx-8 mt-14 bg-bg text-white h-screen'>
                {children}

                <Footer/>
            </body>


        </html>
    );
}
