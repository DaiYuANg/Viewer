import {lazy} from "react";

const Dashboard = lazy(() =>
  import("./Dashboard.tsx").then((module) => ({
    default: module.Dashboard,
  }))
);

const Auth = lazy(() =>
  import("./auth/AuthPage.tsx").then((module) => ({
    default: module.AuthPage,
  }))
);

const Workspace = lazy(() =>
  import("./Workspace.tsx").then((module) => ({
    default: module.Workspace,
  }))
);

export {Dashboard, Auth, Workspace}