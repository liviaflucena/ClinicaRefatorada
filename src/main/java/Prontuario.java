import java.util.ArrayList;
import java.util.List;

public class Prontuario {
    private final String nomePaciente;
    private Internacao internacao;
    private final List<Procedimento> procedimentos;

    public Prontuario(String nomePaciente) {
        this.nomePaciente = nomePaciente;
        this.procedimentos = new ArrayList<>();
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public Internacao getInternacao() {
        return internacao;
    }

    public void setInternacao(Internacao internacao) {
        this.internacao = internacao;
    }

    public List<Procedimento> getProcedimentos() {
        return procedimentos;
    }

    public void addProcedimento(Procedimento procedimento) {
        this.procedimentos.add(procedimento);
    }

    public String imprimaConta() {
        int valorTotalProcedimentos = procedimentos.stream().mapToInt(Procedimento::calcularValor).sum();
        int valorTotalInternacao = internacao != null ? internacao.calcularValor() : 0;
        int valorTotal = valorTotalProcedimentos + valorTotalInternacao;

        StringBuilder conta = new StringBuilder();
        conta.append("----------------------------------------------------------------------------------------------\n");
        conta.append("A conta do(a) paciente ").append(nomePaciente).append(" tem valor total de __ R$ ").append(valorTotal).append(" __\n\n");
        conta.append("Conforme os detalhes abaixo:\n\n");

        if (internacao != null) {
            conta.append("Valor Total Diárias:\t\tR$ ").append(valorTotalInternacao).append("\n");
            conta.append("\t\t\t\t").append(internacao.getQtdeDias()).append(" diárias em ").append(internacao.getTipoLeito()).append("\n");
        }

        conta.append("Valor Total Procedimentos:\tR$ ").append(valorTotalProcedimentos).append("\n");

        for (TipoProcedimento tipo : TipoProcedimento.values()) {
            long count = procedimentos.stream().filter(p -> p.getTipoProcedimento() == tipo).count();
            if (count > 0) {
                conta.append("\t\t\t\t").append(count).append(" procedimento").append(count > 1 ? "s" : "").append(" ").append(tipo).append("\n");
            }
        }

        conta.append("\nVolte sempre, a casa é sua!\n");
        conta.append("----------------------------------------------------------------------------------------------");

        return conta.toString();
    }

    public static void main(String[] args) {
        // Criar um prontuário para o paciente
        Prontuario prontuario = new Prontuario("João da Silva");

        // Adicionar internação
        prontuario.setInternacao(new Internacao(TipoLeito.ENFERMARIA, 5));

        // Adicionar procedimentos
        prontuario.addProcedimento(new Procedimento(TipoProcedimento.BASICO));
        prontuario.addProcedimento(new Procedimento(TipoProcedimento.AVANCADO));
        prontuario.addProcedimento(new Procedimento(TipoProcedimento.COMUM));

        // Imprimir a conta
        System.out.println(prontuario.imprimaConta());
    }

    public Prontuario carregueProntuario(String path) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'carregueProntuario'");
    }
}
