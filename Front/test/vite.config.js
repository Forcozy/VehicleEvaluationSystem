import { fileURLToPath, URL } from 'node:url'

import { defineConfig,loadEnv } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/

export default defineConfig(({command,mode}) =>{


  const env = loadEnv(mode,process.cwd(),'')
  return{

    plugins: [
      vue(),
    ],
    resolve: {
      alias: {
        '@': fileURLToPath(new URL('./src', import.meta.url))
      }
    },
    server:{
      host: '0.0.0.0',
      port: env.VITE_APP_PORT,
      proxy: {
        "/api": {
          target: env.VITE_APP_BASEURL,  //后端路径
          changeOrigin: true,
          rewrite: (path) => path.replace(/^\/api/, ""),
        },
      }
    }


  }
})


/*

export default defineConfig({




  /!*

    optimizeDeps: {
      // 开发时 解决这些commonjs包转成esm包
      include: [
        "@jiaminghi/c-render",
        "@jiaminghi/c-render/lib/plugin/util",
        "@jiaminghi/charts/lib/util/index",
        "@jiaminghi/charts/lib/util",
        "@jiaminghi/charts/lib/extend/index",
        "@jiaminghi/charts",
        "@jiaminghi/color",
      ],
    },
      build: {
        commonjsOptions: {
          include: [
            /node_modules/,
          ],
        },

      },

  *!/




/!*
  server: {
    proxy: {
      "/api": {
        target: "http://localhost:8080/",  //后端路径
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ""),
      },
    },
*!/
/!*


    server: {
      open: false, //项目启东时是否打开页面
      host: "127.0.0.1",
      port: 3456,
      proxy: {
        "^/api/": {
          target: "http://localhost:8080/", // 后台服务器地址
          changeOrigin: true /!* 允许跨域 *!/,
          rewrite: (path) => path.replace(/^\/api/, ""),
        },
      },




*!/




 /!*   // 配置前端服务地址和端口
    port: 8188,
    host: '0.0.0.0',
    cors: true,
    // 设置反向代理，跨域
    proxy: {
      '/api': {
        target: 'http://127.0.0.1:9199',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '')
      },
    },
    hmr: {
      overlay: false
    }
*!/








  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  }
})

*/






