public enum TipoProcedimento {
    BÁSICO(100),
    COMUM(150),
    AVANÇADO(200);

    private final int valor;

    TipoProcedimento(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
