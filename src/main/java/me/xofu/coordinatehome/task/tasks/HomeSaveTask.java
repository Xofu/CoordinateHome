package me.xofu.coordinatehome.task.tasks;

import me.xofu.coordinatehome.CoordinateHome;
import org.bukkit.scheduler.BukkitRunnable;

public class HomeSaveTask extends BukkitRunnable {

    private CoordinateHome instance;

    public HomeSaveTask(CoordinateHome instance) {
        this.instance = instance;

        runTaskTimerAsynchronously(instance, 1, 6000);
    }

    @Override
    public void run() {
        instance.getHomeManager().save();
    }
}
