public enum TipoLeito {
    APARTAMENTO(90),  // Valor da diária
    ENFERMARIA(40);   // Valor da diária

    private final int valorDiaria;

    TipoLeito(int valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public int getValorDiaria() {
        return valorDiaria;
    }
}
