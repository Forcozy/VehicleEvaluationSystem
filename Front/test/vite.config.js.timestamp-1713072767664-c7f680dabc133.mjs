// vite.config.js
import { fileURLToPath, URL } from "node:url";
import { defineConfig, loadEnv } from "file:///C:/Users/QQ1839913610/Desktop/clpjxtspbt3v3/VehicleEvaluationSystem/Front/test/node_modules/vite/dist/node/index.js";
import vue from "file:///C:/Users/QQ1839913610/Desktop/clpjxtspbt3v3/VehicleEvaluationSystem/Front/test/node_modules/@vitejs/plugin-vue/dist/index.mjs";
var __vite_injected_original_import_meta_url = "file:///C:/Users/QQ1839913610/Desktop/clpjxtspbt3v3/VehicleEvaluationSystem/Front/test/vite.config.js";
var vite_config_default = defineConfig(({ command, mode }) => {
  const env = loadEnv(mode, process.cwd(), "");
  return {
    plugins: [
      vue()
    ],
    resolve: {
      alias: {
        "@": fileURLToPath(new URL("./src", __vite_injected_original_import_meta_url))
      }
    },
    server: {
      host: "0.0.0.0",
      port: env.VITE_APP_PORT,
      proxy: {
        "/api": {
          target: env.VITE_APP_BASEURL,
          //后端路径
          changeOrigin: true,
          rewrite: (path) => path.replace(/^\/api/, "")
        }
      }
    }
  };
});
export {
  vite_config_default as default
};
//# sourceMappingURL=data:application/json;base64,ewogICJ2ZXJzaW9uIjogMywKICAic291cmNlcyI6IFsidml0ZS5jb25maWcuanMiXSwKICAic291cmNlc0NvbnRlbnQiOiBbImNvbnN0IF9fdml0ZV9pbmplY3RlZF9vcmlnaW5hbF9kaXJuYW1lID0gXCJDOlxcXFxVc2Vyc1xcXFxRUTE4Mzk5MTM2MTBcXFxcRGVza3RvcFxcXFxjbHBqeHRzcGJ0M3YzXFxcXFZlaGljbGVFdmFsdWF0aW9uU3lzdGVtXFxcXEZyb250XFxcXHRlc3RcIjtjb25zdCBfX3ZpdGVfaW5qZWN0ZWRfb3JpZ2luYWxfZmlsZW5hbWUgPSBcIkM6XFxcXFVzZXJzXFxcXFFRMTgzOTkxMzYxMFxcXFxEZXNrdG9wXFxcXGNscGp4dHNwYnQzdjNcXFxcVmVoaWNsZUV2YWx1YXRpb25TeXN0ZW1cXFxcRnJvbnRcXFxcdGVzdFxcXFx2aXRlLmNvbmZpZy5qc1wiO2NvbnN0IF9fdml0ZV9pbmplY3RlZF9vcmlnaW5hbF9pbXBvcnRfbWV0YV91cmwgPSBcImZpbGU6Ly8vQzovVXNlcnMvUVExODM5OTEzNjEwL0Rlc2t0b3AvY2xwanh0c3BidDN2My9WZWhpY2xlRXZhbHVhdGlvblN5c3RlbS9Gcm9udC90ZXN0L3ZpdGUuY29uZmlnLmpzXCI7aW1wb3J0IHsgZmlsZVVSTFRvUGF0aCwgVVJMIH0gZnJvbSAnbm9kZTp1cmwnXG5cbmltcG9ydCB7IGRlZmluZUNvbmZpZyxsb2FkRW52IH0gZnJvbSAndml0ZSdcbmltcG9ydCB2dWUgZnJvbSAnQHZpdGVqcy9wbHVnaW4tdnVlJ1xuXG4vLyBodHRwczovL3ZpdGVqcy5kZXYvY29uZmlnL1xuXG5leHBvcnQgZGVmYXVsdCBkZWZpbmVDb25maWcoKHtjb21tYW5kLG1vZGV9KSA9PntcblxuXG4gIGNvbnN0IGVudiA9IGxvYWRFbnYobW9kZSxwcm9jZXNzLmN3ZCgpLCcnKVxuICByZXR1cm57XG5cbiAgICBwbHVnaW5zOiBbXG4gICAgICB2dWUoKSxcbiAgICBdLFxuICAgIHJlc29sdmU6IHtcbiAgICAgIGFsaWFzOiB7XG4gICAgICAgICdAJzogZmlsZVVSTFRvUGF0aChuZXcgVVJMKCcuL3NyYycsIGltcG9ydC5tZXRhLnVybCkpXG4gICAgICB9XG4gICAgfSxcbiAgICBzZXJ2ZXI6e1xuICAgICAgaG9zdDogJzAuMC4wLjAnLFxuICAgICAgcG9ydDogZW52LlZJVEVfQVBQX1BPUlQsXG4gICAgICBwcm94eToge1xuICAgICAgICBcIi9hcGlcIjoge1xuICAgICAgICAgIHRhcmdldDogZW52LlZJVEVfQVBQX0JBU0VVUkwsICAvL1x1NTQwRVx1N0FFRlx1OERFRlx1NUY4NFxuICAgICAgICAgIGNoYW5nZU9yaWdpbjogdHJ1ZSxcbiAgICAgICAgICByZXdyaXRlOiAocGF0aCkgPT4gcGF0aC5yZXBsYWNlKC9eXFwvYXBpLywgXCJcIiksXG4gICAgICAgIH0sXG4gICAgICB9XG4gICAgfVxuXG5cbiAgfVxufSlcblxuXG4vKlxuXG5leHBvcnQgZGVmYXVsdCBkZWZpbmVDb25maWcoe1xuXG5cblxuXG4gIC8hKlxuXG4gICAgb3B0aW1pemVEZXBzOiB7XG4gICAgICAvLyBcdTVGMDBcdTUzRDFcdTY1RjYgXHU4OUUzXHU1MUIzXHU4RkQ5XHU0RTlCY29tbW9uanNcdTUzMDVcdThGNkNcdTYyMTBlc21cdTUzMDVcbiAgICAgIGluY2x1ZGU6IFtcbiAgICAgICAgXCJAamlhbWluZ2hpL2MtcmVuZGVyXCIsXG4gICAgICAgIFwiQGppYW1pbmdoaS9jLXJlbmRlci9saWIvcGx1Z2luL3V0aWxcIixcbiAgICAgICAgXCJAamlhbWluZ2hpL2NoYXJ0cy9saWIvdXRpbC9pbmRleFwiLFxuICAgICAgICBcIkBqaWFtaW5naGkvY2hhcnRzL2xpYi91dGlsXCIsXG4gICAgICAgIFwiQGppYW1pbmdoaS9jaGFydHMvbGliL2V4dGVuZC9pbmRleFwiLFxuICAgICAgICBcIkBqaWFtaW5naGkvY2hhcnRzXCIsXG4gICAgICAgIFwiQGppYW1pbmdoaS9jb2xvclwiLFxuICAgICAgXSxcbiAgICB9LFxuICAgICAgYnVpbGQ6IHtcbiAgICAgICAgY29tbW9uanNPcHRpb25zOiB7XG4gICAgICAgICAgaW5jbHVkZTogW1xuICAgICAgICAgICAgL25vZGVfbW9kdWxlcy8sXG4gICAgICAgICAgXSxcbiAgICAgICAgfSxcblxuICAgICAgfSxcblxuICAqIS9cblxuXG5cblxuLyEqXG4gIHNlcnZlcjoge1xuICAgIHByb3h5OiB7XG4gICAgICBcIi9hcGlcIjoge1xuICAgICAgICB0YXJnZXQ6IFwiaHR0cDovL2xvY2FsaG9zdDo4MDgwL1wiLCAgLy9cdTU0MEVcdTdBRUZcdThERUZcdTVGODRcbiAgICAgICAgY2hhbmdlT3JpZ2luOiB0cnVlLFxuICAgICAgICByZXdyaXRlOiAocGF0aCkgPT4gcGF0aC5yZXBsYWNlKC9eXFwvYXBpLywgXCJcIiksXG4gICAgICB9LFxuICAgIH0sXG4qIS9cbi8hKlxuXG5cbiAgICBzZXJ2ZXI6IHtcbiAgICAgIG9wZW46IGZhbHNlLCAvL1x1OTg3OVx1NzZFRVx1NTQyRlx1NEUxQ1x1NjVGNlx1NjYyRlx1NTQyNlx1NjI1M1x1NUYwMFx1OTg3NVx1OTc2MlxuICAgICAgaG9zdDogXCIxMjcuMC4wLjFcIixcbiAgICAgIHBvcnQ6IDM0NTYsXG4gICAgICBwcm94eToge1xuICAgICAgICBcIl4vYXBpL1wiOiB7XG4gICAgICAgICAgdGFyZ2V0OiBcImh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9cIiwgLy8gXHU1NDBFXHU1M0YwXHU2NzBEXHU1MkExXHU1NjY4XHU1NzMwXHU1NzQwXG4gICAgICAgICAgY2hhbmdlT3JpZ2luOiB0cnVlIC8hKiBcdTUxNDFcdThCQjhcdThERThcdTU3REYgKiEvLFxuICAgICAgICAgIHJld3JpdGU6IChwYXRoKSA9PiBwYXRoLnJlcGxhY2UoL15cXC9hcGkvLCBcIlwiKSxcbiAgICAgICAgfSxcbiAgICAgIH0sXG5cblxuXG5cbiohL1xuXG5cblxuXG4gLyEqICAgLy8gXHU5MTREXHU3RjZFXHU1MjREXHU3QUVGXHU2NzBEXHU1MkExXHU1NzMwXHU1NzQwXHU1NDhDXHU3QUVGXHU1M0UzXG4gICAgcG9ydDogODE4OCxcbiAgICBob3N0OiAnMC4wLjAuMCcsXG4gICAgY29yczogdHJ1ZSxcbiAgICAvLyBcdThCQkVcdTdGNkVcdTUzQ0RcdTU0MTFcdTRFRTNcdTc0MDZcdUZGMENcdThERThcdTU3REZcbiAgICBwcm94eToge1xuICAgICAgJy9hcGknOiB7XG4gICAgICAgIHRhcmdldDogJ2h0dHA6Ly8xMjcuMC4wLjE6OTE5OScsXG4gICAgICAgIGNoYW5nZU9yaWdpbjogdHJ1ZSxcbiAgICAgICAgcmV3cml0ZTogKHBhdGgpID0+IHBhdGgucmVwbGFjZSgvXlxcL2FwaS8sICcnKVxuICAgICAgfSxcbiAgICB9LFxuICAgIGhtcjoge1xuICAgICAgb3ZlcmxheTogZmFsc2VcbiAgICB9XG4qIS9cblxuXG5cblxuXG5cblxuXG4gIHBsdWdpbnM6IFtcbiAgICB2dWUoKSxcbiAgXSxcbiAgcmVzb2x2ZToge1xuICAgIGFsaWFzOiB7XG4gICAgICAnQCc6IGZpbGVVUkxUb1BhdGgobmV3IFVSTCgnLi9zcmMnLCBpbXBvcnQubWV0YS51cmwpKVxuICAgIH1cbiAgfVxufSlcblxuKi9cblxuXG5cblxuXG5cbiJdLAogICJtYXBwaW5ncyI6ICI7QUFBNGEsU0FBUyxlQUFlLFdBQVc7QUFFL2MsU0FBUyxjQUFhLGVBQWU7QUFDckMsT0FBTyxTQUFTO0FBSG1RLElBQU0sMkNBQTJDO0FBT3BVLElBQU8sc0JBQVEsYUFBYSxDQUFDLEVBQUMsU0FBUSxLQUFJLE1BQUs7QUFHN0MsUUFBTSxNQUFNLFFBQVEsTUFBSyxRQUFRLElBQUksR0FBRSxFQUFFO0FBQ3pDLFNBQU07QUFBQSxJQUVKLFNBQVM7QUFBQSxNQUNQLElBQUk7QUFBQSxJQUNOO0FBQUEsSUFDQSxTQUFTO0FBQUEsTUFDUCxPQUFPO0FBQUEsUUFDTCxLQUFLLGNBQWMsSUFBSSxJQUFJLFNBQVMsd0NBQWUsQ0FBQztBQUFBLE1BQ3REO0FBQUEsSUFDRjtBQUFBLElBQ0EsUUFBTztBQUFBLE1BQ0wsTUFBTTtBQUFBLE1BQ04sTUFBTSxJQUFJO0FBQUEsTUFDVixPQUFPO0FBQUEsUUFDTCxRQUFRO0FBQUEsVUFDTixRQUFRLElBQUk7QUFBQTtBQUFBLFVBQ1osY0FBYztBQUFBLFVBQ2QsU0FBUyxDQUFDLFNBQVMsS0FBSyxRQUFRLFVBQVUsRUFBRTtBQUFBLFFBQzlDO0FBQUEsTUFDRjtBQUFBLElBQ0Y7QUFBQSxFQUdGO0FBQ0YsQ0FBQzsiLAogICJuYW1lcyI6IFtdCn0K
