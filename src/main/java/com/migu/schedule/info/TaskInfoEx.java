package com.migu.schedule.info;

public class TaskInfoEx extends TaskInfo {

    private int consumption;

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    @Override
    public int hashCode() {
        return consumption*100+this.getTaskId();
    }
}
