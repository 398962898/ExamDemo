package com.migu.schedule.service.impl;

import com.migu.schedule.info.TaskInfoEx;
import com.migu.schedule.service.TaskInfoExService;

import java.util.*;

public class TaskInfoExServiceImpl implements TaskInfoExService {

    private static Map<String, TaskInfoEx> map = new HashMap<String, TaskInfoEx>();

    public int addTaskInfo(int taskId, int consumption) {
        TaskInfoEx taskInfoEx = new TaskInfoEx();
        taskInfoEx.setTaskId(taskId);
        taskInfoEx.setConsumption(consumption);
        addTaskInfo(taskInfoEx);
        return 1;
    }

    public int addTaskInfo(TaskInfoEx taskInfoEx) {
        map.put(taskInfoEx.getTaskId()+"",taskInfoEx);
        return 1;
    }

    public TaskInfoEx deleteTaskInfo(int taskId) {
        TaskInfoEx taskInfoEx = map.remove(String.valueOf(taskId));
        return taskInfoEx;
    }

    public int addTaskInfoList(List<TaskInfoEx> taskInfoList) {
        if (taskInfoList == null || taskInfoList.isEmpty()) {
            return 0;
        }
        for (TaskInfoEx taskInfoEx : taskInfoList) {
            map.put(taskInfoEx.getTaskId() + "", taskInfoEx);
        }
        return taskInfoList.size();
    }

    public TaskInfoEx queryTaskInfo(int taskId) {
        return map.get(String.valueOf(taskId));
    }

    public int clearTaskInfo() {
        int size = map.size();
        map = new HashMap<String, TaskInfoEx>();
        return size;
    }

    public Set<TaskInfoEx> listAll() {
        Set<String> keys = map.keySet();
        if (keys == null || keys.isEmpty()) {
            return null;
        }
        Set<TaskInfoEx> result = new HashSet<TaskInfoEx>();
        for (String key : keys) {
            result.add(map.get(key));
        }
        return result;
    }
}
