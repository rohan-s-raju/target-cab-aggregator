package com.target.intf;

/**
 * Created by rohan on 30/12/17.
 */
public class CabRequestDTO {

    private long cabId;

    private long cost;

    private long capacity;

    public long getCabId() {
        return cabId;
    }

    public void setCabId(long cabId) {
        this.cabId = cabId;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }
}
