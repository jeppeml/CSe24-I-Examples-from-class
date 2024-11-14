package dk.easv.assignmentworkoutfiles.dal;

import dk.easv.assignmentworkoutfiles.be.Routine;
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

public class RoutineDAO {
    private final String splitChar = ";";
    private final Path filePath; // hardcoded path not good
    public RoutineDAO() {
        filePath = Paths.get("routines.csv");
    }
    // Load users from the CSV file
    public List<Routine> getAll() throws WorkoutException {
        List<Routine> routines = new ArrayList<>();
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
                        String name = parts[1].trim();
                        String description = parts[2].trim();
                        int minutes = Integer.parseInt(parts[3].trim());
                        routines.add(new Routine(id, name, description,minutes));
                    } catch (NumberFormatException e) {
                        // Log the error instead of printing it
                        Logger.getLogger(getClass().getName()).log(Level.WARNING, "Invalid format: " + parts[0], e);
                    }
                } else {
                    Logger.getLogger(getClass().getName()).log(Level.WARNING, "Invalid line format: " + line);
                }
            }
        }
        return routines;
    }

    private String getAsCSVString(Routine routine){
        return routine.getId() + splitChar + routine.getName() + splitChar + routine.getDescription() + splitChar + routine.getDuration();
    }

    // Save (overwrite) the entire user list to the CSV file
    public void clearAndSave(List<Routine> routines) throws WorkoutException {
        List<String> lines = new ArrayList<>();
        for (Routine routine : routines) {
            lines.add(getAsCSVString(routine));
        }
        try {
            Files.write(filePath, lines); // Overwrites the file
        } catch (IOException e) {
            throw new WorkoutException(e);
        }
    }

    // Add a new user (append with no id in User)
    public Routine add(Routine routine) throws WorkoutException {
        routine.setId(getNextId());
        try {
            Files.write(filePath, List.of(getAsCSVString(routine)), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new WorkoutException(e);
        }
        return routine;
    }

    // Delete a user by ID (removes the user and rewrites the file)
    public void delete(Routine routine) throws WorkoutException {
        List<Routine> routines = getAll();
        routines.removeIf(u -> u.getId() == routine.getId());
        clearAndSave(routines);
    }

    // Update a user (updates the user if it exists and rewrites the file)
    public void update(Routine routine) throws WorkoutException {
        List<Routine> routines = getAll();
        boolean userFound = false;
        for (int i = 0; i < routines.size(); i++) {
            if (routines.get(i).getId() == routine.getId()) {
                routines.set(i, routine); // Update the user
                userFound = true;
                break;
            }
        }
        if (!userFound) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, routine.getId() + " not found for update.");
        }
        clearAndSave(routines); // Save the updated list
    }

    // Get the next available user ID
    public int getNextId() throws WorkoutException {
        List<Routine> routines = getAll();
        int maxId = 0;
        for (Routine routine : routines) {
            if (routine.getId() > maxId) {
                maxId = routine.getId();
            }
        }
        return maxId + 1;
    }

    public Routine get(int routineId) throws WorkoutException {
        List<Routine> all = getAll();
        for(Routine routine : all){
            if (routine.getId() == routineId)
                return routine;
        }
        return null;
    }


}
