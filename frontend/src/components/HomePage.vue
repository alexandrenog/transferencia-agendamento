<script setup lang="ts">
import TabelaAgendamentos from './TabelaAgendamentos.vue'
</script>

<template>
  <wa-page style="width: 80%; margin: 0 auto;">
    <main>
      <TabelaAgendamentos>  </TabelaAgendamentos>
      <div class="main-actions">
        <wa-button variant="primary" @click="$router.push('/agendamento')">
          Agendar uma nova Transferência
        </wa-button>
      </div>
    </main>

  </wa-page>
</template>

<style scoped>
.main-actions {
  display: flex;
  justify-content: flex-end;
  gap: var(--wa-space-s);
  margin-top: var(--wa-space-s);
  padding-right: var(--wa-space-m);
}
</style>



