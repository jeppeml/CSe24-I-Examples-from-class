package dk.easv.assignmentworkoutfiles.bll;

import dk.easv.assignmentworkoutfiles.be.Routine;
import dk.easv.assignmentworkoutfiles.be.User;
import dk.easv.assignmentworkoutfiles.be.UserWorkout;
import dk.easv.assignmentworkoutfiles.dal.RoutineDAO;
import dk.easv.assignmentworkoutfiles.dal.UserDAO;
import dk.easv.assignmentworkoutfiles.dal.UserWorkoutDAO;
import dk.easv.assignmentworkoutfiles.exceptions.WorkoutException;

import java.util.List;

public class WorkoutManager {
    private final UserDAO userDAO = new UserDAO();
    private final UserWorkoutDAO userWorkoutDAO = new UserWorkoutDAO();
    private final RoutineDAO routineDAO = new RoutineDAO();

    // Add a new user
    public User addUser(User user) throws WorkoutException {
        if (sanityCheckUserName(user.getUsername())) {
            return userDAO.add(user);
        }
        return null;
    }

    // Update an existing user
    public void updateUser(User updatedUser) throws WorkoutException {
        if (sanityCheckUserName(updatedUser.getUsername())) {
            userDAO.update(updatedUser);
        }
    }

    // Get all users
    public List<User> getUsers() throws WorkoutException {
        return userDAO.getAll();
    }

    // Delete a user
    public void deleteUser(User user) throws WorkoutException {
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

    public List<UserWorkout> getUserWorkouts(User u) throws WorkoutException {
        return userWorkoutDAO.getUserWorkouts(u);
    }

    public List<Routine> getRoutines() throws WorkoutException {
        return routineDAO.getAll();
    }

    public Routine addRoutine(Routine routine) throws WorkoutException {
        return routineDAO.add(routine);
    }

    public void deleteRoutine(Routine routine) throws WorkoutException {
        routineDAO.delete(routine);
    }
}
