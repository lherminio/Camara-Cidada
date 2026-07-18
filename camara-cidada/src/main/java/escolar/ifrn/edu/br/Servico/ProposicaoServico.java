package escolar.ifrn.edu.br.Servico;

import java.util.List;

import escolar.ifrn.edu.br.Modelo.Proposicao;
import escolar.ifrn.edu.br.Repositorio.ProposicaoRepositorio;

/**
 * Classe responsável pelas regras de negócio relacionadas às Proposições.
 * Implementa as validações descritas nos Critérios de Aceitação do RF02.
 */
public class ProposicaoServico {

    private final ProposicaoRepositorio repositorio = new ProposicaoRepositorio();

    public void cadastrarProposicao(Proposicao proposicao) {
        if (proposicao.getTitulo() == null || proposicao.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de Regra: Título é obrigatório.");
        }
        if (proposicao.getAutor() == null || proposicao.getAutor().trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de Regra: Autor é obrigatório.");
        }
        if (proposicao.getStatus() == null || proposicao.getStatus().trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de Regra: Status inicial é obrigatório.");
        }

        repositorio.inserir(proposicao);
    }

    public List<Proposicao> listarProposicoes() {
        return repositorio.selecionarTodas();
    }

    public List<Proposicao> pesquisarPorTituloOuEmenta(String termo) {
        if (termo == null || termo.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de Regra: Termo de busca é obrigatório.");
        }
        return repositorio.buscarPorTituloOuEmenta(termo);
    }

    public void alterarDadosProposicao(Proposicao proposicao) {
        if (proposicao.getId() == null) {
            throw new IllegalArgumentException("Erro de Regra: Não é possível atualizar um registro sem ID.");
        }
        if (proposicao.isArquivado()) {
            System.out.println("LOG ALERTA: A proposição '" + proposicao.getTitulo() + "' foi arquivada!");
        }
        repositorio.atualizar(proposicao);
    }

    public void removerProposicao(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Erro de Regra: ID inválido para exclusão.");
        }
        repositorio.excluir(id);
    }

}