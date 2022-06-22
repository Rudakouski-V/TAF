package models;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Project {
    @SerializedName("id")
    public int projectId;
    private String name;
    private String announcement;
    @SerializedName("show_announcement")
    private boolean showAnnouncement;
    @SerializedName("is_completed")
    private boolean isCompleted;
    @SerializedName("completed_on")
    private int completedOn;
    @SerializedName("suite_mode")
    private int suiteMode;
    @SerializedName("default_role_id")
    private int defaultRoleId;
    private String url;
}
