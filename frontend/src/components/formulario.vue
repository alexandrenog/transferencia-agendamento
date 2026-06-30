<template>
  <section class="formulario">
    <h2>Agendamento de Transferência</h2>
    <form @submit.prevent="submitForm">
      <div class="field">
        <label for="nome"><WaUser /> Nome</label>
        <input id="nome" v-model="form.nome" type="text" required />
      </div>

      <div class="field">
        <label for="data"><WaCalendar /> Data</label>
        <input id="data" v-model="form.data" type="date" required />
      </div>

      <button type="submit" class="submit-button">
        <WaCheck /> Agendar
      </button>
    </form>
  </section>
</template>

<script>
import WaUser from '@awesome.me/webawesome/dist/components/wa-user'
import WaCalendar from '@awesome.me/webawesome/dist/components/wa-calendar'
import WaCheck from '@awesome.me/webawesome/dist/components/wa-check'

export default {
  name: 'Formulario',
  components: {
    WaUser,
    WaCalendar,
    WaCheck
  },
  data() {
    return {
      form: {
        nome: '',
        data: ''
      }
    }
  },
  methods: {
    submitForm() {
      this.$emit('submit', { ...this.form });
      this.resetForm();
    },
    resetForm() {
      this.form = {
        nome: '',
        data: ''
      }
    }
  }
}
</script>

<style scoped>

.submit-button {
  display: inline-flex;
  align-items: center;
  gap: 10px;
  padding: 12px 20px;
  background: #2f6bed;
  color: #ffffff;
  border: none;
  border-radius: 999px;
  cursor: pointer;
  font-weight: 700;
  transition: background-color 0.2s ease;
}

.submit-button:hover {
  background: #214fc1;
}
</style>
