import type { Config } from "tailwindcss";

export default {
  content: [
    "./pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./components/**/*.{js,ts,jsx,tsx,mdx}",
    "./app/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  theme: {
    extend: {
      screens: {
        '3xl': "1800px",
      },
      fontFamily: {
        outfit: ['var(--font-outfit)'],
        roboto: ['var(--font-roboto)'],
      },
      colors: {
        bg: "#1E1E1E",
        lgbg: "#E6E6E6",
      },
    },
  },
  plugins: [],
} satisfies Config;
