package nl.tudelft.abatrineanu.ToDoList.user.exceptions;

public class UsernameAlreadyInUseException extends Exception {
    static final long serialVersionUID = -3387516993124229948L;

    public UsernameAlreadyInUseException(String username) {
        super(username);
    }
}
