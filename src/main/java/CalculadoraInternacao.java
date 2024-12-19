public class CalculadoraInternacao implements ICalculadora {
    private final TipoLeito tipoLeito;
    private final int qtdeDias;

    public CalculadoraInternacao(TipoLeito tipoLeito, int qtdeDias) {
        this.tipoLeito = tipoLeito;
        this.qtdeDias = qtdeDias;
    }

    @Override
    public float calcularValor() {
        if (tipoLeito == TipoLeito.ENFERMARIA) {
            return calcularValorEnfermaria();
        }
        return calcularValorApartamento();
    }

    private float calcularValorEnfermaria() {
        if (qtdeDias <= 3) return 40.00f * qtdeDias;
        if (qtdeDias <= 8) return 35.00f * qtdeDias;
        return 30.00f * qtdeDias;
    }

    private float calcularValorApartamento() {
        if (qtdeDias <= 3) return 100.00f * qtdeDias;
        if (qtdeDias <= 8) return 90.00f * qtdeDias;
        return 80.00f * qtdeDias;
    }
}

/**
 * Classe responsável pelo cálculo de valores de internação
 * Implementa o padrão Strategy para diferentes tipos de leito
 * Encapsula a lógica de cálculo específica para internações
 */