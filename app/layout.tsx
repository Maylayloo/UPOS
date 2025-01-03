import {Inter} from "next/font/google"
import "./globals.css";
import localFont from "next/font/local";

const inter = Inter({subsets: ["latin"]});
import type {Metadata} from "next";
import React from "react";
import {ClerkProvider} from "@clerk/nextjs";

// const geistSans = localFont({
//   src: "./fonts/GeistVF.woff",
//   variable: "--font-geist-sans",
//   weight: "100 900",
// });
// const geistMono = localFont({
//   src: "./fonts/GeistMonoVF.woff",
//   variable: "--font-geist-mono",
//   weight: "100 900",
// });

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
        <html lang="en">
            <body className='m-12 bg-white'>
                {children}
            </body>


        </html>
    );
}
