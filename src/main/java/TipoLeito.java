public enum TipoLeito {
    APARTAMENTO(90),  
    ENFERMARIA(40);   

    private final int valorDiaria;

    TipoLeito(int valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public int getValorDiaria() {
        return valorDiaria;
    }
}
