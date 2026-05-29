package escolar.ifrn.edu.br.Servico;

import escolar.ifrn.edu.br.Modelo.Proposicao;

public class ProposicaoServico {

    public void cadastrarProposicao(
            Proposicao proposicao) {

        if (proposicao.getStatus() == null) {

            throw new IllegalArgumentException(
                "Status obrigatório."
            );
        }

        System.out.println(
            "Proposição cadastrada!"
        );
    }
}