package escolar.ifrn.edu.br.Repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import escolar.ifrn.edu.br.Modelo.Parlamentar;

public class ParlamentarRepositorio {

    private Connection getConnection() throws SQLException {
        return GerenciadorDeConexao.getConnection();
    }

    // [C] - INSERIR (INSERT)
    public void inserir(Parlamentar parlamentar) {
        String sql = "INSERT INTO parlamentar (nome_completo, nome_parlamentar, partido, biografia, foto, ativo, legislatura) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, parlamentar.getNomeCompleto());
            stmt.setString(2, parlamentar.getNomeParlamentar());
            stmt.setString(3, parlamentar.getPartido());
            stmt.setString(4, parlamentar.getBiografia());
            stmt.setString(5, parlamentar.getFoto());
            stmt.setBoolean(6, parlamentar.isAtivo());
            stmt.setInt(7, parlamentar.getLegislatura());
            stmt.executeUpdate();

            // Recupera o ID gerado pelo auto_increment do MySQL e guarda no objeto Java
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    parlamentar.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir dados no MySQL", e);
        }
    }

    // [R] - SELECIONAR / LISTAR (SELECT)
    public List<Parlamentar> selecionarTodos() {
        List<Parlamentar> parlamentares = new ArrayList<>();
        String sql = "SELECT * FROM parlamentar";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Parlamentar parlamentar = new Parlamentar();
                parlamentar.setId(rs.getLong("id"));
                parlamentar.setNomeCompleto(rs.getString("nome_completo"));
                parlamentar.setNomeParlamentar(rs.getString("nome_parlamentar"));
                parlamentar.setPartido(rs.getString("partido"));
                parlamentar.setBiografia(rs.getString("biografia"));
                parlamentar.setFoto(rs.getString("foto"));
                parlamentar.setAtivo(rs.getBoolean("ativo"));
                parlamentar.setLegislatura(rs.getInt("legislatura"));
                parlamentares.add(parlamentar);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao selecionar dados do MySQL", e);
        }
        return parlamentares;
    }

    // Busca usada pelo Serviço para validar nome parlamentar duplicado (RF01)
    public Parlamentar buscarPorNomeParlamentar(String nomeParlamentar) {
        String sql = "SELECT * FROM parlamentar WHERE nome_parlamentar = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomeParlamentar);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Parlamentar parlamentar = new Parlamentar();
                    parlamentar.setId(rs.getLong("id"));
                    parlamentar.setNomeCompleto(rs.getString("nome_completo"));
                    parlamentar.setNomeParlamentar(rs.getString("nome_parlamentar"));
                    parlamentar.setPartido(rs.getString("partido"));
                    parlamentar.setBiografia(rs.getString("biografia"));
                    parlamentar.setFoto(rs.getString("foto"));
                    parlamentar.setAtivo(rs.getBoolean("ativo"));
                    parlamentar.setLegislatura(rs.getInt("legislatura"));
                    return parlamentar;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar parlamentar no MySQL", e);
        }
        return null;
    }

    // [U] - ATUALIZAR (UPDATE)
    public void atualizar(Parlamentar parlamentar) {
        String sql = "UPDATE parlamentar SET nome_completo = ?, nome_parlamentar = ?, partido = ?, " +
                     "biografia = ?, foto = ?, ativo = ?, legislatura = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, parlamentar.getNomeCompleto());
            stmt.setString(2, parlamentar.getNomeParlamentar());
            stmt.setString(3, parlamentar.getPartido());
            stmt.setString(4, parlamentar.getBiografia());
            stmt.setString(5, parlamentar.getFoto());
            stmt.setBoolean(6, parlamentar.isAtivo());
            stmt.setInt(7, parlamentar.getLegislatura());
            stmt.setLong(8, parlamentar.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dados no MySQL", e);
        }
    }

    // [D] - EXCLUIR (DELETE)
    public void excluir(Long id) {
        String sql = "DELETE FROM parlamentar WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir dados do MySQL", e);
        }
    }

}