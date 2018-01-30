package com.target.impl.domain.entity;

import java.io.Serializable;

/**
 * Created by rohan on 17/1/18.
 */
public class DropIds implements Serializable {

    private String from;

    private String to;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DropIds dropIds = (DropIds) o;

        if (from != null ? !from.equals(dropIds.from) : dropIds.from != null) return false;
        return to != null ? to.equals(dropIds.to) : dropIds.to == null;
    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }
}
