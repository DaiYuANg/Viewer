import axios from "axios";
import {nprogress} from "@mantine/nprogress";

const instance = axios.create({
  baseURL: '/',
  timeout: 1000,
});

instance.interceptors.request.use(config => {
  console.log("请求")
  nprogress.start()
  return config;
})

instance.interceptors.response.use(resp=>{
  nprogress.stop()
  return resp.data
})

export {
  instance
}