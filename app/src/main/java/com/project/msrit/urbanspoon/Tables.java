package com.project.msrit.urbanspoon;

/**
 * Created by plank-dhamini on 21/01/18.
 */

public class Tables {

    private String tableName;
    private Boolean availability;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Tables withTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public Tables withAvaliablilty(Boolean avaliablilty) {
        this.availability = avaliablilty;
        return this;
    }

}
