package dk.easv.assignmentworkoutfiles.gui.controllers;

import dk.easv.assignmentworkoutfiles.be.Routine;
import dk.easv.assignmentworkoutfiles.be.User;
import dk.easv.assignmentworkoutfiles.be.UserWorkout;
import dk.easv.assignmentworkoutfiles.exceptions.WorkoutException;
import dk.easv.assignmentworkoutfiles.gui.models.WorkoutModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

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
        } catch (WorkoutException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }

    public void onLoadRoutinesClick(ActionEvent actionEvent) {
        try {
            workoutModel.loadRoutines();
        } catch (WorkoutException e) {
            showAlertWindow(e);
        }
    }

    public void onLoadUserWorkoutsClick(ActionEvent actionEvent) {
        // TODO: Implement this
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lstUsers.setItems(workoutModel.getUsers());
        lstUserWorkouts.setItems(workoutModel.getUserWorkouts());
        lstRoutines.setItems(workoutModel.getRoutines());
        lstUsers.getSelectionModel().selectedItemProperty().addListener((prop, old, current)->{
            try {
                workoutModel.loadUserWorkouts(current);
            } catch (WorkoutException e) {
                showAlertWindow(e);
            }
        });
    }

    public void onAddUserClick(ActionEvent actionEvent) {
        try {
            workoutModel.addUser(getRandomName());
        } catch (WorkoutException e) {
            showAlertWindow(e);
        }
    }
    public void onDeleteUserClick(ActionEvent actionEvent) {
        User selectedUser = lstUsers.getSelectionModel().getSelectedItem();
        try {
            workoutModel.deleteUser(selectedUser);
        } catch (WorkoutException e) {
            showAlertWindow(e);
        }
    }
    public void onUpdateUserClick(ActionEvent actionEvent) {
        User selectedUser = lstUsers.getSelectionModel().getSelectedItem();
        selectedUser.setUsername(getRandomName());
        try {
            workoutModel.updateUser(selectedUser);
        } catch (WorkoutException e) {
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

    @FXML
    private void onAddWorkout(ActionEvent actionEvent) {
        Random r = new Random();
        String[] routines = {
                "Push-Ups",
                "Squats",
                "Plank",
                "Jumping Jacks",
                "Lunges",
                "Burpees",
                "Mountain Climbers",
                "Sit-Ups",
                "Pull-Ups",
                "High Knees",
                "Russian Twists",
                "Bicycle Crunches",
                "Jump Rope",
                "Deadlifts",
                "Box Jumps"
        };
        String[] descriptions = {
                "A full-body exercise that targets the chest, triceps, and core.",
                "A lower body exercise that primarily targets the quadriceps, hamstrings, and glutes.",
                "An isometric core exercise that builds endurance in the abs, back, and shoulders.",
                "A cardio exercise that improves endurance and helps with weight loss.",
                "A unilateral lower-body movement targeting the glutes, quadriceps, and core stability.",
                "A high-intensity full-body workout that burns calories and builds strength.",
                "An intense cardio and core exercise that engages the entire body.",
                "An abdominal exercise targeting the rectus abdominis and improving core strength.",
                "A bodyweight exercise focusing on upper body strength, mainly the back and biceps.",
                "A cardio move that improves speed and coordination by lifting knees quickly.",
                "An oblique-targeting exercise that also engages the core through rotational movement.",
                "A core and cardio exercise that mimics cycling movements while lying on your back.",
                "A cardio exercise that improves agility, coordination, and cardiovascular health.",
                "A compound strength exercise focusing on the hamstrings, glutes, and lower back.",
                "An explosive plyometric movement targeting leg power and coordination."
        };
        int randomMinutes = r.nextInt(100);
        try {
            Routine routine = workoutModel.addRoutine(
                    new Routine(
                            routines[r.nextInt(routines.length)],
                            descriptions[r.nextInt(descriptions.length)],
                            randomMinutes
                    ));
            lstRoutines.getItems().add(routine);
        } catch (WorkoutException e) {
            showAlertWindow(e);
        }

    }

    @FXML
    private void onDeleteWorkout(ActionEvent actionEvent) {
        Routine selected = lstRoutines.getSelectionModel().getSelectedItem();
        try {
            workoutModel.deleteRoutine(selected);
        } catch (WorkoutException e) {
            showAlertWindow(e);
        }
        lstRoutines.getItems().remove(selected);
    }
}