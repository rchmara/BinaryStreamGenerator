package pracownia.badawcza.model;

import pracownia.badawcza.DatabaseConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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

    public BinaryStreamDTO getBinaryStream(int id) {
        Statement statement = databaseConnector.getStatement();
        BinaryStreamDTO binaryStreamDTO = null;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM histogram.BINARYSTREAM WHERE ID = id");
            if (resultSet.next()) {

                binaryStreamDTO = new BinaryStreamDTO(
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5),
                        resultSet.getString(6)
                );
                binaryStreamDTO.setId(resultSet.getInt(1));
            }
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
            ResultSet resultSet = statement.executeQuery("SELECT * FROM histogram.ERROR");
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
        runUpdateQuery(String.format("INSERT INTO histogram.ERROR (STEP, STREAMID, ORGINALCOUNT, HISTOGRAMCOUNT) VALUES (%d, %d, %d, %d)",
                errorDTO.getStep(),
                errorDTO.getStreamId(),
                errorDTO.getOriginalCount(),
                errorDTO.getHistogramCount()
                )
        );
    }

    public void addErrors(List<ErrorDTO> errors) {
        String query = "INSERT INTO histogram.ERROR (STEP, STREAMID, ORGINALCOUNT, HISTOGRAMCOUNT) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = databaseConnector.getPreparedStatement(query);
        try {
            for (ErrorDTO error : errors) {
                ps.setInt(1, error.getStep());
                ps.setInt(2, error.getStreamId());
                ps.setInt(3, error.getOriginalCount());
                ps.setInt(4, error.getHistogramCount());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
