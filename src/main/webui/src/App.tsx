import {useRoutes} from "react-router-dom";
import "./App.css"
import {Suspense} from "react";
import {routes} from "./route";

const App = () => {
  return <Suspense fallback={<p>Loading...</p>}>
    {useRoutes(routes)}
  </Suspense>
};

export {App};
