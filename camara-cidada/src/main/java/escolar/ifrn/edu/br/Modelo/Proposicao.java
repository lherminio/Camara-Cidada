package escolar.ifrn.edu.br.Modelo;

public class Proposicao {

    private Long id; // Identificador correspondente à Chave Primária do MySQL
    private String titulo;
    private int numeroProjeto;
    private int ano;
    private String autor;
    private String ementa;
    private String categoria;
    private String status;
    private String pdfProjeto;

    public boolean isArquivado() {
        return status != null && status.equalsIgnoreCase("Arquivado");
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public int getNumeroProjeto() { return numeroProjeto; }
    public void setNumeroProjeto(int numeroProjeto) { this.numeroProjeto = numeroProjeto; }

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getEmenta() { return ementa; }
    public void setEmenta(String ementa) { this.ementa = ementa; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPdfProjeto() { return pdfProjeto; }
    public void setPdfProjeto(String pdfProjeto) { this.pdfProjeto = pdfProjeto; }

    @Override
    public String toString() {
    return "Proposicao{id=" + id + ", numeroProjeto=" + numeroProjeto + "/" + ano +
           ", titulo='" + titulo + "', autor='" + autor + "', status='" + status + "'}";}
}