package models;

import com.google.gson.annotations.SerializedName;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
//@JsonIgnoreProperties(ignoreUnknown = true)
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




//    @JsonProperty("id")
//    private int milestoneId;
//    private String name;
//    private String description;
//    @JsonProperty("start_on")
//    private int startOn;
//    @JsonProperty("started_on")
//    private int startedOn;
//    @JsonProperty("is_started")
//    private boolean isStarted;
//    @JsonProperty("due_on")
//    private int dueOn;
//    @JsonProperty("is_completed")
//    private boolean isCompleted;
//    @JsonProperty("completed_on")
//    private int completedOn;
//    @JsonProperty("project_id")
//    private int projectId;
//    @JsonProperty("parent_id")
//    private int parentId;
//    private String refs;
//    private String url;
//    private Milestone[] milestones;
}
