package com.target.intf;

/**
 * Created by rohan on 18/1/18.
 */
public class Route {

    private long cabId;
    private String teamMemberIds;
    private String route;
    private long routeCost;

    public long getCabId() {
        return cabId;
    }

    public void setCabId(long cabId) {
        this.cabId = cabId;
    }

    public String getTeamMemberIds() {
        return teamMemberIds;
    }

    public void setTeamMemberIds(String teamMemberIds) {
        this.teamMemberIds = teamMemberIds;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public long getRouteCost() {
        return routeCost;
    }

    public void setRouteCost(long routeCost) {
        this.routeCost = routeCost;
    }
}
