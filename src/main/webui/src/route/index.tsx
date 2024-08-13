import {RouteObject} from "react-router-dom";
import {MainLayout} from "../component/MainLayout.tsx";
import {Auth, Dashboard, Workspace} from "../pages";
import {WorkspaceLayout} from "../component/WorkspaceLayout.tsx";

const routes: Array<RouteObject> = [
  {
    path: '',
    element: <MainLayout/>,
    children: [
      {
        path: '/',
        element: <Dashboard/>
      }
    ]
  },
  {
    path: '/workspace',
    element: <WorkspaceLayout/>,
    children: [
      {
        path: '',
        element: <Workspace/>
      }
    ]
  },
  {
    path: '/auth',
    element: <Auth/>
  }
]

export {routes}