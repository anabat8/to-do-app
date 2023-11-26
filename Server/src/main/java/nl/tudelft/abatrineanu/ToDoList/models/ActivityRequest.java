package nl.tudelft.abatrineanu.ToDoList.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.tudelft.abatrineanu.ToDoList.user.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityRequest {

    private String description;

    private User activityOwner;
}
