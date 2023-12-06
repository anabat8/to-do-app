package nl.tudelft.abatrineanu.ToDoList.controllers;

import lombok.RequiredArgsConstructor;
import nl.tudelft.abatrineanu.ToDoList.authentication.AuthManager;
import nl.tudelft.abatrineanu.ToDoList.models.ActivityRequest;
import nl.tudelft.abatrineanu.ToDoList.repositories.UserRepository;
import nl.tudelft.abatrineanu.ToDoList.services.ActivityService;
import nl.tudelft.abatrineanu.ToDoList.user.Activity;
import nl.tudelft.abatrineanu.ToDoList.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/toDo")
@CrossOrigin(origins = {"http://localhost:3000", "https://todo.tandrei.com"})
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;
    private final UserRepository userRepository;
    private final AuthManager authManager;

    @PostMapping("/add")
    public ResponseEntity<Activity> addActivity(
            @RequestBody ActivityRequest request
    ) {
        var user = getUserFromContext();
        request.setActivityOwner(user);
        return ResponseEntity.ok(activityService.addActivity(request));
    }

    @GetMapping("/seeAll")
    public ResponseEntity<List<Activity>> seeAllActivities() {
        var user = getUserFromContext();
        return ResponseEntity.ok(user.getToDoList());
    }

    @DeleteMapping("/deleteActivity/{id}")
    public ResponseEntity<Activity> deleteActivity(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(activityService.deleteActivity(id));
    }

    @PostMapping("/checkActivity/{id}")
    public ResponseEntity<Activity> checkActivity(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(activityService.checkActivity(id));
    }

    public User getUserFromContext() {
        String name = authManager.getUsername();
        var user = userRepository.findByName(name);
        return user.orElseThrow(() -> new UsernameNotFoundException(name));
    }
}
