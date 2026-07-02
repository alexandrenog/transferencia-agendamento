<template>
  <wa-page>
    <main style="padding: 0 2rem;">
      <div class="agendamento-page-content">
        <wa-card class="transfer-form-card">
          <div slot="header">
            <span class="transfer-form-eyebrow">Agendamento de Transferência</span>
          </div>

          <form
            class="transfer-form"
            :class="{ 'transfer-form--disabled': formBloqueado }"
            @submit.prevent="calculaTaxaTransferencia"
          >
            <!-- Conta de origem -->
            <wa-input
              class="transfer-form-input--mono"
              label="Conta de origem"
              name="contaOrigem"
              v-model="form.contaOrigem"
              :disabled="formBloqueado"
              required
            ></wa-input>

            <!-- Conta de destino -->
            <wa-input
              class="transfer-form-input--mono"
              label="Conta de destino"
              name="contaDestino"
              v-model="form.contaDestino"
              :disabled="formBloqueado"
              required
            ></wa-input>

            <!-- Valor da transferência -->
            <wa-input
              class="transfer-form-input--mono transfer-form-input--amount"
              label="Valor da transferência"
              name="valorTransferencia"
              type="number"
              min="0"
              step="0.01"
              inputmode="decimal"
              placeholder="0,00"
              required
              v-model.number="form.valorTransferencia"
              :disabled="formBloqueado"
            >
              <span slot="start">R$</span>
            </wa-input>

            <div class="transfer-form-perforation" aria-hidden="true"></div>

            <div class="transfer-form-dates">
              <!-- Data de agendamento (somente leitura, data atual) -->
              <wa-input
                class="transfer-form-input--mono"
                label="Data de agendamento"
                name="dataAgendamento"
                type="date"
                :value="form.dataAgendamento"
                readonly
                disabled
              ></wa-input>

              <!-- Data da transferência -->
              <wa-input
                class="transfer-form-input--mono"
                label="Data da transferência"
                name="dataTransferencia"
                type="date"
                :min="dataMinimaTransferencia"
                lang="pt-BR"
                required
                v-model="form.dataTransferencia"
                :disabled="formBloqueado"
              ></wa-input>
            </div>

            <div class="transfer-form-actions">
              <wa-button variant="neutral" type="button" @click="$router.push('/')">
                Cancelar
              </wa-button>
              <wa-button variant="neutral" appearance="outlined" type="button" @click="handleReset">
                Limpar
              </wa-button>
              <wa-button variant="brand" type="submit" :loading="enviando" :disabled="formBloqueado">
                Prosseguir
              </wa-button>
            </div>
          </form>
        </wa-card>

        <div class="transfer-result" v-if="resultado">
          <div class="receipt-stub">
            <span class="receipt-stub-eyebrow">Resumo da transferência</span>
            <dl class="receipt-stub-figures">
              <div>
                <dt>Taxa calculada</dt>
                <dd>R$ {{ resultado.taxa.toFixed(2) }}</dd>
              </div>
              <div>
                <dt>Valor total</dt>
                <dd>R$ {{ resultado.valorTotal.toFixed(2) }}</dd>
              </div>
            </dl>
            <div class="transfer-form-actions">
              <wa-button variant="neutral" appearance="outlined" type="button" @click="$router.push('/')">
                Cancelar
              </wa-button>
              <wa-button variant="brand" :loading="enviando" @click="confirmarTransferencia">
                Confirmar transferência
              </wa-button>
            </div>
          </div>
        </div>
      </div>
    </main>
  </wa-page>
</template>

<script setup>
import { reactive, ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import { toast } from 'vue-sonner'
import { useErrorHandler } from '@/composables/useErrorHandler'
const { handleApiError } = useErrorHandler()

const router = useRouter()

const CONTA_PADRAO = 'XXXXXXXXXX'

// Helper para formatar a data atual no padrão exigido pelo input type="date" (YYYY-MM-DD)
function dataAtualISO() {
  const hoje = new Date()
  const ano = hoje.getFullYear()
  const mes = String(hoje.getMonth() + 1).padStart(2, '0')
  const dia = String(hoje.getDate()).padStart(2, '0')
  return `${ano}-${mes}-${dia}`
}

const form = reactive({
  contaOrigem: "",
  contaDestino: "",
  valorTransferencia: null,
  dataTransferencia: '',
  // Data de agendamento inicia com a data atual e não é editável
  dataAgendamento: dataAtualISO()
})

// Não é possível agendar uma transferência para uma data anterior à data de agendamento
const dataMinimaTransferencia = computed(() => form.dataAgendamento)


// --- Envio do formulário ---
const enviando = ref(false)
const resultado = ref(null)   // resposta da API
const formBloqueado = ref(false) // trava o formulário (não-editável / estilo acinzentado)

const emit = defineEmits(['submit'])

async function calculaTaxaTransferencia() {
  enviando.value = true
  resultado.value = null

  const payload = {
    contaOrigem: form.contaOrigem,
    contaDestino: form.contaDestino,
    valorTransferencia: form.valorTransferencia,
    dataTransferencia: form.dataTransferencia,
    dataAgendamento: form.dataAgendamento,
  }

  try {
    const response = await fetch('/api/calcula-taxa', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(payload)
    })

    if (!response.ok) {
      const texto = await response.text()
      throw new Error(texto || `Erro ${response.status}`)
    }

    const data = await response.json()
    resultado.value = data
    emit('submit', payload) // mantém o evento, se o componente pai precisar dele
    formBloqueado.value = true
  } catch (e) {
    formBloqueado.value = false
    handleApiError(e)
  } finally {
    enviando.value = false
  }
}

async function confirmarTransferencia () {
  enviando.value = true
  resultado.value = null

  const payload = {
    contaOrigem: form.contaOrigem,
    contaDestino: form.contaDestino,
    valorTransferencia: form.valorTransferencia,
    dataTransferencia: form.dataTransferencia,
    dataAgendamento: form.dataAgendamento,
  }

  try {
    const response = await fetch('/api/agenda-transferencia', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(payload)
    })

    if (!response.ok) {
      const texto = await response.text()
      throw new Error(texto || `Erro ${response.status}`)
    }

    const message = await response.text()
    toast.success(message)    
  } catch (e) {
    handleApiError(e)
  } finally {
    enviando.value = false
    router.push('/')
  }
}

function handleReset() {
  form.contaOrigem = ""
  form.contaDestino = ""
  form.valorTransferencia = null
  form.dataTransferencia = ''
  resultado.value = null
  formBloqueado.value = false
}


</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Fraunces:opsz,wght@9..144,500;9..144,600&family=Inter:wght@400;500;600&family=IBM+Plex+Mono:wght@500;600&display=swap');

/* ---- Design tokens, scoped to this page ---- */
.agendamento-page-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: var(--wa-space-l);
  padding: 3rem 1.5rem 4rem;
}

.transfer-form-card, .transfer-form {
  width: 600px;
  max-width: 100%;
}

.transfer-form-card {
  position: relative;
  --wa-color-surface-default: var(--card);
  border-radius: 0 12px 12px 0;
  box-shadow: 0 0 2px rgba(20, 34, 56, 0.96), 0 0 20px -12px rgba(20, 34, 56, 0.95); 
  padding: 14px;
  background: white;
}

/* A brass spine along the card's edge — the one deliberate flourish */
.transfer-form-card::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  width: 3px;
  background: var(--brass);
  border-radius: 2px 0 0 2px;
}

.transfer-form-eyebrow {
  display: inline-block;
  font-family: var(--font-mono);
  font-size: 22px;
  font-weight: 600;
  letter-spacing: 0.11em;
  text-transform: uppercase;
  color: var(--ink);
  margin-bottom: var(--wa-space-2xs);
}

.transfer-form {
  display: flex;
  flex-direction: column;
  gap: var(--wa-space-l);
}

/* Account numbers and money read like a statement: tabular mono figures */
.transfer-form-input--mono {
  --wa-font-family-body: var(--font-mono);
}

.transfer-form-input--amount::part(start),
.transfer-form-input--amount span[slot="start"] {
  font-weight: 600;
  color: var(--brass-deep);
}

/* Signature element: a torn receipt edge separating the transfer
   details from the scheduling dates */
.transfer-form-perforation {
  position: relative;
  height: 1px;
  margin: 0;
  background-image: linear-gradient(to right, var(--hairline) 60%, transparent 0%);
  background-position: top;
  background-size: 12px 1px;
  background-repeat: repeat-x;
}


.transfer-form-dates {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: var(--wa-space-l);
}

@media (max-width: 520px) {
  .transfer-form-dates {
    grid-template-columns: 1fr;
  }
}

.transfer-form-actions {
  display: flex;
  justify-content: flex-end;
  gap: var(--wa-space-s);
  margin-top: var(--wa-space-s);
}

.transfer-form--disabled {
  opacity: 0.6;
  filter: grayscale(50%);
  pointer-events: none;
  cursor: not-allowed;
  transition: opacity 0.2s ease, filter 0.2s ease;
}

/* ---- Result: styled as a carbon-copy receipt stub ---- */
.transfer-result {
  width: 600px;
  max-width: 100%;
  display: flex;
  flex-direction: column;
  gap: var(--wa-space-m);
}

.receipt-stub {
  position: relative;
  background: var(--card);
  border: 1px dashed var(--hairline);
  border-radius: 4px;
  padding: 1.75rem 2rem 2rem;
  overflow: hidden;
}

.receipt-stub-eyebrow {
  display: block;
  font-family: var(--font-mono);
  font-size: 11px;
  font-weight: 600;
  letter-spacing: 0.1em;
  text-transform: uppercase;
  color: var(--ink-soft);
  margin-bottom: 1rem;
}

.receipt-stub-figures {
  display: flex;
  flex-wrap: wrap;
  gap: 2rem;
  margin: 0 0 0.5rem;
}

.receipt-stub-figures dt {
  font-size: var(--wa-font-size-s);
  color: var(--ink-soft);
  margin-bottom: 0.2rem;
}

.receipt-stub-figures dd {
  margin: 0;
  font-family: var(--font-mono);
  font-size: 1.4rem;
  font-weight: 600;
  color: var(--ink);
}

.transfer-form-result {
  --wa-color-danger-fill-quiet: color-mix(in srgb, var(--rust) 10%, white);
  --wa-color-danger-on-quiet: var(--rust);
}
</style>