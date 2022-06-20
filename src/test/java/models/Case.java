package models;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Case {
    @SerializedName("id")
    private int caseId;
    private String title;
    @SerializedName("section_id")
    private int sectionId;
    @SerializedName("template_id")
    private int templateId;
    @SerializedName("type_id")
    private int typeId;
    @SerializedName("priority_id")
    private int priorityId;
    @SerializedName("milestone_id")
    private int milestoneId;
    private String refs;
    @SerializedName("created_by")
    private int createdBy;
    @SerializedName("created_on")
    private int createdOn;
    @SerializedName("updated_by")
    private int updatedBy;
    @SerializedName("updated_on")
    private int updatedOn;
    private String estimate;
    @SerializedName("estimate_forecast")
    private String estimateForecast;
    @SerializedName("suite_id")
    private int suiteId;
    @SerializedName("display_order")
    private int displayOrder;
//    @SerializedName("is_deleted")
//    private int isDeleted;
//    @SerializedName("custom_automation_type")
//    private int customAutomationType;
//    @SerializedName("custom_preconds")
//    private int customPreconds;
//    @SerializedName("custom_steps")
//    private int customSteps;
//    @SerializedName("custom_expected")
//    private int customExpected;
//    @SerializedName("custom_steps_separated")
//    private int customStepsSeparated;
//    @SerializedName("custom_mission")
//    private int customMission;
//    @SerializedName("custom_goals")
//    private int customGoals;
}
