package me.xofu.coordinatehome;

import me.xofu.coordinatehome.commands.DelHomeCommand;
import me.xofu.coordinatehome.commands.HomeCommand;
import me.xofu.coordinatehome.commands.SetHomeCommand;
import me.xofu.coordinatehome.home.HomeManager;
import me.xofu.coordinatehome.task.TaskManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CoordinateHome extends JavaPlugin {

    private HomeManager homeManager;
    private TaskManager taskManager;

    @Override
    public void onEnable() {
        homeManager = new HomeManager(this);
        taskManager = new TaskManager(this);

        registerCommands();
        taskManager.runTasks();

        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        homeManager.save();
    }

    public void registerCommands() {
        getCommand("sethome").setExecutor(new SetHomeCommand(this));
        getCommand("delhome").setExecutor(new DelHomeCommand(this));
        getCommand("home").setExecutor(new HomeCommand(this));
    }

    public HomeManager getHomeManager() {
        return homeManager;
    }
}
