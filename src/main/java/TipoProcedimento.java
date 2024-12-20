public enum TipoProcedimento {
    BÁSICO(50),
    COMUM(150),
    AVANÇADO(500);

    private final int valor;

    TipoProcedimento(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
