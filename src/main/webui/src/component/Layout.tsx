import {AppShell} from "@mantine/core";
import {Outlet} from "react-router-dom";
import {useDisclosure} from "@mantine/hooks";

const Layout = () => {
  const [opened] = useDisclosure();

  return (
    <>
      <AppShell
        navbar={{width: 300, breakpoint: 'sm', collapsed: {mobile: !opened}}}
        padding="md"
      >
        <AppShell.Navbar p="md">
          {/*<Navbar/>*/}
          Navbar
        </AppShell.Navbar>
        <AppShell.Main>
          <Outlet/>
        </AppShell.Main>
      </AppShell>
    </>
  )

}

export {Layout}