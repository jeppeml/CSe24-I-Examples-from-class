package dk.easv.assignmentworkoutfiles.dal;

import dk.easv.assignmentworkoutfiles.be.User;

import java.nio.file.StandardOpenOption;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    private final String splitChar = ";";
    private final Path filePath; // hardcoded path not good
    public UserDAO() {
            filePath = Paths.get("users.csv");
    }
    // Load users from the CSV file
    public List<User> getAll() throws IOException {
        List<User> users = new ArrayList<>();
        if (Files.exists(filePath)) {
            List<String> lines = Files.readAllLines(filePath);
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
    public void clearAndSave(List<User> users) throws IOException {
        List<String> lines = new ArrayList<>();
        for (User user : users) {
            lines.add(user.getId() + splitChar + user.getUsername());
        }
        Files.write(filePath, lines); // Overwrites the file
    }

    // Add a new user (append with no id in User)
    public User add(User user) throws IOException {
        user.setId(getNextId());
        String newUser =  user.getId() + splitChar + user.getUsername();
        Files.write(filePath, List.of(newUser), StandardOpenOption.APPEND);
        return user;
    }

    // Delete a user by ID (removes the user and rewrites the file)
    public void delete(User user) throws IOException {
        List<User> users = getAll();
        users.removeIf(u -> u.getId() == user.getId());
        clearAndSave(users);
    }

    // Update a user (updates the user if it exists and rewrites the file)
    public void update(User user) throws IOException {
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
    public int getNextId() throws IOException {
        List<User> users = getAll();
        int maxId = 0;
        for (User user : users) {
            if (user.getId() > maxId) {
                maxId = user.getId();
            }
        }
        return maxId + 1;
    }
}
