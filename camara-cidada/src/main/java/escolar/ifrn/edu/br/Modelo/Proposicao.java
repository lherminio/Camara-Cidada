package escolar.ifrn.edu.br.Modelo;

public class Proposicao {

    private String titulo;
    private int numeroProjeto;
    private int ano;
    private String autor;
    private String ementa;
    private String categoria;
    private String status;
    private String pdfProjeto;

    public boolean isArquivado() {
        return status.equalsIgnoreCase("Arquivado");
    }

    // getters e setters
}