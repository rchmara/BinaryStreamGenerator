package pracownia.badawcza;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by tomas on 21.05.2017.
 */
public class DatabaseConnector {

    private MysqlDataSource mysqlDataSource;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private Connection connection;

    public DatabaseConnector() {
    }

    private void initDataSource() {
        if (mysqlDataSource == null) {
            mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setUser("root");
            mysqlDataSource.setPassword("kszksz12");
            mysqlDataSource.setServerName("localhost");
            mysqlDataSource.setPort(3306);
        }
    }

    public Statement getStatement() {
        initDataSource();

        try {
            connection = mysqlDataSource.getConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public PreparedStatement getPreparedStatement(String sql) {
        initDataSource();

        try {
            connection = mysqlDataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }


    public void closeConnection() {
        try {
            if(statement != null) {
                statement.close();
            }
            if(preparedStatement != null) {
                preparedStatement.close();
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
