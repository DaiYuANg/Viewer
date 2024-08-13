import {AppShell, LoadingOverlay} from "@mantine/core";
import {Outlet} from "react-router-dom";
import {useDisclosure} from "@mantine/hooks";
import {Suspense} from "react";

const MainLayout = () => {
  const [opened] = useDisclosure();
  return (
    <>
      <AppShell
        navbar={{width: 300, breakpoint: 'sm', collapsed: {mobile: !opened}}}
        padding="md"
        transitionDuration={500}
        transitionTimingFunction="ease"
      >
        <AppShell.Navbar p="md">
          Navbar
        </AppShell.Navbar>
        <AppShell.Main>
          <Suspense fallback={
            <LoadingOverlay
              visible
              zIndex={1000}
              overlayProps={{ radius: 'sm', blur: 2 }}
              loaderProps={{ color: 'blue', type: 'bars' }}
            />
          }>
            <Outlet/>
          </Suspense>
        </AppShell.Main>
      </AppShell>
    </>
  )
}

export {MainLayout}