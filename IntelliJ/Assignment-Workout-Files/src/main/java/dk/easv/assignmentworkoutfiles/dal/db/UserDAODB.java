package dk.easv.assignmentworkoutfiles.dal.db;

import dk.easv.assignmentworkoutfiles.be.User;
import dk.easv.assignmentworkoutfiles.dal.IUserDAO;
import dk.easv.assignmentworkoutfiles.exceptions.WorkoutException;

import java.util.List;

public class UserDAODB implements IUserDAO {

    @Override
    public List<User> getAll() throws WorkoutException {
        return List.of();
    }

    @Override
    public void clearAndSave(List<User> users) throws WorkoutException {

    }

    @Override
    public User add(User user) throws WorkoutException {
        return null;
    }

    @Override
    public void delete(User user) throws WorkoutException {

    }

    @Override
    public void update(User user) throws WorkoutException {

    }

    @Override
    public int getNextId() throws WorkoutException {
        return 0;
    }

    @Override
    public User get(int userId) throws WorkoutException {
        return null;
    }
}
