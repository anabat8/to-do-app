package nl.tudelft.abatrineanu.ToDoList.repositories;

import nl.tudelft.abatrineanu.ToDoList.user.Activity;
import nl.tudelft.abatrineanu.ToDoList.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {
    Optional<Activity> findById(Integer id);

    @Modifying
    @Query(value="DELETE FROM Activity_Table a WHERE a.id = ?1", nativeQuery = true)
    Optional<Integer> deleteActivityById(Integer id);

    Optional<List<Activity>> findByActivityOwner(User user);
}
