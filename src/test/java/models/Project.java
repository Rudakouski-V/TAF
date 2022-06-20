package models;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Project {
    @SerializedName("id")
    public int projectId;
    public String name;
    public String announcement;
    @SerializedName("show_announcement")
    public boolean showAnnouncement;
    @SerializedName("is_completed")
    public boolean isCompleted;
    @SerializedName("completed_on")
    public int completedOn;
    @SerializedName("suite_mode")
    public int suiteMode;
    @SerializedName("default_role_id")
    public int defaultRoleId;
    public String url;
    public User[] users;
    public Group[] groups;
//    @SerializedName("id")
//    private int projectId;
//    private String name;
//    private String announcement;
//    @SerializedName("show_announcement")
//    private boolean showAnnouncement;
//    @SerializedName("is_completed")
//    private boolean isCompleted;
//    @SerializedName("completed_on")
//    private int completedOn;
//    @SerializedName("suite_mode")
//    private int suiteMode;
//    @SerializedName("default_role_id")
//    private int defaultRoleId;
//    private String url;
//    private User[] users;
//    private Group[] groups;
}
