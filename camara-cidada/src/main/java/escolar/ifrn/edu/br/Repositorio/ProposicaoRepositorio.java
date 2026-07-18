package escolar.ifrn.edu.br.Repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import escolar.ifrn.edu.br.Modelo.Proposicao;

public class ProposicaoRepositorio {

    private Connection getConnection() throws SQLException {
        return GerenciadorDeConexao.getConnection();
    }

    // [C] - INSERIR (INSERT)
    public void inserir(Proposicao proposicao) {
        String sql = "INSERT INTO proposicao (titulo, numero_projeto, ano, autor, ementa, categoria, status, pdf_projeto) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, proposicao.getTitulo());
            stmt.setInt(2, proposicao.getNumeroProjeto());
            stmt.setInt(3, proposicao.getAno());
            stmt.setString(4, proposicao.getAutor());
            stmt.setString(5, proposicao.getEmenta());
            stmt.setString(6, proposicao.getCategoria());
            stmt.setString(7, proposicao.getStatus());
            stmt.setString(8, proposicao.getPdfProjeto());
            stmt.executeUpdate();

            // Recupera o ID gerado pelo auto_increment do MySQL e guarda no objeto Java
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    proposicao.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir dados no MySQL", e);
        }
    }

    // [R] - SELECIONAR / LISTAR (SELECT)
    public List<Proposicao> selecionarTodas() {
        List<Proposicao> proposicoes = new ArrayList<>();
        String sql = "SELECT * FROM proposicao";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                proposicoes.add(mapearProposicao(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao selecionar dados do MySQL", e);
        }
        return proposicoes;
    }

    // Busca usada pela Consulta Pública (RF04) — pesquisa por título ou ementa
    public List<Proposicao> buscarPorTituloOuEmenta(String termo) {
        List<Proposicao> proposicoes = new ArrayList<>();
        String sql = "SELECT * FROM proposicao WHERE titulo LIKE ? OR ementa LIKE ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String termoBusca = "%" + termo + "%";
            stmt.setString(1, termoBusca);
            stmt.setString(2, termoBusca);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    proposicoes.add(mapearProposicao(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar proposições no MySQL", e);
        }
        return proposicoes;
    }

    // [U] - ATUALIZAR (UPDATE)
    public void atualizar(Proposicao proposicao) {
        String sql = "UPDATE proposicao SET titulo = ?, numero_projeto = ?, ano = ?, autor = ?, " +
                     "ementa = ?, categoria = ?, status = ?, pdf_projeto = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, proposicao.getTitulo());
            stmt.setInt(2, proposicao.getNumeroProjeto());
            stmt.setInt(3, proposicao.getAno());
            stmt.setString(4, proposicao.getAutor());
            stmt.setString(5, proposicao.getEmenta());
            stmt.setString(6, proposicao.getCategoria());
            stmt.setString(7, proposicao.getStatus());
            stmt.setString(8, proposicao.getPdfProjeto());
            stmt.setLong(9, proposicao.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dados no MySQL", e);
        }
    }

    // [D] - EXCLUIR (DELETE)
    public void excluir(Long id) {
        String sql = "DELETE FROM proposicao WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir dados do MySQL", e);
        }
    }

    // Método auxiliar: evita repetir o mapeamento ResultSet -> Objeto em cada método de leitura
    private Proposicao mapearProposicao(ResultSet rs) throws SQLException {
        Proposicao proposicao = new Proposicao();
        proposicao.setId(rs.getLong("id"));
        proposicao.setTitulo(rs.getString("titulo"));
        proposicao.setNumeroProjeto(rs.getInt("numero_projeto"));
        proposicao.setAno(rs.getInt("ano"));
        proposicao.setAutor(rs.getString("autor"));
        proposicao.setEmenta(rs.getString("ementa"));
        proposicao.setCategoria(rs.getString("categoria"));
        proposicao.setStatus(rs.getString("status"));
        proposicao.setPdfProjeto(rs.getString("pdf_projeto"));
        return proposicao;
    }

}