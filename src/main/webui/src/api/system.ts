import {instance} from "./request.ts";

const firstAccess = () => {
  return instance.get<null, boolean, null>("/sys/first/access")
}

export {firstAccess}