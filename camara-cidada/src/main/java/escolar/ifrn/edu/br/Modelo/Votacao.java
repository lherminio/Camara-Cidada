package escolar.ifrn.edu.br.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Votacao {

    private Proposicao proposicao;
    private List<Voto> votos = new ArrayList<>();

    public String calcularResultado() {

        int sim = 0;
        int nao = 0;

        for (Voto voto : votos) {

            if (voto.getVoto().equalsIgnoreCase("SIM")) {
                sim++;
            }

            if (voto.getVoto().equalsIgnoreCase("NAO")) {
                nao++;
            }
        }

        if (sim > nao) {
            return "APROVADO";
        }

        return "REPROVADO";
    }

    public List<Voto> getVotos() {
        return votos;
    }
}