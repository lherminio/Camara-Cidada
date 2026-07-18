package escolar.ifrn.edu.br.Repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import escolar.ifrn.edu.br.Modelo.Parlamentar;
import escolar.ifrn.edu.br.Modelo.Proposicao;
import escolar.ifrn.edu.br.Modelo.Votacao;
import escolar.ifrn.edu.br.Modelo.Voto;

public class VotacaoRepositorio {

    // Usado para buscar o Parlamentar completo de cada Voto ao carregar do banco
    private ParlamentarRepositorio parlamentarRepositorio = new ParlamentarRepositorio();

    private Connection getConnection() throws SQLException {
        return GerenciadorDeConexao.getConnection();
    }

    // [C] - INSERIR (INSERT) - votação e seus votos, em uma única operação
    public void inserir(Votacao votacao) {
        String sqlVotacao = "INSERT INTO votacao (numero_projeto, ano, resultado) VALUES (?, ?, ?)";
        String sqlVoto = "INSERT INTO voto (votacao_id, nome_parlamentar, voto) VALUES (?, ?, ?)";

        try (Connection conn = getConnection()) {

            Long votacaoId;

            // 1º passo: insere a votação e recupera o ID gerado
            try (PreparedStatement stmt = conn.prepareStatement(sqlVotacao, Statement.RETURN_GENERATED_KEYS)) {
                Proposicao proposicao = votacao.getProposicao();
                stmt.setInt(1, proposicao.getNumeroProjeto());
                stmt.setInt(2, proposicao.getAno());
                stmt.setString(3, votacao.calcularResultado());
                stmt.executeUpdate();

                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (!generatedKeys.next()) {
                        throw new SQLException("Falha ao gerar ID da votação.");
                    }
                    votacaoId = generatedKeys.getLong(1);
                    votacao.setId(votacaoId);
                }
            }

            // 2º passo: insere cada voto vinculado ao ID da votação
            try (PreparedStatement stmt = conn.prepareStatement(sqlVoto)) {
                for (Voto voto : votacao.getVotos()) {
                    stmt.setLong(1, votacaoId);
                    stmt.setString(2, voto.getParlamentar().getNomeParlamentar());
                    stmt.setString(3, voto.getVoto());
                    stmt.addBatch();
                }
                stmt.executeBatch();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir votação no MySQL", e);
        }
    }

    // [R] - SELECIONAR / LISTAR (SELECT) - reconstrói cada Votacao com sua lista de Votos
    public List<Votacao> selecionarTodas() {
        List<Votacao> votacoes = new ArrayList<>();
        String sqlVotacoes = "SELECT * FROM votacao";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlVotacoes)) {

            while (rs.next()) {
                Votacao votacao = new Votacao();
                votacao.setId(rs.getLong("id"));

                // Reconstrói a Proposicao apenas com os dados de referência disponíveis
                Proposicao proposicao = new Proposicao();
                proposicao.setNumeroProjeto(rs.getInt("numero_projeto"));
                proposicao.setAno(rs.getInt("ano"));
                votacao.setProposicao(proposicao);

                carregarVotos(conn, votacao);
                votacoes.add(votacao);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao selecionar votações do MySQL", e);
        }
        return votacoes;
    }

    // Método auxiliar: carrega os votos de uma votação específica e preenche votacao.getVotos()
    private void carregarVotos(Connection conn, Votacao votacao) throws SQLException {
        String sqlVotos = "SELECT * FROM voto WHERE votacao_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sqlVotos)) {
            stmt.setLong(1, votacao.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String nomeParlamentar = rs.getString("nome_parlamentar");
                    Parlamentar parlamentar = parlamentarRepositorio.buscarPorNomeParlamentar(nomeParlamentar);

                    Voto voto = new Voto();
                    voto.setId(rs.getLong("id"));
                    voto.setParlamentar(parlamentar);
                    voto.setVoto(rs.getString("voto"));

                    votacao.getVotos().add(voto);
                }
            }
        }
    }

    // [D] - EXCLUIR (DELETE) - remove os votos primeiro, depois a votação (integridade referencial)
    public void excluir(Long id) {
        String sqlDeleteVotos = "DELETE FROM voto WHERE votacao_id = ?";
        String sqlDeleteVotacao = "DELETE FROM votacao WHERE id = ?";

        try (Connection conn = getConnection()) {

            try (PreparedStatement stmt = conn.prepareStatement(sqlDeleteVotos)) {
                stmt.setLong(1, id);
                stmt.executeUpdate();
            }

            try (PreparedStatement stmt = conn.prepareStatement(sqlDeleteVotacao)) {
                stmt.setLong(1, id);
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir votação do MySQL", e);
        }
    }

}