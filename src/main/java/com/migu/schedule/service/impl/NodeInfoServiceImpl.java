package com.migu.schedule.service.impl;

import com.migu.schedule.info.NodeInfo;
import com.migu.schedule.info.TaskInfoEx;
import com.migu.schedule.service.NodeInfoService;

import java.util.*;

public class NodeInfoServiceImpl implements NodeInfoService {

    private static Map<String, NodeInfo> map = new HashMap<String, NodeInfo>();

    public int addNodeInfo(NodeInfo nodeInfo) {
        map.put(nodeInfo.getNodeId()+"",nodeInfo);
        return 1;
    }

    public List<TaskInfoEx> deleteNodeInfo(int nodeId) {
        NodeInfo nodeInfo = map.get(String.valueOf(nodeId));
        List<TaskInfoEx> taskInfoExList = nodeInfo.getTaskInfoExList();
        map.remove(String.valueOf(nodeId));
        if (taskInfoExList == null || taskInfoExList.isEmpty()) {
            return null;
        }
        for (TaskInfoEx taskInfoEx : taskInfoExList) {
            taskInfoEx.setNodeId(0);
        }
        return taskInfoExList;
    }

    public int deleteTaskInfo(int nodeId, int taskId) {
        NodeInfo nodeInfo = map.get(nodeId + "");
        List<TaskInfoEx> taskInfoExList = nodeInfo.getTaskInfoExList();
        int index = 0;
        for (int i =0;i< taskInfoExList.size();i++) {
            TaskInfoEx taskInfoEx = taskInfoExList.get(i);
            if(taskInfoEx.getTaskId()==taskId)
            {
                index = i;
                break;
            }
        }
        taskInfoExList.remove(index);
        return 1;
    }

    public NodeInfo queryNodeInfo(int nodeId) {
        NodeInfo nodeInfo = map.get(nodeId + "");
        return nodeInfo;
    }

    public int clearNodeInfo() {
        int size = map.size();
        map = new HashMap<String, NodeInfo>();
        return size;
    }

    public Set<NodeInfo> listAll() {
        Set<String> keys = map.keySet();
        if (keys == null || keys.isEmpty()) {
            return null;
        }
        Set<NodeInfo> result = new HashSet<NodeInfo>();
        for (String key : keys) {
            result.add(map.get(key));
        }
        return result;
    }
}
