import { Box, Button, Card, Center, Container, Group, rem, Stepper, Text, TextInput } from "@mantine/core";
import { useState } from "react";
import { IconAt } from "@tabler/icons-react";
import { useTranslation } from "react-i18next";
import { firstStep, install } from "../i18n";
import { useForm } from "@mantine/form";
import Joi from "joi";

type InstallationField = {
  email: string;
};

const InstallationGuarder = () => {
  const { t, i18n } = useTranslation();
  const form = useForm<InstallationField>({
    mode: "uncontrolled",
    initialValues: {
      email: "",
    },

    validate: {
      email: (value) => {
        return Joi.string()
          .email({
            minDomainSegments: 2,
          })
          .validate(value).value;
      },
    },
  });
  const [active, setActive] = useState(0);
  const [highestStepVisited, setHighestStepVisited] = useState(active);
  console.log(t("install"));
  console.log(i18n.language);
  const handleStepChange = (nextStep: number) => {
    const isOutOfBounds = nextStep > 3 || nextStep < 0;

    if (isOutOfBounds) {
      return;
    }

    setActive(nextStep);
    setHighestStepVisited((hSC) => Math.max(hSC, nextStep));
  };

  // Allow the user to freely go back and forth between visited steps.
  const shouldAllowSelectStep = (step: number) => highestStepVisited >= step && active !== step;

  return (
    <>
      <Container>
        <Center style={{ height: "100vh" }}>
          <Card withBorder shadow="sm" radius="md">
            <Card.Section withBorder inheritPadding py="xs">
              <Group justify="space-between">
                <Text fw={500}>{t(install)} Guarder</Text>
                <div>
                  <Button.Group>
                    <Button size="compact-md">First</Button>
                  </Button.Group>
                </div>
              </Group>
            </Card.Section>
            <Box my="xl">
              <form onSubmit={form.onSubmit((values) => console.log(values))}>
                <Stepper active={active} onStepClick={setActive}>
                  <Stepper.Step
                    label={t(firstStep)}
                    description="Create an account"
                    allowStepSelect={shouldAllowSelectStep(0)}
                  >
                    <Text fw={500}>Step 1 content: Create an account</Text>
                    <TextInput
                      mt="md"
                      variant="filled"
                      size="md"
                      radius="md"
                      rightSectionPointerEvents="none"
                      rightSection={<IconAt style={{ width: rem(16), height: rem(16) }} />}
                      placeholder="Your email"
                    />
                  </Stepper.Step>
                  <Stepper.Step
                    label="Second step"
                    description="Verify email"
                    allowStepSelect={shouldAllowSelectStep(1)}
                  >
                    Step 2 content: Verify email
                  </Stepper.Step>
                  <Stepper.Step
                    label="Final step"
                    description="Get full access"
                    allowStepSelect={shouldAllowSelectStep(2)}
                  >
                    Step 3 content: Get full access
                  </Stepper.Step>

                  <Stepper.Completed>Completed, click back button to get to previous step</Stepper.Completed>
                </Stepper>
                <Group justify="center" mt="xl">
                  <Button variant="default" onClick={() => handleStepChange(active - 1)}>
                    Back
                  </Button>
                  <Button onClick={() => handleStepChange(active + 1)}>Next step</Button>
                </Group>
              </form>
            </Box>
          </Card>
        </Center>
      </Container>
    </>
  );
};

export { InstallationGuarder };
