package com.migu.schedule.service;

import com.migu.schedule.info.TaskInfoEx;

import java.util.List;
import java.util.Set;

public interface TaskInfoExService {

    int addTaskInfo(int taskId, int consumption);

    int addTaskInfo(TaskInfoEx taskInfoEx);

    TaskInfoEx deleteTaskInfo(int taskId);

    int addTaskInfoList(List<TaskInfoEx> taskInfoList);

    TaskInfoEx queryTaskInfo(int taskId);

    int clearTaskInfo();

    Set<TaskInfoEx> listAll();
}
