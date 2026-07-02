<script setup lang="ts">
import { Toaster } from 'vue-sonner'
</script>

<template>
    <Toaster />
    <header slot="header" class="app-header" style="padding: 1rem; border-bottom: 1px solid #ccc;">
      <h1>Sistemas de Transferências com Spring Boot e Vue.js</h1>
    </header>
    <main style="padding: 0 2rem;">
      <router-view />
    </main>
</template>

<style>
html,
body {
  min-height: 100%;
  margin: 0;
  padding: 0;
}
 
#app, wa-page {
  --ink: #142238;
  --paper: #eaf0ec;
  --card: #fffdf9;
  --brass: #b4863a;
  --brass-deep: #8c6425;
  --ledger-green: #2b6e4c;
  --rust: #9c3b27;
  --hairline: #d7ded8;
  --font-display: 'Fraunces', Georgia, serif;
  --font-body: 'Inter', var(--wa-font-family-body, sans-serif);
  --font-mono: 'IBM Plex Mono', ui-monospace, Menlo, monospace;

  --wa-color-brand-fill-loud: var(--ink);
  --wa-color-brand-on-loud: var(--card);
  --wa-color-brand-border-loud: var(--ink);
  --wa-font-family-body: var(--font-body);
 
  min-height: 100vh;
  background:
    radial-gradient(circle at 12% 8%, rgba(180, 134, 58, 0.10), transparent 45%),
    var(--paper);
  font-family: var(--font-body);
  color: var(--ink);
}
</style>