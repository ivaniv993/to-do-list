package com.ivaniv.domain;

/**
 * Created by iivaniv on 20.10.2016.
 */
public enum TaskStatus {

    OVERDUE("OVERDUE"),
    FINISHED("FINISHED"),
    PLANNED("PLANNED");

    private String name;

    TaskStatus(String name){
        this.name = name;
    }

    public String toString() {
        return "[Task status : "+this.name+"]";
    }
}
