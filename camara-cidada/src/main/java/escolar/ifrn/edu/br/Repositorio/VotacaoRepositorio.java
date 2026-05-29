package escolar.ifrn.edu.br.Repositorio;

import java.util.ArrayList;
import java.util.List;

import escolar.ifrn.edu.br.Modelo.Votacao;

public class VotacaoRepositorio {

    private List<Votacao> votacoes =
        new ArrayList<>();

    public void salvar(Votacao votacao) {

        votacoes.add(votacao);
    }

    public List<Votacao> listarTodas() {

        return votacoes;
    }
}