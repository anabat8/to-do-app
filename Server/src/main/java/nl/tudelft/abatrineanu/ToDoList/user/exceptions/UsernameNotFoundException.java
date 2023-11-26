package nl.tudelft.abatrineanu.ToDoList.user.exceptions;

public class UsernameNotFoundException extends Exception {
    static final long serialVersionUID = -3387516993124229948L;

    public UsernameNotFoundException(String username) {
        super(username);
    }
}
