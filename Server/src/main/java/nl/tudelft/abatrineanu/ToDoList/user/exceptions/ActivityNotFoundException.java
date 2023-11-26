package nl.tudelft.abatrineanu.ToDoList.user.exceptions;

public class ActivityNotFoundException extends Exception {
    static final long serialVersionUID = -3387516993124229948L;

    public ActivityNotFoundException(String activity) {
        super(activity);
    }
}
