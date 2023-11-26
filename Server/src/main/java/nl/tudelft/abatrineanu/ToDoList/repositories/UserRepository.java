package nl.tudelft.abatrineanu.ToDoList.repositories;

import nl.tudelft.abatrineanu.ToDoList.user.Activity;
import nl.tudelft.abatrineanu.ToDoList.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByName(String name);
    Optional<User> findById(Integer id);

    /**
     * Check if an existing user already uses a specific username.
     */
    boolean existsByName(String name);

    @Query(value = "SELECT u.toDoList FROM User u WHERE u = ?1", nativeQuery = true)
    Optional<List<Activity>> findToDoList(User user);
}
