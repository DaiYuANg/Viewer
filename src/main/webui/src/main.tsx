import React from "react";
import {createRoot} from "react-dom/client";
import {App} from "./App.tsx";

import {MantineProvider} from "@mantine/core";
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";
import {ReactQueryDevtools} from "@tanstack/react-query-devtools";
import {NavigationProgress} from "@mantine/nprogress";
import {ModalsProvider} from "@mantine/modals";
import {GlobalSpotlight} from "./component/GlobalSpotlight.tsx";
import {Notifications} from "@mantine/notifications";
import i18n from "./i18n";
import {BrowserRouter} from "react-router-dom";

console.log(i18n.resolvedLanguage);
console.log(`current language${i18n.language}`);
const queryClient = new QueryClient();
createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <BrowserRouter>
      <QueryClientProvider client={queryClient}>
        <ReactQueryDevtools initialIsOpen={true}/>
        <MantineProvider>
          <Notifications/>
          <NavigationProgress/>
          <ModalsProvider>
            <GlobalSpotlight/>
            <App/>
          </ModalsProvider>
        </MantineProvider>
      </QueryClientProvider>
    </BrowserRouter>
  </React.StrictMode>,
);
