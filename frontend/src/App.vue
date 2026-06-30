<script setup lang="ts">
import { ref } from 'vue'
import '@awesome.me/webawesome/dist/components/input/input.js'
import '@awesome.me/webawesome/dist/components/button/button.js'

const name = ref('')
const message = ref('')

async function fetchHello() {
  const res = await fetch('/api/hello')
  const data = await res.json()
  message.value = data.message
}
</script>

<template>
  <div class="container">
    <wa-input label="Your name" v-model="name" clearable></wa-input>
    <wa-button variant="primary" @click="fetchHello">Say hello</wa-button>
    <p v-if="message">{{ message }}</p>
  </div>
</template>

<style scoped></style>
