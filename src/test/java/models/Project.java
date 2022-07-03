package models;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class Project {
    @EqualsAndHashCode.Exclude
    @SerializedName("id")
    public int projectId;

    private String name;

    private String announcement;

    @SerializedName("show_announcement")
    private boolean showAnnouncement;

    @EqualsAndHashCode.Exclude
    @SerializedName("is_completed")
    private boolean isCompleted;

    @EqualsAndHashCode.Exclude
    @SerializedName("completed_on")
    private int completedOn;

    @SerializedName("suite_mode")
    private int suiteMode;

    @EqualsAndHashCode.Exclude
    @SerializedName("default_role_id")
    private int defaultRoleId;

    @EqualsAndHashCode.Exclude
    private String url;
}
