package escolar.ifrn.edu.br.Servico;

import java.util.List;

import escolar.ifrn.edu.br.Modelo.Parlamentar;
import escolar.ifrn.edu.br.Repositorio.ParlamentarRepositorio;

/**
 * Classe responsável pelas regras de negócio relacionadas aos Parlamentares.
 * Implementa as validações descritas nos Critérios de Aceitação do RF01.
 */
public class ParlamentarServico {

    private final ParlamentarRepositorio repositorio = new ParlamentarRepositorio();

    public void cadastrarParlamentar(Parlamentar parlamentar) {
        if (parlamentar.getNomeParlamentar() == null || parlamentar.getNomeParlamentar().trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de Regra: Nome parlamentar é obrigatório.");
        }
        if (parlamentar.getNomeCompleto() == null || parlamentar.getNomeCompleto().trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de Regra: Nome completo é obrigatório.");
        }
        if (parlamentar.getPartido() == null || parlamentar.getPartido().trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de Regra: Partido é obrigatório.");
        }

        Parlamentar existente = repositorio.buscarPorNomeParlamentar(parlamentar.getNomeParlamentar());
        if (existente != null && existente.getLegislatura() == parlamentar.getLegislatura()) {
            throw new IllegalArgumentException("Erro de Regra: Já existe parlamentar com esse nome nesta legislatura.");
        }

        repositorio.inserir(parlamentar);
    }

    public List<Parlamentar> listarParlamentares() {
        return repositorio.selecionarTodos();
    }

    public void alterarDadosParlamentar(Parlamentar parlamentar) {
        if (parlamentar.getId() == null) {
            throw new IllegalArgumentException("Erro de Regra: Não é possível atualizar um registro sem ID.");
        }
        repositorio.atualizar(parlamentar);
    }

    public void inativarParlamentar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Erro de Regra: ID inválido para inativação.");
        }
        Parlamentar parlamentar = buscarPorId(id);
        parlamentar.inativar();
        repositorio.atualizar(parlamentar);
    }

    private Parlamentar buscarPorId(Long id) {
        for (Parlamentar parlamentar : repositorio.selecionarTodos()) {
            if (parlamentar.getId().equals(id)) {
                return parlamentar;
            }
        }
        throw new IllegalArgumentException("Erro de Regra: Parlamentar não encontrado.");
    }

}