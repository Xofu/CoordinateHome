package me.xofu.coordinatehome.task;

import me.xofu.coordinatehome.CoordinateHome;
import me.xofu.coordinatehome.task.tasks.HomeSaveTask;

public class TaskManager {

    private CoordinateHome instance;

    private HomeSaveTask homeSaveTask;

    public TaskManager(CoordinateHome instance) {
        this.instance = instance;

        homeSaveTask = new HomeSaveTask(instance);
    }

    public void runTasks() {
        homeSaveTask.run();
    }
}
