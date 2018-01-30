package com.target.intf;

import java.util.List;

/**
 * Created by rohan on 30/12/17.
 */
public class RoutePlanResponseDTO {

    private long totalCost;
    private List<Route> routes;

    public long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(long totalCost) {
        this.totalCost = totalCost;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
