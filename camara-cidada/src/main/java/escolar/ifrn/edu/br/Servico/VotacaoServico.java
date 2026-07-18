package escolar.ifrn.edu.br.Servico;

import java.util.List;

import escolar.ifrn.edu.br.Modelo.Votacao;
import escolar.ifrn.edu.br.Repositorio.VotacaoRepositorio;

/**
 * Classe responsável pelas regras de negócio relacionadas às Votações.
 * Implementa as validações descritas nos Critérios de Aceitação do RF03.
 */
public class VotacaoServico {

    private final VotacaoRepositorio repositorio = new VotacaoRepositorio();

    public void registrarVotacao(Votacao votacao) {
        if (votacao == null) {
            throw new IllegalArgumentException("Erro de Regra: Votação inválida.");
        }
        if (votacao.getProposicao() == null) {
            throw new IllegalArgumentException("Erro de Regra: A votação deve estar vinculada a uma proposição cadastrada.");
        }
        if (votacao.getVotos() == null || votacao.getVotos().isEmpty()) {
            throw new IllegalArgumentException("Erro de Regra: A votação precisa ter ao menos um voto registrado.");
        }

        repositorio.inserir(votacao);
        System.out.println("Resultado da votação: " + votacao.calcularResultado());
    }

    public List<Votacao> listarVotacoes() {
        return repositorio.selecionarTodas();
    }

    public void removerVotacao(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Erro de Regra: ID inválido para exclusão.");
        }
        repositorio.excluir(id);
    }

}