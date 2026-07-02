/// <reference types="vite/client" />
declare module '*.vue' {
  import type { DefineComponent } from 'vue'
  import { ref } from 'vue'

  const component: DefineComponent<{}, {}, any>
  export default component
}