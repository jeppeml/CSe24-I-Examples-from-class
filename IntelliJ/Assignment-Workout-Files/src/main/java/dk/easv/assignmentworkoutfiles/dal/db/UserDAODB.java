package dk.easv.assignmentworkoutfiles.dal.db;

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
    private DBConnection con = new DBConnection();
    @Override
    public List<User> getAll() throws WorkoutException {
        List<User> users = new ArrayList<>();
        try {
            Connection c = con.getConnection();
            String sql = "SELECT * FROM users";
            PreparedStatement stmt = c.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // while there are rows
                int id = rs.getInt("id");
                String username = rs.getString("username");
                User user = new User(id, username);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new WorkoutException(e);
        }
        return users;
    }

    @Override
    public User add(User user) throws WorkoutException {
        try {
            Connection c = con.getConnection();
            String sql = "INSERT INTO users (id, username) VALUES (?,?)";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getUsername());
            stmt.execute();
        } catch (SQLException e) {
            throw new WorkoutException(e);
        }
        return user;
    }

    @Override
    public void delete(User user) throws WorkoutException {
        try {
            Connection c = con.getConnection();
            String sql = "DELETE FROM users WHERE id=?";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, user.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new WorkoutException(e);
        }
    }

    @Override
    public void update(User user) throws WorkoutException {
        try {
            Connection c = con.getConnection();
            String sql = "UPDATE users SET username=? WHERE id=?";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setInt(2, user.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new WorkoutException(e);
        }
    }

    @Override
    public User get(int userId) throws WorkoutException {
        try {
            Connection c = con.getConnection();
            String sql = "SELECT * FROM users WHERE id=?";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // while there are rows
                int id = rs.getInt("id");
                String username = rs.getString("username");
                User user = new User(id, username);
                return user;
            }

        } catch (SQLException e) {
            throw new WorkoutException(e);
        }
        throw new WorkoutException("User not found: " + userId);
    }
}
