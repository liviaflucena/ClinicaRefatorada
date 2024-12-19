public class CalculadoraProcedimento implements ICalculadora {
    private final TipoProcedimento tipo;
  

    public CalculadoraProcedimento(TipoProcedimento tipo) {
        this.tipo = tipo;
 
    }

    @Override
    public float calcularValor() {
        switch (tipo) {
            case BÁSICO: return 50.00f;
            case COMUM: return 150.00f;
            case AVANÇADO: return 500.00f;
            default: return 0.00f;
        }
    }
}

/**
 * Classe responsável pelo cálculo de valores de procedimentos
 * Implementa o padrão Strategy para diferentes tipos de procedimentos
 * Encapsula a lógica de cálculo específica para procedimentos
 */