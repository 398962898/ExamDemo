package com.migu.schedule.service;

import com.migu.schedule.info.NodeInfo;
import com.migu.schedule.info.TaskInfo;
import com.migu.schedule.info.TaskInfoEx;

import java.util.List;
import java.util.Set;

public interface NodeInfoService {

    int addNodeInfo(NodeInfo nodeInfo);

    List<TaskInfoEx> deleteNodeInfo(int nodeId);

    NodeInfo queryNodeInfo(int nodeId);

    int clearNodeInfo();

    int deleteTaskInfo(int nodeId, int taskId);

    Set<NodeInfo> listAll();
}
