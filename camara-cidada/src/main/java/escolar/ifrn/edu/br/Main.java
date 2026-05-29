package escolar.ifrn.edu.br;

import escolar.ifrn.edu.br.Modelo.Parlamentar;
import escolar.ifrn.edu.br.Servico.ParlamentarServico;

public class Main {

    public static void main(String[] args) {

        Parlamentar parlamentar =
            new Parlamentar();

        parlamentar.setNomeCompleto(
            "João da Silva"
        );

        parlamentar.setNomeParlamentar(
            "João Silva"
        );

        parlamentar.setPartido(
            "PT"
        );

        parlamentar.setAtivo(true);

        ParlamentarServico servico =
            new ParlamentarServico();

        servico.cadastrarParlamentar(
            parlamentar
        );
    }
}