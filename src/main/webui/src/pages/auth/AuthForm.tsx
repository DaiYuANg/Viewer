import {Checkbox, PasswordInput, Stack, TextInput} from "@mantine/core";
import {useForm} from "@mantine/form";
import {useCallback} from "react";

type AuthFormProp = {
  type: string
}

const AuthForm = ({type}: AuthFormProp) => {
  const form = useForm({
    initialValues: {
      email: '',
      name: '',
      password: '',
      terms: true,
    },

    validate: {
      email: (val) => (/^\S+@\S+$/.test(val) ? null : 'Invalid email'),
      password: (val) => (val.length <= 6 ? 'Password should include at least 6 characters' : null),
    },
  });

  const submit = useCallback(() => {
    console.log(form.values)
  }, [form])

  return <>
    <form onSubmit={submit}>
      <Stack>
        {type === 'register' && (
          <TextInput
            label="Name"
            placeholder="Your name"
            value={form.values.name}
            onChange={(event) => form.setFieldValue('name', event.currentTarget.value)}
            radius="md"
          />
        )}

        <TextInput
          required
          label="Email"
          placeholder="hello@mantine.dev"
          value={form.values.email}
          onChange={(event) => form.setFieldValue('email', event.currentTarget.value)}
          error={form.errors.email && 'Invalid email'}
          radius="md"
        />

        <PasswordInput
          required
          label="Password"
          placeholder="Your password"
          value={form.values.password}
          onChange={(event) => form.setFieldValue('password', event.currentTarget.value)}
          error={form.errors.password && 'Password should include at least 6 characters'}
          radius="md"
        />

        {type === 'register' && (
          <Checkbox
            label="I accept terms and conditions"
            checked={form.values.terms}
            onChange={(event) => form.setFieldValue('terms', event.currentTarget.checked)}
          />
        )}
      </Stack>
    </form>
  </>
}

export {AuthForm}