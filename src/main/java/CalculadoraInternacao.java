import java.util.HashMap;
import java.util.Map;

public class CalculadoraInternacao implements ICalculadora {
    private final TipoLeito tipoLeito;
    private final int qtdeDias;

    // Mapa para associar tipo de leito a uma função de cálculo
    private static final Map<TipoLeito, LeitoCalculador> calculoLeitos = new HashMap<>();

    static {
        // Inicializando os cálculos para cada tipo de leito
        calculoLeitos.put(TipoLeito.ENFERMARIA, (qtdeDias) -> calcularValorEnfermaria(qtdeDias));
        calculoLeitos.put(TipoLeito.APARTAMENTO, (qtdeDias) -> calcularValorApartamento(qtdeDias));
    }

    public CalculadoraInternacao(TipoLeito tipoLeito, int qtdeDias) {
        this.tipoLeito = tipoLeito;
        this.qtdeDias = qtdeDias;
    }

    @Override
    public float calcularValor() {
        // Obtém a função de cálculo associada ao tipo de leito e calcula o valor
        LeitoCalculador calculador = calculoLeitos.get(tipoLeito);
        return calculador != null ? calculador.calcularValor(qtdeDias) : 0.00f;
    }

    // Função de cálculo de enfermaria
    private static float calcularValorEnfermaria(int qtdeDias) {
        if (qtdeDias <= 3) return 40.00f * qtdeDias;
        if (qtdeDias <= 8) return 35.00f * qtdeDias;
        return 30.00f * qtdeDias;
    }

    // Função de cálculo de apartamento
    private static float calcularValorApartamento(int qtdeDias) {
        if (qtdeDias <= 3) return 100.00f * qtdeDias;
        if (qtdeDias <= 8) return 90.00f * qtdeDias;
        return 80.00f * qtdeDias;
    }

    // Interface funcional para os cálculos de leito
    @FunctionalInterface
    interface LeitoCalculador {
        float calcularValor(int qtdeDias);
    }
}


/**
 * Classe responsável pelo cálculo de valores de internação
 * Implementa o padrão Strategy para diferentes tipos de leito
 * Encapsula a lógica de cálculo específica para internações
 */