public class Procedimento {
    private final TipoProcedimento tipoProcedimento;

    public Procedimento(TipoProcedimento tipoProcedimento) {
        this.tipoProcedimento = tipoProcedimento;
    }

    public TipoProcedimento getTipoProcedimento() {
        return tipoProcedimento;
    }

    public int calcularValor() {
        return tipoProcedimento.getValor();
    }
}

/**
 * Classe que representa um procedimento
 * - Imutável para garantir consistência
 * - Usa composição com ICalculadora para delegar cálculos
 * - Mantém apenas os dados essenciais do procedimento
 */