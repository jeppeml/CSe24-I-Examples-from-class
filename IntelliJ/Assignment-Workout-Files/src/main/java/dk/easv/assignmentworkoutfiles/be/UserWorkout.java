package dk.easv.assignmentworkoutfiles.be;

public class UserWorkout {
    private int id;
    private User user;
    private Routine routine;
    private String date;

    public UserWorkout(int id, User u, Routine r, String date){
        this.id = id;
        user = u;
        routine = r;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Routine getRoutine() {
        return routine;
    }

    public void setRoutine(Routine routine) {
        this.routine = routine;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "UserWorkout{" +
                id +
                ", " + user +
                ", " + routine +
                ", " + date;
    }
}
