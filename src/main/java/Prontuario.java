import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Prontuario {

    private final ProntuariorepositoryCSV repository;

    private String nomePaciente;
    private Internacao internacao;
    private final List<Procedimento> procedimentos;

    public Prontuario(String nomePaciente) {
        this.nomePaciente = nomePaciente;
        this.procedimentos = new ArrayList<>();
        this.repository = new ProntuariorepositoryCSV();
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
        int valorTotalInternacao = internacao != null ? internacao.calcularValor() : 0;
        int valorTotalProcedimentos = procedimentos.stream().mapToInt(Procedimento::calcularValor).sum();
        int valorTotal = valorTotalProcedimentos + valorTotalInternacao;
        
        StringBuilder conta = new StringBuilder();
        conta.append("----------------------------------------------------------------------------------------------\n");
        conta.append("A conta do(a) paciente ").append(nomePaciente).append(" tem valor total de __ R$ ").append(String.format("%,.2f", (float) valorTotal)).append(" __\n\n");
        conta.append("Conforme os detalhes abaixo:\n\n");
    
        if (internacao != null) {
            conta.append("Valor Total Diárias:\t\t\tR$ ").append(valorTotalInternacao).append(",00").append("\n");
            conta.append("\t\t\t\t\t").append(internacao.getQtdeDias());
            if(internacao.getQtdeDias()>1)
                conta.append(" diárias em ");
            else
                conta.append(" diária em ");
            conta.append(internacao.getTipoLeito().equals(TipoLeito.APARTAMENTO) ? "apartamento" : "enfermaria").append("\n");
        }
        
        if (valorTotalProcedimentos != 0) {
            conta.append("Valor Total Procedimentos:\tR$  ").append(valorTotalProcedimentos).append(",00").append("\n");
        }
        
        // Loop para contar os procedimentos e exibir os detalhes
        for (TipoProcedimento tipo : TipoProcedimento.values()) {
            long count = procedimentos.stream().filter(p -> p.getTipoProcedimento() == tipo).count();
            if (count > 0) {
                // Exibe a quantidade do procedimento
                conta.append("\t\t\t\t").append(count).append(" procedimento");
                conta.append(count > 1 ? "s" : "").append(" ");
                
                // Exibe o tipo de procedimento
                if (tipo.equals(TipoProcedimento.COMUM)) {
                    conta.append(count > 1 ? "comuns" : "comum");
                } else {
                    conta.append(tipo.toString().toLowerCase());
                }
                conta.append("\n");
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
        prontuario.addProcedimento(new Procedimento(TipoProcedimento.BÁSICO));
        prontuario.addProcedimento(new Procedimento(TipoProcedimento.AVANÇADO));
        prontuario.addProcedimento(new Procedimento(TipoProcedimento.COMUM));

        // Imprimir a conta
        System.out.println(prontuario.imprimaConta());
    }

    public Prontuario carregueProntuario(String path) throws IOException {
        return repository.carregar(path);
        
    }

    public void setNomePaciente(String nomePaciente2) {
        this.nomePaciente = nomePaciente2;
    }

    public Map<TipoProcedimento, Long> getProcedimentosAgrupados() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProcedimentosAgrupados'");
    }
}