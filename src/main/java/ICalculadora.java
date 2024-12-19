public interface ICalculadora {
    float calcularValor();
}

/**
 * Esta interface define o contrato básico para cálculos de valores
 * Seguindo o princípio de Interface Segregation do SOLID
 * Cada implementação será responsável por seu próprio cálculo
 */