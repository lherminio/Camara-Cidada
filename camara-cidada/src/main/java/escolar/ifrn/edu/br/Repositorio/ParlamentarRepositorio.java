package escolar.ifrn.edu.br.Repositorio;

import java.util.ArrayList;
import java.util.List;

import escolar.ifrn.edu.br.Modelo.Parlamentar;

public class ParlamentarRepositorio {

    private List<Parlamentar> parlamentares =
        new ArrayList<>();

    public void salvar(Parlamentar parlamentar) {

        parlamentares.add(parlamentar);
    }

    public List<Parlamentar> listarTodos() {

        return parlamentares;
    }

    public Parlamentar buscarPorNomeParlamentar(
            String nomeParlamentar) {

        for (Parlamentar parlamentar : parlamentares) {

            if (parlamentar.getNomeParlamentar()
                    .equalsIgnoreCase(nomeParlamentar)) {

                return parlamentar;
            }
        }

        return null;
    }
}