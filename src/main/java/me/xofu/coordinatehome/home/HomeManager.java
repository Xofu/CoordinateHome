package me.xofu.coordinatehome.home;

import me.xofu.coordinatehome.CoordinateHome;
import me.xofu.coordinatehome.file.ConfigFile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HomeManager {

    private CoordinateHome instance;

    private List<Home> homes;
    private ConfigFile homesFile;
    private FileConfiguration homesConfig;

    public HomeManager(CoordinateHome instance) {
        this.instance = instance;

        homes = new ArrayList<>();
        homesFile = new ConfigFile("homes.yml", instance);
        homesConfig = homesFile.getConfig();

        load();
    }

    public void addHome(Home home) {
        homes.add(home);
    }

    public void removeHome(Home home) {
        homes.remove(home);
    }

    public boolean hasHome(UUID uuid) {
        if(getHomeByOwner(uuid) != null) {
            return true;
        }
        return false;
    }

    public Home getHomeByOwner(UUID uuid) {
        for(Home home: getHomes()) {
            if(home.getOwner().equals(uuid)) {
                return home;
            }
        }
        return null;
    }

    public List<Home> getHomes() {
        return homes;
    }

    public void load() {
        for(String string: homesConfig.getStringList("List.homes")) {
            UUID uuid = UUID.fromString(string);

            World world = Bukkit.getWorld(homesConfig.getString("Home." + string + ".world"));
            int blockX = homesConfig.getInt("Home." + string + ".x");
            int blockY = homesConfig.getInt("Home." + string + ".y");
            int blockZ = homesConfig.getInt("Home." + string + ".z");

            Location location = new Location(world, blockX, blockY, blockZ);

            Home home = new Home(uuid, location);
            addHome(home);
        }
    }

    public void save() {
        List<String> homesList = new ArrayList<>();

        homesConfig.set("Home", null);
        homesFile.save();
        for(Home home: getHomes()) {
            homesList.add(home.getOwner().toString());
            homesConfig.set("Home." + home.getOwner().toString() + ".world", home.getLocation().getWorld().getName());
            homesConfig.set("Home." + home.getOwner().toString() + ".x", home.getLocation().getBlockX());
            homesConfig.set("Home." + home.getOwner().toString() + ".y", home.getLocation().getBlockY());
            homesConfig.set("Home." + home.getOwner().toString() + ".z", home.getLocation().getBlockZ());
            homesFile.save();
        }
        homesConfig.set("List.homes", homesList);
        homesFile.save();
    }
}
