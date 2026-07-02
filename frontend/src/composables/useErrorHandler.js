import { toast } from 'vue-sonner'

export function useErrorHandler() {
  function handleApiError(error) {
    if (!error || !error.message) {
      toast.error('Erro de conexão com o servidor')
      return
    }
    const { message, details } = JSON.parse(error.message)
    toast.error(message || 'Erro inesperado', {
      description: details?.join('\n'),
    })
  }

  return { handleApiError }
}