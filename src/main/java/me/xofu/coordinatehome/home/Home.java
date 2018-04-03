package me.xofu.coordinatehome.home;

import org.bukkit.Location;

import java.util.UUID;

public class Home {

    private UUID owner;
    private Location location;

    public Home(UUID owner, Location location) {
        this.owner = owner;
        this.location = location;
    }

    public UUID getOwner() {
        return owner;
    }

    public Location getLocation() {
        return location;
    }
}
