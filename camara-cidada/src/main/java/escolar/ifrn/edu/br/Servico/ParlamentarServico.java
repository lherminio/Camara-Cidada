package escolar.ifrn.edu.br.Servico;

import escolar.ifrn.edu.br.Modelo.Parlamentar;
import escolar.ifrn.edu.br.Repositorio.ParlamentarRepositorio;

public class ParlamentarServico {

    private ParlamentarRepositorio repositorio =
        new ParlamentarRepositorio();

    public void cadastrarParlamentar(
            Parlamentar parlamentar) {

        if (parlamentar.getNomeParlamentar() == null ||
            parlamentar.getNomeParlamentar().isEmpty()) {

            throw new IllegalArgumentException(
                "Nome parlamentar obrigatório."
            );
        }

        Parlamentar existente =
            repositorio.buscarPorNomeParlamentar(
                parlamentar.getNomeParlamentar()
            );

        if (existente != null) {

            throw new IllegalArgumentException(
                "Já existe parlamentar com esse nome."
            );
        }

        repositorio.salvar(parlamentar);

        System.out.println(
            "Parlamentar cadastrado!"
        );
    }
}