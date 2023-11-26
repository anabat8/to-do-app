package nl.tudelft.abatrineanu.ToDoList.services;

import lombok.RequiredArgsConstructor;
import nl.tudelft.abatrineanu.ToDoList.models.ActivityRequest;
import nl.tudelft.abatrineanu.ToDoList.repositories.ActivityRepository;
import nl.tudelft.abatrineanu.ToDoList.repositories.UserRepository;
import nl.tudelft.abatrineanu.ToDoList.user.Activity;
import nl.tudelft.abatrineanu.ToDoList.user.exceptions.ActivityAlreadyCheckedException;
import nl.tudelft.abatrineanu.ToDoList.user.exceptions.ActivityNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    public Activity addActivity(ActivityRequest request) {
        var user = request.getActivityOwner();

        var activity = Activity.builder()
                .description(request.getDescription())
                .status(false)
                .activityOwner(user)
                .build();

        user.getToDoList().add(activity);
        activityRepository.save(activity);

        return activity;
    }

    public Activity deleteActivity(Integer id) throws Exception {
        var activity = activityRepository.findById(id).orElseThrow(() -> new ActivityNotFoundException("Activity with id " + id + " not found"));
        activityRepository.deleteActivityById(id);
        return activity;
    }

    public Activity checkActivity(Integer id) throws Exception {
        var activity = activityRepository.findById(id);

        if(activity.isPresent())
            if(activity.get().isStatus())
                throw new ActivityAlreadyCheckedException("Activity with id " + id + " already checked");

        activity.map(x -> {
            x.setStatus(true);
            activityRepository.save(x);
            return x;
        });

        return activity.orElseThrow(() -> new ActivityNotFoundException("Activity with id " + id + " not found"));
    }
}