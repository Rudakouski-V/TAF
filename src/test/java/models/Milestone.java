package models;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Milestone {
    @SerializedName("id")
    private int milestoneId;
    private String name;
    private String description;
    @SerializedName("start_on")
    private int startOn;
    @SerializedName("started_on")
    private int startedOn;
    @SerializedName("is_started")
    private boolean isStarted;
    @SerializedName("due_on")
    private int dueOn;
    @SerializedName("is_completed")
    private boolean isCompleted;
    @SerializedName("completed_on")
    private int completedOn;
    @SerializedName("project_id")
    private int projectId;
    @SerializedName("parent_id")
    private int parentId;
    private String refs;
    private String url;
    private Milestone[] milestones;
}
