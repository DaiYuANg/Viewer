import i18n from "i18next";
import { initReactI18next } from "react-i18next";
import LanguageDetector from "i18next-browser-languagedetector";
import en from "./en/translation.json";
import zh from "./zh/translation.json";

i18n.on("initialized", () => {
  console.log("Resolved language:", i18n.resolvedLanguage);
  console.log("Translation for 'install':", i18n.t("firstStep"));
  console.log(`Current language: ${i18n.language}`);
});

const resources = {
  en: {
    translation: en,
  },
  "zh-CN": {
    translation: zh,
  },
};

i18n
  .use(initReactI18next)
  .use(LanguageDetector)
  .init({
    debug: true,
    preload: ["en", "zh-CN"],
    resources,
    fallbackLng: "en",

    interpolation: {
      escapeValue: false,
    },
  });

export * from "./keys";
export default i18n;
