package dk.easv.assignmentworkoutfiles.gui.models;

import dk.easv.assignmentworkoutfiles.be.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import dk.easv.assignmentworkoutfiles.bll.*;
import javafx.scene.control.Alert;

import java.io.IOException;

public class WorkoutModel {
    private final WorkoutManager workoutManager = new WorkoutManager();
    private final ObservableList<User> users = FXCollections.observableArrayList();

    public void loadUsers() throws IOException {
        users.setAll(workoutManager.getUsers());
    }

    // Get observable list of users
    public ObservableList<User> getUsers() {
        return users;
    }

    // Add a new user
    public void addUser(String userName) throws IOException {
        User newUser = workoutManager.addUser(new User(-1, userName)); // create user with fake id, get user back with right id
        users.add(newUser);
    }

    public void deleteUser(User user) throws IOException {
        workoutManager.deleteUser(user);
        users.remove(user);
    }

    public void updateUser(User user) throws IOException {
        workoutManager.updateUser(user);
        users.remove(user);
        users.add(user);
    }
}
