import { RouterProvider } from "react-router-dom";
import { router } from "./router";
import { useQuery } from "@tanstack/react-query";
import { firstAccess } from "./api/system.ts";
import { InstallationGuarder } from "./component/InstallationGuarder.tsx";

const App = () => {
  const { data } = useQuery({
    queryKey: ["firstAccess"],
    queryFn: firstAccess,
  });

  return <>{data ? <InstallationGuarder /> : <RouterProvider router={router} />}</>;
};

export default App;
