package com.migu.schedule;


import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.NodeInfo;
import com.migu.schedule.info.TaskInfo;
import com.migu.schedule.info.TaskInfoEx;
import com.migu.schedule.service.NodeInfoService;
import com.migu.schedule.service.TaskInfoExService;
import com.migu.schedule.service.impl.NodeInfoServiceImpl;
import com.migu.schedule.service.impl.TaskInfoExServiceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/*
*类名和方法不能修改
 */
public class Schedule {

    private TaskInfoExService taskInfoExService = new TaskInfoExServiceImpl();

    private NodeInfoService nodeInfoService = new NodeInfoServiceImpl();

    public int init() {
        taskInfoExService.clearTaskInfo();
        nodeInfoService.clearNodeInfo();
        return ReturnCodeKeys.E001;
    }


    public int registerNode(int nodeId) {
        if (nodeId <= 0) {
            return ReturnCodeKeys.E004;
        }
        NodeInfo nodeInfo = nodeInfoService.queryNodeInfo(nodeId);
        if (nodeInfo != null) {
            return ReturnCodeKeys.E005;
        }
        nodeInfo = new NodeInfo();
        nodeInfo.setNodeId(nodeId);
        nodeInfoService.addNodeInfo(nodeInfo);
        return ReturnCodeKeys.E003;
    }

    public int unregisterNode(int nodeId) {
        if (nodeId <= 0) {
            return ReturnCodeKeys.E004;
        }
        NodeInfo nodeInfo = nodeInfoService.queryNodeInfo(nodeId);
        if (nodeInfo == null) {
            return ReturnCodeKeys.E007;
        }
        List<TaskInfoEx> taskInfoExes = nodeInfoService.deleteNodeInfo(nodeId);
        taskInfoExService.addTaskInfoList(taskInfoExes);
        return ReturnCodeKeys.E006;
    }


    public int addTask(int taskId, int consumption) {
        if (taskId <= 0) {
            return ReturnCodeKeys.E009;
        }
        TaskInfo taskInfo = taskInfoExService.queryTaskInfo(taskId);
        if (taskInfo != null) {
            return ReturnCodeKeys.E010;
        }
        taskInfoExService.addTaskInfo(taskId, consumption);
        return ReturnCodeKeys.E008;
    }


    public int deleteTask(int taskId) {
        if (taskId <= 0) {
            return ReturnCodeKeys.E009;
        }
        TaskInfo taskInfo = taskInfoExService.queryTaskInfo(taskId);
        if (taskInfo == null) {
            return ReturnCodeKeys.E012;
        }
        TaskInfoEx taskInfoEx = taskInfoExService.deleteTaskInfo(taskId);
        if (taskInfoEx.getNodeId() != 0) {
            nodeInfoService.deleteTaskInfo(taskInfoEx.getNodeId(), taskId);
        }
        return ReturnCodeKeys.E011;
    }


    public int scheduleTask(int threshold) {
        // TODO 方法未实现
        if (threshold <= 0) {
            return ReturnCodeKeys.E002;
        }

        int min = 0;
        int max = 0;
        List<TaskInfoEx> resultTaskInfoExList = new ArrayList<TaskInfoEx>();
        List<NodeInfo> resultNodeInfoList = new ArrayList<NodeInfo>();

        Set<TaskInfoEx> taskInfoExes = taskInfoExService.listAll();
        Set<NodeInfo> nodeInfos = nodeInfoService.listAll();

        Iterator<TaskInfoEx> iteratorTaskInfoEx = taskInfoExes.iterator();
        Iterator<NodeInfo> iteratorNodeInfo = nodeInfos.iterator();

        if (nodeInfos.size() >= taskInfoExes.size()) {
            while (iteratorTaskInfoEx.hasNext()) {
                TaskInfoEx taskInfoEx = iteratorTaskInfoEx.next();
                NodeInfo resultNodeInfo = iteratorNodeInfo.next();
                taskInfoEx.setNodeId(resultNodeInfo.getNodeId());
                resultTaskInfoExList.add(taskInfoEx);
                if (min == 0) {
                    min = taskInfoEx.getConsumption();
                }
                List<TaskInfoEx> taskInfos = new ArrayList<TaskInfoEx>();
                taskInfos.add(taskInfoEx);
                resultNodeInfo.setTaskInfoExList(taskInfos);
                resultNodeInfoList.add(resultNodeInfo);
                if (!iteratorTaskInfoEx.hasNext()) {
                    max = taskInfoEx.getConsumption();
                }
            }
            while (iteratorNodeInfo.hasNext()) {
                NodeInfo resultNodeInfo = iteratorNodeInfo.next();
                resultNodeInfo.setTaskInfoExList(null);
                resultNodeInfoList.add(resultNodeInfo);
            }
        } else {
            int count = 0;
            while (iteratorTaskInfoEx.hasNext()) {
                TaskInfoEx taskInfoEx = iteratorTaskInfoEx.next();
                count = count + taskInfoEx.getConsumption();
            }
        }

        if ((max - min) > threshold) {
            return ReturnCodeKeys.E013;
        } else {
            init();
            for (NodeInfo nodeInfo : resultNodeInfoList) {
                nodeInfoService.addNodeInfo(nodeInfo);
            }
            for (TaskInfoEx taskInfoEx : resultTaskInfoExList) {
                taskInfoExService.addTaskInfo(taskInfoEx);
            }
            return ReturnCodeKeys.E014;
        }
    }


    public int queryTaskStatus(List<TaskInfo> tasks) {
        // TODO 方法未实现
        return ReturnCodeKeys.E000;
    }

}
