import {Center} from "@mantine/core";
import {useState} from "react";
import MonacoEditor from "react-monaco-editor/lib/editor";
import {useWorkspaceLayoutSize} from "../component/WorkspaceLayoutContext.ts";

const Workspace = () => {
  const [code] = useState(`
    console.log('first')
  `);
  const options = {
    selectOnLineNumbers: true
  };
  const {width, height} = useWorkspaceLayoutSize()
  console.log(width, height)
  return <>
    <Center className={['min-w-fit min-h-fit'].join(" ")}>
      <MonacoEditor
        width={width}
        height={height}
        language="javascript"
        theme="vs-dark"
        value={code}
        options={options}
      />
    </Center>
  </>
}

export {Workspace}