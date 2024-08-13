import {createContext, useContext} from "react";

const WorkspaceLayoutContext = createContext<{ width: number, height: number } | undefined>(undefined);

const useWorkspaceLayoutSize = () => {
  const context = useContext(WorkspaceLayoutContext);
  if (!context) {
    throw new Error("useMainSize must be used within a MainSizeProvider");
  }
  return context;
}

export {WorkspaceLayoutContext, useWorkspaceLayoutSize}