package nl.tudelft.abatrineanu.ToDoList.user.exceptions;

public class ActivityAlreadyCheckedException extends Exception {
    static final long serialVersionUID = -3387516993124229948L;

    public ActivityAlreadyCheckedException(String activity) {
        super(activity);
    }
}
