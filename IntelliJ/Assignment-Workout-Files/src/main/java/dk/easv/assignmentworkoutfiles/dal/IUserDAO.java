package dk.easv.assignmentworkoutfiles.dal;

import dk.easv.assignmentworkoutfiles.be.User;
import dk.easv.assignmentworkoutfiles.exceptions.WorkoutException;

import java.util.List;

public interface IUserDAO {
    List<User> getAll() throws WorkoutException;

    void clearAndSave(List<User> users) throws WorkoutException;

    User add(User user) throws WorkoutException;

    void delete(User user) throws WorkoutException;

    void update(User user) throws WorkoutException;

    int getNextId() throws WorkoutException;

    User get(int userId) throws WorkoutException;
}
