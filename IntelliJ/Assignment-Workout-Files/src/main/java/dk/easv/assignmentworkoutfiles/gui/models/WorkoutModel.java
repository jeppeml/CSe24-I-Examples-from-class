package dk.easv.assignmentworkoutfiles.gui.models;

import dk.easv.assignmentworkoutfiles.be.Routine;
import dk.easv.assignmentworkoutfiles.be.User;
import dk.easv.assignmentworkoutfiles.be.UserWorkout;
import dk.easv.assignmentworkoutfiles.exceptions.WorkoutException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import dk.easv.assignmentworkoutfiles.bll.*;
import javafx.scene.control.Alert;

import java.io.IOException;

public class WorkoutModel {
    private final WorkoutManager workoutManager = new WorkoutManager();
    private final ObservableList<User> users = FXCollections.observableArrayList();
    private final ObservableList<UserWorkout> userWorkouts = FXCollections.observableArrayList();
    private final ObservableList<Routine> routines = FXCollections.observableArrayList();

    public void loadRoutines() throws WorkoutException {
        routines.setAll(workoutManager.getRoutines());
    }

    public void loadUserWorkouts(User u) throws WorkoutException {
        userWorkouts.setAll(workoutManager.getUserWorkouts(u));
    }

    public ObservableList<UserWorkout> getUserWorkouts(){
        return userWorkouts;
    }

    public void loadUsers() throws WorkoutException {
        users.setAll(workoutManager.getUsers());
    }

    // Get observable list of users
    public ObservableList<User> getUsers() {
        return users;
    }

    // Add a new user
    public void addUser(String userName) throws WorkoutException {
        User newUser = workoutManager.addUser(new User(-1, userName)); // create user with fake id, get user back with right id
        users.add(newUser);
    }

    public void deleteUser(User user) throws WorkoutException {
        workoutManager.deleteUser(user);
        users.remove(user);
    }

    public void updateUser(User user) throws WorkoutException {
        workoutManager.updateUser(user);
        users.remove(user);
        users.add(user);
    }

    public ObservableList<Routine> getRoutines() {
        return routines;
    }

    public Routine addRoutine(Routine routine) throws WorkoutException {
        return workoutManager.addRoutine(routine);
    }

    public void deleteRoutine(Routine routine) throws WorkoutException {
        workoutManager.deleteRoutine(routine);
    }
}
