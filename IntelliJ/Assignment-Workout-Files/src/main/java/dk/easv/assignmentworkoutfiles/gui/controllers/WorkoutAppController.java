package dk.easv.assignmentworkoutfiles.gui.controllers;

import dk.easv.assignmentworkoutfiles.be.Routine;
import dk.easv.assignmentworkoutfiles.be.User;
import dk.easv.assignmentworkoutfiles.be.UserWorkout;
import dk.easv.assignmentworkoutfiles.gui.models.WorkoutModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class WorkoutAppController implements Initializable {
    @FXML
    private ListView<User> lstUsers;

    @FXML
    private ListView<Routine> lstRoutines;

    @FXML
    private ListView<UserWorkout> lstUserWorkouts;

    private final WorkoutModel workoutModel = new WorkoutModel();

    public void onLoadUsersClick(ActionEvent actionEvent) {
        try {
            workoutModel.loadUsers();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }

    public void onLoadRoutinesClick(ActionEvent actionEvent) {
        // TODO: Implement this
    }

    public void onLoadUserWorkoutsClick(ActionEvent actionEvent) {
        // TODO: Implement this
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lstUsers.setItems(workoutModel.getUsers());
    }

    public void onAddUserClick(ActionEvent actionEvent) {
        try {
            workoutModel.addUser(getRandomName());
        } catch (IOException e) {
            showAlertWindow(e);
        }
    }
    public void onDeleteUserClick(ActionEvent actionEvent) {
        User selectedUser = lstUsers.getSelectionModel().getSelectedItem();
        try {
            workoutModel.deleteUser(selectedUser);
        } catch (IOException e) {
            showAlertWindow(e);
        }
    }
    public void onUpdateUserClick(ActionEvent actionEvent) {
        User selectedUser = lstUsers.getSelectionModel().getSelectedItem();
        selectedUser.setUsername(getRandomName());
        try {
            workoutModel.updateUser(selectedUser);
        } catch (IOException e) {
            showAlertWindow(e);
        }
    }

    private String getRandomName(){
        String[] randomNames = {"Alice", "Bob", "Charlie", "Diana", "Eve", "Frank", "Grace", "Hannah", "Ivan", "Jack", "Karen", "Liam", "Mia", "Nathan", "Olivia", "Paul", "Quinn", "Rita", "Steve", "Tina", "Uma", "Victor", "Wendy", "Xander", "Yara", "Zane"};
        Random r = new Random();
        return randomNames[r.nextInt(randomNames.length)];
    }

    private void showAlertWindow(Exception e){
        Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
        alert.showAndWait();
    }
}