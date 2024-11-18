package dk.easv.assignmentworkoutfiles.dal;

import dk.easv.assignmentworkoutfiles.be.User;
import dk.easv.assignmentworkoutfiles.exceptions.WorkoutException;

import java.nio.file.StandardOpenOption;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO implements IUserDAO {
    private final String splitChar = ";";
    private final Path filePath; // hardcoded path not good
    public UserDAO() {
            filePath = Paths.get("users.csv");
    }
    // Load users from the CSV file
    @Override
    public List<User> getAll() throws WorkoutException {
        List<User> users = new ArrayList<>();
        if (Files.exists(filePath)) {
            List<String> lines = null;
            try {
                lines = Files.readAllLines(filePath);
            } catch (IOException e) {
                throw new WorkoutException(e);
            }
            for (String line : lines) {
                String[] parts = line.split(splitChar);
                if (parts.length == 2) {
                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        String userName = parts[1].trim();
                        users.add(new User(id, userName));
                    } catch (NumberFormatException e) {
                        // Log the error instead of printing it
                        Logger.getLogger(UserDAO.class.getName()).log(Level.WARNING, "Invalid user ID format: " + parts[0], e);
                    }
                } else {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.WARNING, "Invalid line format: " + line);
                }
            }
        }
        return users;
    }

    // Save (overwrite) the entire user list to the CSV file

    private void clearAndSave(List<User> users) throws WorkoutException {
        List<String> lines = new ArrayList<>();
        for (User user : users) {
            lines.add(user.getId() + splitChar + user.getUsername());
        }
        try {
            Files.write(filePath, lines); // Overwrites the file
        } catch (IOException e) {
            throw new WorkoutException(e);
        }
    }

    // Add a new user (append with no id in User)
    @Override
    public User add(User user) throws WorkoutException {
        user.setId(getNextId());
        String newUser =  user.getId() + splitChar + user.getUsername();
        try {
            Files.write(filePath, List.of(newUser), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new WorkoutException(e);
        }
        return user;
    }

    // Delete a user by ID (removes the user and rewrites the file)
    @Override
    public void delete(User user) throws WorkoutException {
        List<User> users = getAll();
        users.removeIf(u -> u.getId() == user.getId());
        clearAndSave(users);
    }

    // Update a user (updates the user if it exists and rewrites the file)
    @Override
    public void update(User user) throws WorkoutException {
        List<User> users = getAll();
        boolean userFound = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == user.getId()) {
                users.set(i, user); // Update the user
                userFound = true;
                break;
            }
        }
        if (!userFound) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.WARNING, "User with ID " + user.getId() + " not found for update.");
        }
        clearAndSave(users); // Save the updated list
    }

    // Get the next available user ID
    private int getNextId() throws WorkoutException {
        List<User> users = getAll();
        int maxId = 0;
        for (User user : users) {
            if (user.getId() > maxId) {
                maxId = user.getId();
            }
        }
        return maxId + 1;
    }

    @Override
    public User get(int userId) throws WorkoutException {
        List<User> all = getAll();
        for(User user : all){
            if (user.getId() == userId)
                return user;
        }

        return null;
    }
}
