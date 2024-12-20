import java.util.HashMap;
import java.util.Map;

public class CalculadoraProcedimento implements ICalculadora {
    private final TipoProcedimento tipo;

    // Mapa para associar tipo de procedimento a seu valor
    private static final Map<TipoProcedimento, Float> valoresProcedimentos = new HashMap<>();

    static {
        // Preenche o mapa com os valores dos procedimentos
        valoresProcedimentos.put(TipoProcedimento.BÁSICO, 50.00f);
        valoresProcedimentos.put(TipoProcedimento.COMUM, 150.00f);
        valoresProcedimentos.put(TipoProcedimento.AVANÇADO, 500.00f);
    }

    public CalculadoraProcedimento(TipoProcedimento tipo) {
        this.tipo = tipo;
    }

    @Override
    public float calcularValor() {
        // Retorna o valor diretamente do mapa, caso o tipo seja encontrado
        return valoresProcedimentos.getOrDefault(tipo, 0.00f);
    }
}

/**
 * Classe responsável pelo cálculo de valores de procedimentos
 * Implementa o padrão Strategy para diferentes tipos de procedimentos
 * Encapsula a lógica de cálculo específica para procedimentos
 */