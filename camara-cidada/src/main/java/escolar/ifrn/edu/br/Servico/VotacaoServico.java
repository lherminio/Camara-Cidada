package escolar.ifrn.edu.br.Servico;

import escolar.ifrn.edu.br.Modelo.Votacao;

public class VotacaoServico {

    public void registrarVotacao(
            Votacao votacao) {

        if (votacao == null) {

            throw new IllegalArgumentException(
                "Votação inválida."
            );
        }

        System.out.println(
            votacao.calcularResultado()
        );
    }
}