import {
  Anchor,
  Button,
  Center, Container,
  Divider,
  Group,
  Text,
  Paper,
  PaperProps,
  UnstyledButton
} from "@mantine/core";
import {upperFirst, useToggle} from "@mantine/hooks";
import {AuthForm} from "./AuthForm";
import {IconBrandGithub, IconBrandGitlab, IconBrandGoogle} from "@tabler/icons-react";

const AuthPage = (props: PaperProps) => {
  const [type, toggle] = useToggle(['login', 'register']);


  return <>
    <Container fluid>
      <Center className="h-lvh">
        <Paper radius="md" p="xl" withBorder {...props}>
          <Text size="lg" fw={500}>
            Welcome to Mantine, {type} with
          </Text>

          <Group grow mb="md" mt="md">
            <UnstyledButton>
              <IconBrandGoogle/>
            </UnstyledButton>
            <UnstyledButton>
              <IconBrandGithub/>
            </UnstyledButton>
            <UnstyledButton>
              <IconBrandGitlab/>
            </UnstyledButton>
          </Group>

          <Divider label="Or continue with email" labelPosition="center" my="lg"/>

          <AuthForm type={type}/>
          <Group justify="space-between" mt="xl">
            <Anchor component="button" type="button" c="dimmed" onClick={() => toggle()} size="xs">
              {type === 'register'
                ? 'Already have an account? Login'
                : "Don't have an account? Register"}
            </Anchor>
            <Button type="submit" radius="xl">
              {upperFirst(type)}
            </Button>
          </Group>
        </Paper>
      </Center>
    </Container>
  </>
}

export {AuthPage}