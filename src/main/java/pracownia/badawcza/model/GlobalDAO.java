package pracownia.badawcza.model;

import pracownia.badawcza.DatabaseConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by tomas on 21.05.2017.
 */
public class GlobalDAO {

    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public GlobalDAO() {

    }

    public void runUpdateQuery(String query) {
        Statement statement = databaseConnector.getStatement();
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnector.closeConnection();
    }

    public BinaryStreamDTO getBinaryStream() {
        Statement statement = databaseConnector.getStatement();
        BinaryStreamDTO binaryStreamDTO = null;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM BINARYSTREAM");
            binaryStreamDTO = new BinaryStreamDTO(
                    resultSet.getString(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4),
                    resultSet.getString(5)
            );
            binaryStreamDTO.setId(resultSet.getInt(0));
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnector.closeConnection();
        return binaryStreamDTO;
    }

    public ErrorDTO getErrorDTO() {
        Statement statement = databaseConnector.getStatement();
        ErrorDTO errorDTO = null;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ERROR");
            errorDTO = new ErrorDTO(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4)
            );
            errorDTO.setId(resultSet.getInt(0));
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnector.closeConnection();
        return errorDTO;
    }

    public void addBinaryStream(BinaryStreamDTO binaryStreamDTO) {
        runUpdateQuery(String.format("INSERT INTO histogram.BINARYSTREAM (NAME, D, L, R, STREAM) VALUES ('%s', %d, %d, %d, '%s')",
                binaryStreamDTO.getName(),
                binaryStreamDTO.getD(),
                binaryStreamDTO.getL(),
                binaryStreamDTO.getR(),
                binaryStreamDTO.getStream()
                )
        );
    }

    public void addError(ErrorDTO errorDTO) {
        runUpdateQuery(String.format("INSERT INTO ERROR (STEP, STREAMID, ORIGINALCOUNT, HISTOGRAMCOUNT) VALUES (%d, %d, %d, %d)",
                errorDTO.getStep(),
                errorDTO.getStreamId(),
                errorDTO.getOriginalCount(),
                errorDTO.getHistogramCount()
                )
        );
    }
}
