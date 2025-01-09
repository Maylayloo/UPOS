import "./globals.css";

import type {Metadata} from "next";
import React from "react";
import Footer from "@/components/Footer";

import { Outfit, Roboto } from "next/font/google"
import DynamicBody from "@/components/DynamicBody";

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

export default function RootLayout({ children, }: Readonly<{ children: React.ReactNode; }>) {
    return (
        <html lang="en" className={`${outfit.variable} ${roboto.variable}`}>
            <DynamicBody>
                {children}

                <Footer/>
            </DynamicBody>

        </html>
    );
}
