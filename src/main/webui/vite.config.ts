import {defineConfig, PluginOption} from "vite";
import react from "@vitejs/plugin-react-swc";
import TurboConsole from "unplugin-turbo-console/vite";
import {visualizer} from "rollup-plugin-visualizer";
import {compression} from "vite-plugin-compression2";
import {VitePWA} from "vite-plugin-pwa";
import tsconfigPaths from "vite-tsconfig-paths";

export default defineConfig({
  base: "",
  plugins: [
    react(),
    TurboConsole(),
    visualizer({
      emitFile: true,
    }) as PluginOption,
    compression(),
    tsconfigPaths(),
    VitePWA(),
  ],
  esbuild: {
    drop: ['console', 'debugger'],
  },
  build: {
    reportCompressedSize: true,
    rollupOptions: {
      output: {
        experimentalMinChunkSize: 1024,
        manualChunks: {
          "monaco-editor": ['react-monaco-editor'],
          "mantine": ['@mantine/core', '@mantine/dates', '@mantine/code-highlight', '@mantine/charts', '@mantine/form', '@mantine/hooks', '@mantine/modals', '@mantine/notifications', '@mantine/nprogress', '@mantine/spotlight', '@mantine/tiptap']
        }
      },
    },
  },
  server: {
    port: 3000,
  },
});
