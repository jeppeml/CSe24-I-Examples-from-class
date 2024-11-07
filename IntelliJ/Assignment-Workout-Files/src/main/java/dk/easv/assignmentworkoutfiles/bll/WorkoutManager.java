package dk.easv.assignmentworkoutfiles.bll;

import dk.easv.assignmentworkoutfiles.be.User;
import dk.easv.assignmentworkoutfiles.dal.UserDAO;

import java.io.IOException;
import java.util.List;

public class WorkoutManager {
    private final UserDAO userDAO = new UserDAO();

    // Add a new user
    public User addUser(User user) throws IOException {
        if (sanityCheckUserName(user.getUsername())) {
            return userDAO.add(user);
        }
        return null;
    }

    // Update an existing user
    public void updateUser(User updatedUser) throws IOException {
        if (sanityCheckUserName(updatedUser.getUsername())) {
            userDAO.update(updatedUser);
        }
    }

    // Get all users
    public List<User> getUsers() throws IOException {
        return userDAO.getAll();
    }

    // Delete a user
    public void deleteUser(User user) throws IOException {
        userDAO.delete(user);
    }

    // Sanity check, just an example of some logic
    private boolean sanityCheckUserName(String userName) {
        if (userName == null || userName.trim().isEmpty()) {
            throw new IllegalArgumentException("User name is empty or null");
        }
        if (userName.length() < 2 || userName.length() > 20) {
            throw new IllegalArgumentException("User name must be between 2 and 20 characters");
        }
        if (!userName.matches("^[a-zA-Z0-9_]+$")) {
            throw new IllegalArgumentException("User name contains invalid characters");
        }
        return true;
    }
}
