import java.io.IOException;

public interface IProntuarioRepository {
    Prontuario carregar(String arquivo) throws IOException;
    String salvar(Prontuario prontuario) throws IOException;
}

/**
 * Interface que define o contrato para persistência de prontuários
 * Seguindo o princípio de Dependency Inversion do SOLID
 * Permite diferentes implementações de armazenamento (CSV, BD, etc)
 */