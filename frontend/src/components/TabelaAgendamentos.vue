<template>
  <wa-card with-header>
    <div class="tabela-agendamentos-card-content">
      <div slot="header" class="tabela-agendamentos-header">
        <span class="tabela-agendamentos-eyebrow">Transferências Agendadas</span>
      </div>

      <!-- Loading state -->
      <div v-if="isLoading" style="padding: var(--wa-space-l); text-align: center;">
        <wa-spinner></wa-spinner>
      </div>

      <!-- Error state -->
      <wa-callout v-else-if="error" variant="danger" appearance="filled" style="margin: var(--wa-space-m);">
        <wa-icon slot="icon" name="circle-exclamation" variant="solid"></wa-icon>
        {{ error }}
      </wa-callout>

      <!-- Data table -->
      <table v-else-if="transferencias.length" class="wa-hover-rows wa-zebra-rows">
        <thead>
          <tr>
            <th>Conta Origem</th>
            <th>Conta Destino</th>
            <th>Valor</th>
            <th>Taxa</th>
            <th>Valor Total</th>
            <th>Data Agendamento</th>
            <th>Data Transferência</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, index) in transferencias" :key="index">
            <td>{{ item.contaOrigem }}</td>
            <td>{{ item.contaDestino }}</td>
            <td>{{ formatCurrency(item.valorTransferencia) }}</td>
            <td>{{ formatCurrency(item.taxa) }}</td>
            <td>{{ formatCurrency(item.valorTotal) }}</td>
            <td>{{ formatDate(item.dataAgendamento) }}</td>
            <td>{{ formatDate(item.dataTransferencia) }}</td>
          </tr>
        </tbody>
      </table>

      <!-- Empty state -->
      <div v-else class="wa-body-m" style="padding: var(--wa-space-l); text-align: center;">
        Nenhuma transferência encontrada.
      </div>
    </div>
  </wa-card>
</template>

<script setup>
import { ref, onMounted } from 'vue';

const transferencias = ref([]);
const isLoading = ref(true);
const error = ref(null);

async function fetchTransferencias() {
  isLoading.value = true;
  error.value = null;

  try {
    const response = await fetch('/api/listar-transferencias');

    if (!response.ok) {
      throw new Error(`Erro ao buscar transferências: ${response.status}`);
    }

    transferencias.value = await response.json();
  } catch (err) {
    error.value = err.message || 'Não foi possível carregar as transferências.';
  } finally {
    isLoading.value = false;
  }
}

onMounted(() => {
  fetchTransferencias();
});

function formatCurrency(value) {
  return new Intl.NumberFormat('pt-BR', {
    style: 'currency',
    currency: 'BRL'
  }).format(value);
}

function formatDate(dateStr) {
  const [year, month, day] = dateStr.split('-');
  return `${day}/${month}/${year}`;
}
</script>

<style scoped>
table {
  width: 100%;
}
.tabela-agendamentos-card-content {
  background: white;
  border-radius: 12px;
  box-shadow: 0 0 2px rgba(20, 34, 56, 0.96), 0 0 20px -12px rgba(20, 34, 56, 0.95); 
  padding: 14px;
  margin-bottom: var(--wa-space-l);
}


.tabela-agendamentos-eyebrow {
  display: inline-block;
  font-family: var(--font-mono);
  font-size: 22px;
  font-weight: 600;
  letter-spacing: 0.11em;
  text-transform: uppercase;
  color: var(--ink);
  margin-bottom: var(--wa-space-2xs);
  margin-left: var(--wa-space-l);
}
</style>