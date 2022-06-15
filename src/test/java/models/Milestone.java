package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Milestone {
    @JsonProperty("id")
    private int milestoneId;
    private String name;
    private String description;
    @JsonProperty("start_on")
    private int startOn;
    @JsonProperty("started_on")
    private int startedOn;
    @JsonProperty("is_started")
    private boolean isStarted;
    @JsonProperty("due_on")
    private int dueOn;
    @JsonProperty("is_completed")
    private boolean isCompleted;
    @JsonProperty("completed_on")
    private int completedOn;
    @JsonProperty("project_id")
    private int projectId;
    @JsonProperty("parent_id")
    private int parentId;
    private String refs;
    private String url;
    private Milestone[] milestones;
}
