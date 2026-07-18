package escolar.ifrn.edu.br.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Votacao {

    private Long id; // Identificador correspondente à Chave Primária do MySQL
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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Proposicao getProposicao() { return proposicao; }
    public void setProposicao(Proposicao proposicao) { this.proposicao = proposicao; }

    public List<Voto> getVotos() {
        return votos;
    }
}