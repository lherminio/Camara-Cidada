package escolar.ifrn.edu.br.Repositorio;

import java.util.ArrayList;
import java.util.List;

import escolar.ifrn.edu.br.Modelo.Proposicao;

public class ProposicaoRepositorio {

    private List<Proposicao> proposicoes =
        new ArrayList<>();

    public void salvar(Proposicao proposicao) {

        proposicoes.add(proposicao);
    }

    public List<Proposicao> listarTodas() {

        return proposicoes;
    }
}