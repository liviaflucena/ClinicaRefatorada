public enum TipoProcedimento {
    BASICO(100),
    COMUM(150),
    AVANCADO(200);

    private final int valor;

    TipoProcedimento(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
