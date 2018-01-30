package com.target.intf;

import javax.validation.constraints.NotNull;

/**
 * Created by rohan on 30/12/17.
 */
public class RegisterCabRequestDTO {

    @NotNull(message =  "TEAM_MEMBER_IS_NOT_NULL")
    private long teamMemberID;

    @NotNull(message =  "GENDER_IS_NOT_NULL")
    private String gender;

    @NotNull(message = "DROP_POINT_IS_NOT_NULL")
    private String dropPoint;

    public long getTeamMemberID() {
        return teamMemberID;
    }

    public void setTeamMemberID(long teamMemberID) {
        this.teamMemberID = teamMemberID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDropPoint() {
        return dropPoint;
    }

    public void setDropPoint(String dropPoint) {
        this.dropPoint = dropPoint;
    }
}
