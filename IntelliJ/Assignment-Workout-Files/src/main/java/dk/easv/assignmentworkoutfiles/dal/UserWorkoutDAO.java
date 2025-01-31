package dk.easv.assignmentworkoutfiles.dal;

import dk.easv.assignmentworkoutfiles.be.User;
import dk.easv.assignmentworkoutfiles.be.UserWorkout;
import dk.easv.assignmentworkoutfiles.exceptions.WorkoutException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserWorkoutDAO {
    UserDAO userDAO = new UserDAO();
    RoutineDAO routineDAO = new RoutineDAO();
    private final String splitChar = ";";
    private final Path filePath; // hardcoded path not good
    public UserWorkoutDAO() {
        filePath = Paths.get("user_workouts.csv");
    }

    public List<UserWorkout> getUserWorkouts(User u) throws WorkoutException {
        if(u!=null) {
            return getAll().stream().filter((userWorkout) -> userWorkout.getUser().getId() == u.getId()).toList();
        }
        else return new ArrayList<>(); // User not found
    }

    public List<UserWorkout> getAll() throws WorkoutException {
        List<UserWorkout> userWorkouts = new ArrayList<>();
        if (Files.exists(filePath)) {
            List<String> lines = null;
            try {
                lines = Files.readAllLines(filePath);
            } catch (IOException e) {
                throw new WorkoutException(e);
            }
            for (String line : lines) {
                String[] parts = line.split(splitChar);
                if (parts.length == 4) {
                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        int userId = Integer.parseInt(parts[1].trim());
                        int routineId = Integer.parseInt(parts[2].trim());
                        String date = parts[3].trim();
                        userWorkouts.add(new UserWorkout(id, userDAO.get(userId), routineDAO.get(routineId), date));
                    } catch (NumberFormatException e) {
                        Logger.getLogger(getClass().getName()).log(Level.WARNING, "Invalid format: " + parts[0], e);
                    }
                } else {
                    Logger.getLogger(getClass().getName()).log(Level.WARNING, "Invalid line format: " + line);
                }
            }
        }
        return userWorkouts;
    }

    private String getAsCSVString(UserWorkout userWorkout){
        return userWorkout.getId() + splitChar + userWorkout.getUser().getId() + splitChar + userWorkout.getRoutine().getId() + splitChar + userWorkout.getDate();
    }

    public void clearAndSave(List<UserWorkout> userWorkouts) throws WorkoutException {
        List<String> lines = new ArrayList<>();
        for (UserWorkout userWorkout : userWorkouts) {
            lines.add(getAsCSVString(userWorkout));
        }
        try {
            Files.write(filePath, lines); // Overwrites the file
        } catch (IOException e) {
            throw new WorkoutException(e);
        }
    }

    public UserWorkout add(UserWorkout userWorkout) throws WorkoutException {
        userWorkout.setId(getNextId());
        try {
            Files.write(filePath, List.of(getAsCSVString(userWorkout)), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new WorkoutException(e);
        }
        return userWorkout;
    }

    public void delete(UserWorkout userWorkout) throws WorkoutException {
        List<UserWorkout> userWorkouts = getAll();
        userWorkouts.removeIf(u -> u.getId() == userWorkout.getId());
        clearAndSave(userWorkouts);
    }

    public void update(UserWorkout userWorkout) throws WorkoutException {
        List<UserWorkout> userWorkouts = getAll();
        boolean userFound = false;
        for (int i = 0; i < userWorkouts.size(); i++) {
            if (userWorkouts.get(i).getId() == userWorkout.getId()) {
                userWorkouts.set(i, userWorkout); // Update the user
                userFound = true;
                break;
            }
        }
        if (!userFound) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, userWorkout.getId() + " not found for update.");
        }
        clearAndSave(userWorkouts); // Save the updated list
    }

    // Get the next available user ID
    public int getNextId() throws WorkoutException {
        List<UserWorkout> userWorkouts = getAll();
        int maxId = 0;
        for (UserWorkout userWorkout : userWorkouts) {
            if (userWorkout.getId() > maxId) {
                maxId = userWorkout.getId();
            }
        }
        return maxId + 1;
    }
}
