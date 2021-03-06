package me.xofu.coordinatehome.commands;

import me.xofu.coordinatehome.CoordinateHome;
import me.xofu.coordinatehome.home.Home;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {

    private CoordinateHome instance;

    public HomeCommand(CoordinateHome instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', instance.getConfig().getString("NOT_A_PLAYER")));
            return true;
        }

        Player player = (Player) sender;
        if(!player.hasPermission("coordinatehome.use")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', instance.getConfig().getString("NO_PERMISSION")));
            return true;
        }

        if(!instance.getHomeManager().hasHome(player.getUniqueId())) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', instance.getConfig().getString("NO_HOME")));
            return true;
        }

        Home home = instance.getHomeManager().getHomeByOwner(player.getUniqueId());
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', instance.getConfig().getString("HOME").replace("%world%", home.getLocation().getWorld().getName()).replace("%x%", String.valueOf(home.getLocation().getBlockX())).replace("%y%", String.valueOf(home.getLocation().getBlockY())).replace("%z%", String.valueOf(home.getLocation().getBlockZ()))));
        return false;
    }
}
