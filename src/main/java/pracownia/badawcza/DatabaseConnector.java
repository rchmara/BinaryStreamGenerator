package pracownia.badawcza;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by tomas on 21.05.2017.
 */
public class DatabaseConnector {

    private MysqlDataSource mysqlDataSource;
    private Statement statement;
    private Connection connection;

    public DatabaseConnector() {
    }

    public Statement getStatement() {
        if (mysqlDataSource == null) {
            mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setUser("root");
            mysqlDataSource.setPassword("postgres");
            mysqlDataSource.setServerName("localhost");
            mysqlDataSource.setPort(3307);
        }

        try {
            connection = mysqlDataSource.getConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public void closeConnection() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
