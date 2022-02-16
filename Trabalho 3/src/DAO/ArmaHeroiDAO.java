package DAO;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.ArmaHeroi;
import util.JDBCUtil;

/**
 * @author fabricio
 */
public class ArmaHeroiDAO {

    private Connection connection = null;
    private PreparedStatement pstdados = null;
    private ResultSet rsdados = null;
    private static final String path = System.getProperty("user.dir");
    private static final File config_file = new File(path + "/src/bancodados/configuracaobd.properties");
    private static final String sqlconsultaArmaHeroi = "SELECT * FROM armaheroi order by nome";
    private static final String sqlinserir = "INSERT INTO armaheroi (id, nome, dano, municao, longaDistancia) VALUES (?, ?, ?, ?, ?)";
    private static final String sqlalterar = "UPDATE armaheroi SET nome = ?, dano = ?, municao = ?, longaDistancia = ? WHERE id = ?";
    private static final String sqlaexcluir = "DELETE FROM armaheroi WHERE id = ?";
    private static final String sqlbuscaarma = "SELECT * FROM armaheroi WHERE nome = ?";

    public ArmaHeroiDAO() {

    }

    public boolean CriaConexao() {
        try {
            JDBCUtil.init(config_file);
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(false);//configuracao necessaria para confirmacao ou nao de alteracoes no banco de dados.

            DatabaseMetaData dbmt = connection.getMetaData();
            System.out.println("Nome do BD: " + dbmt.getDatabaseProductName());
            System.out.println("Versao do BD: " + dbmt.getDatabaseProductVersion());
            System.out.println("URL: " + dbmt.getURL());
            System.out.println("Driver: " + dbmt.getDriverName());
            System.out.println("Versao Driver: " + dbmt.getDriverVersion());
            System.out.println("Usuario: " + dbmt.getUserName());

            return true;
        } catch (ClassNotFoundException erro) {
            System.out.println("Falha ao carregar o driver JDBC." + erro);
        } catch (IOException erro) {
            System.out.println("Falha ao carregar o arquivo de configuração." + erro);
        } catch (SQLException erro) {
            System.out.println("Falha na conexao, comando sql = " + erro);
        }
        return false;
    }

    public boolean FechaConexao() {
        if (connection != null) {
            try {
                connection.close();
                return true;
            } catch (SQLException erro) {
                System.err.println("Erro ao fechar a conexão = " + erro);
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean Inserir(ArmaHeroi arma) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlinserir, tipo, concorrencia);
            pstdados.setInt(1, arma.getId());
            pstdados.setString(2, arma.getNome());
            pstdados.setInt(3, arma.getDano());
            pstdados.setInt(4, arma.getMunicao());
            pstdados.setBoolean(5, arma.isLongaDistancia());
            int resposta = pstdados.executeUpdate();
            pstdados.close();
            //DEBUG
            System.out.println("Resposta da inserção = " + resposta);
            //FIM-DEBUG
            if (resposta == 1) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException erro) {
            System.out.println("Erro na execução da inserção = " + erro);
        }
        return false;
    }

    public boolean Alterar(ArmaHeroi arma) {
       try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlalterar, tipo, concorrencia);
            pstdados.setString(1, arma.getNome());
            pstdados.setInt(2, arma.getDano());
            pstdados.setInt(3, arma.getMunicao());
            pstdados.setBoolean(4, arma.isLongaDistancia());
            pstdados.setInt(5, arma.getId());
            int resposta = pstdados.executeUpdate();
            pstdados.close();
            //DEBUG
            System.out.println("Resposta da atualização = " + resposta);
            //FIM-DEBUG
            if (resposta == 1) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException erro) {
            System.out.println("Erro na execução da atualização = " + erro);
        }
        return false;
    }

    public boolean Excluir(ArmaHeroi arma) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlaexcluir, tipo, concorrencia);
            pstdados.setInt(1, arma.getId());
            int resposta = pstdados.executeUpdate();
            pstdados.close();
            //DEBUG
            System.out.println("Resposta da exclusão = " + resposta);
            //FIM-DEBUG
            if (resposta == 1) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException erro) {
            System.out.println("Erro na execução da exclusão = " + erro);
        }
        return false;
    }

    public boolean ConsultarTodos() {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlconsultaArmaHeroi, tipo, concorrencia);
            rsdados = pstdados.executeQuery();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
        }
        return false;
    }
    
    public boolean BuscaArma(String busca) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlbuscaarma, tipo, concorrencia);
            pstdados.setString(1, busca);
            rsdados = pstdados.executeQuery();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
        }
        return false;
    }

    public ArmaHeroi getArmaHeroi() {
        ArmaHeroi arma = null;
        if (rsdados != null) {
            try {
                int id = rsdados.getInt("id");
                String nome = rsdados.getString("nome");
                int dano = rsdados.getInt("dano");
                int municao = rsdados.getInt("municao");
                boolean longaDistancia = rsdados.getBoolean("longaDistancia");
                arma = new ArmaHeroi(id, nome, dano, municao, longaDistancia);
            } catch (SQLException erro) {
                System.out.println(erro);
            }
        }
        if (rsdados == null)
            System.out.println("atencao: rsdados nulo!");
        return arma;
    }

    /**
     * @return the rsdados
     */
    public ResultSet getRsdados() {
        return rsdados;
    }

}
