package dk.easv.assignmentworkoutfiles.dal.db;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

public class DBConnection {
    public Connection getConnection() throws SQLServerException {
        SQLServerDataSource ds;
        ds = new SQLServerDataSource();
        ds.setDatabaseName("cse2024e30_workout"); // make this unique as names are shared on server
        ds.setUser("CSe2024b_e_30"); // Use your own username
        ds.setPassword("CSe2024bE30!24"); // Use your own password
        ds.setServerName("EASV-DB4");
        ds.setPortNumber(1433);
        ds.setTrustServerCertificate(true);
        return ds.getConnection();
    }
}
