package com.migu.schedule.info;

import java.util.List;
import java.util.Map;

public class NodeInfo {

    private int nodeId;

    private List<TaskInfoEx> taskInfoExList;

    private Map<String, Object> extInfo;

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public List<TaskInfoEx> getTaskInfoExList() {
        return taskInfoExList;
    }

    public void setTaskInfoExList(List<TaskInfoEx> taskInfoExList) {
        this.taskInfoExList = taskInfoExList;
    }

    public Map<String, Object> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, Object> extInfo) {
        this.extInfo = extInfo;
    }

    @Override
    public int hashCode() {
        return nodeId;
    }
}
