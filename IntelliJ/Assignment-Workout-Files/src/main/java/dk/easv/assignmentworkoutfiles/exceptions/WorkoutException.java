package dk.easv.assignmentworkoutfiles.exceptions;

public class WorkoutException extends Exception{
    public WorkoutException (Exception e){
        super(e);
    }

    public WorkoutException (String message){
        super(message);
    }
}
