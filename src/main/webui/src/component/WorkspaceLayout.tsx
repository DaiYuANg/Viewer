import {AppShell, Burger} from "@mantine/core";
import {useDisclosure} from "@mantine/hooks";
import {useEffect, useRef, useState} from "react";
import {Outlet} from "react-router-dom";
import {WorkspaceLayoutContext} from "./WorkspaceLayoutContext.ts";


const WorkspaceLayout = () => {
  const [opened, {toggle}] = useDisclosure();
  const mainRef = useRef<HTMLDivElement>(null);
  const [mainSize, setMainSize] = useState({width: 0, height: 0});

  useEffect(() => {
    const updateMainSize = () => {
      if (mainRef.current) {
        setMainSize({
          width: mainRef.current.offsetWidth,
          height: mainRef.current.offsetHeight,
        });
      }
    };

    updateMainSize();

    window.addEventListener("resize", updateMainSize);
    return () => {
      window.removeEventListener("resize", updateMainSize);
    };
  }, []);

  return <>
    <WorkspaceLayoutContext.Provider value={mainSize}>
      <AppShell
        header={{height: 60}}
        navbar={{
          width: 300,
          breakpoint: 'sm',
          collapsed: {mobile: !opened},
        }}
        padding="md"
        transitionDuration={500}
        transitionTimingFunction="ease"
      >
        <AppShell.Header>
          <Burger
            opened={opened}
            onClick={toggle}
            hiddenFrom="sm"
            size="sm"
          />
          <div>Logo</div>
        </AppShell.Header>

        <AppShell.Navbar p="md">Navbar</AppShell.Navbar>

        <AppShell.Main ref={mainRef}>
          <Outlet/>
        </AppShell.Main>
      </AppShell>
    </WorkspaceLayoutContext.Provider>
  </>
}

export {WorkspaceLayout}