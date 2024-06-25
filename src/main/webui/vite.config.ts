import { defineConfig, PluginOption } from "vite";
import react from "@vitejs/plugin-react-swc";
import TurboConsole from "unplugin-turbo-console/vite";
import Pages from "vite-plugin-pages";
import { visualizer } from "rollup-plugin-visualizer";
import { compression } from "vite-plugin-compression2";
import { VitePWA } from "vite-plugin-pwa";

export default defineConfig({
  plugins: [
    react(),
    TurboConsole(),
    Pages({
      dirs: "src/pages",
      importMode: "async",
    }),
    visualizer({
      emitFile: true,
    }) as PluginOption,
    compression(),
    VitePWA(),
  ],
  build: {
    reportCompressedSize: true,
    rollupOptions: {
      output: {
        experimentalMinChunkSize: 1024,
      },
    },
  },
});
