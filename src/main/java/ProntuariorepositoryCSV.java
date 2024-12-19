import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ProntuariorepositoryCSV implements IProntuarioRepository {
    @Override
    public Prontuario carregar(String arquivo) throws IOException {
        Path path = Paths.get(arquivo);
        Stream<String> linhas = Files.lines(path);
        Prontuario prontuario = new Prontuario(null);
        boolean primeiraLinha = true;

        Iterator<String> iterator = linhas.iterator();
        while (iterator.hasNext()) {
            String linha = iterator.next();
            if (primeiraLinha) {
                primeiraLinha = false;
                continue;
            }
            processarLinha(linha, prontuario);
        }

        linhas.close();
        return prontuario;
    }

    @Override
    public String salvar(Prontuario prontuario) throws IOException {
        List<String> linhas = new ArrayList<>();
        linhas.add("nome_paciente,tipo_leito,qtde_dias_internacao,tipo_procedimento,qtde_procedimentos");

        // Adiciona linha de internação se existir
        if (prontuario.getInternacao() != null) {
            linhas.add(String.format("%s,%s,%d,,",
                prontuario.getNomePaciente(),
                prontuario.getInternacao().getTipoLeito(),
                prontuario.getInternacao().getQtdeDias()));
        }

        // Adiciona linhas de procedimentos agrupados
        Map<TipoProcedimento, Long> procedimentosAgrupados = prontuario.getProcedimentosAgrupados();
        for (TipoProcedimento tipo : TipoProcedimento.values()) {
            if (procedimentosAgrupados.containsKey(tipo)) {
                linhas.add(String.format("%s,,,%s,%d",
                    prontuario.getNomePaciente(),
                    tipo,
                    procedimentosAgrupados.get(tipo)));
            }
        }

        // Se não tem nem internação nem procedimentos, adiciona linha vazia
        if (linhas.size() == 1) {
            linhas.add(String.format("%s,,,,", prontuario.getNomePaciente()));
        }

        String nomeArquivo = prontuario.getNomePaciente().replaceAll(" ", "_") + 
                            System.currentTimeMillis() + ".csv";
        Path path = Paths.get(nomeArquivo);
        Files.write(path, linhas);
        return path.toString();
    }

    private void processarLinha(String linha, Prontuario prontuario) {
        String[] dados = linha.split(",");
        String nomePaciente = dados[0].trim();
        prontuario.setNomePaciente(nomePaciente);

        // Processa internação
        String tipoLeito = dados[1].trim();
        String qtdeDias = dados[2].trim();
        if (!tipoLeito.isEmpty() && !qtdeDias.isEmpty()) {
            prontuario.setInternacao(new Internacao(
                TipoLeito.valueOf(tipoLeito),
                Integer.parseInt(qtdeDias)
            ));
        }

        // Processa procedimentos
        String tipoProcedimento = dados[3].trim();
        String qtdeProcedimentos = dados.length > 4 ? dados[4].trim() : "";
        if (!tipoProcedimento.isEmpty() && !qtdeProcedimentos.isEmpty()) {
            int qtde = Integer.parseInt(qtdeProcedimentos);
            TipoProcedimento tipo = TipoProcedimento.valueOf(tipoProcedimento);
            for (int i = 0; i < qtde; i++) {
                prontuario.addProcedimento(new Procedimento(tipo));
            }
        }
    }
}

/**
 * Implementação do repositório para arquivo CSV
 * - Responsável pela persistência em CSV
 * - Encapsula toda lógica de leitura/escrita
 * - Implementa a interface IProntuarioRepository
 */