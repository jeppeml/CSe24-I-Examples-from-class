package dk.easv.assignmentworkoutfiles.dal.db;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dk.easv.assignmentworkoutfiles.be.User;
import dk.easv.assignmentworkoutfiles.dal.IUserDAO;
import dk.easv.assignmentworkoutfiles.exceptions.WorkoutException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAODB implements IUserDAO {

    @Override
    public List<User> getAll() throws WorkoutException {
        List<User> users = new ArrayList<>();

        SQLServerDataSource ds;
        ds = new SQLServerDataSource();
        ds.setDatabaseName("cse2024e30_workout"); // make this unique as names are shared on server
        ds.setUser("CSe2024b_e_30"); // Use your own username
        ds.setPassword("CSe2024bE30!24"); // Use your own password
        ds.setServerName("EASV-DB4");
        ds.setPortNumber(1433);
        ds.setTrustServerCertificate(true);

        try {
            Connection c = ds.getConnection();
            String sql = "SELECT * FROM users";
            PreparedStatement stmt = c.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // while there are rows
                int id = rs.getInt("id");
                String username = rs.getString("username");
                User user = new User(id, username);
                users.add(user);
            }

        } catch (SQLServerException e) {
            throw new WorkoutException(e);
        } catch (SQLException e) {
            throw new WorkoutException(e);
        }

        return users;
    }

    @Override
    public void clearAndSave(List<User> users) throws WorkoutException {

    }

    @Override
    public User add(User user) throws WorkoutException {
        return null;
    }

    @Override
    public void delete(User user) throws WorkoutException {

    }

    @Override
    public void update(User user) throws WorkoutException {

    }

    @Override
    public int getNextId() throws WorkoutException {
        return 0;
    }

    @Override
    public User get(int userId) throws WorkoutException {
        return null;
    }
}
