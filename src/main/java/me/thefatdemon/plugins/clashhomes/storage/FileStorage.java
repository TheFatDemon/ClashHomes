package me.thefatdemon.plugins.clashhomes.storage;

import me.thefatdemon.plugins.clashhomes.ClashHomes;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class FileStorage implements ClashStorage {
    private final ClashHomes plugin;
    private FileConfiguration config;

    public FileStorage(ClashHomes clashHomes) {
        this.plugin = clashHomes;
        this.config = plugin.getConfig();
    }

    @Override
    public Location getHome(Player player) {
        return this.getHome(player, "default");
    }

    @Override
    public Location getHome(Player player, String homeName) {
        if (!config.isSet("homes." + player.getUniqueId().toString() + "." + homeName)){
            return null;
        }
        return new Location(plugin.getServer().getWorld(config.getString("homes." + player.getUniqueId().toString() + "." + homeName + ".world")),
                config.getDouble(("homes." + player.getUniqueId().toString() + "." + homeName + ".x")),
                config.getDouble(("homes." + player.getUniqueId().toString() + "." + homeName + ".y")),
                config.getDouble(("homes." + player.getUniqueId().toString() + "." + homeName + ".z")),
                (float)config.getDouble(("homes." + player.getUniqueId().toString() + "." + homeName + ".yaw")),
                (float)config.getDouble(("homes." + player.getUniqueId().toString() + "." + homeName + ".pitch")));
    }

    @Override
    public boolean setHome(Player player, String homeName, Location location) {
        try {
            config.set(("homes." + player.getUniqueId().toString() + "." + homeName + ".world"), location.getWorld().getName());
            config.set(("homes." + player.getUniqueId().toString() + "." + homeName + ".x"), location.getX());
            config.set(("homes." + player.getUniqueId().toString() + "." + homeName + ".y"), location.getY());
            config.set(("homes." + player.getUniqueId().toString() + "." + homeName + ".z"), location.getZ());
            config.set(("homes." + player.getUniqueId().toString() + "." + homeName + ".yaw"),(double) location.getYaw());
            config.set(("homes." + player.getUniqueId().toString() + "." + homeName + ".pitch"), (double) location.getPitch());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean setHome(Player player, String homeName) {
        return this.setHome(player, homeName, player.getLocation());
    }

    @Override
    public boolean setHome(Player player) {
        return this.setHome(player, "default");
    }

    @Override
    public boolean updateHome(Player player) {
        return this.updateHome(player, "default");
    }

    @Override
    public boolean updateHome(Player player, String homeName) {
        return this.updateHome(player, homeName, player.getLocation());
    }

    @Override
    public boolean updateHome(Player player, String homeName, Location location) {
        return this.setHome(player, homeName, location);
    }

    @Override
    public boolean deleteHome(Player player) {
        return this.deleteHome(player, "default");
    }

    @Override
    public boolean deleteHome(Player player, String homeName) {
        try {
            config.getList("homes." + player.getUniqueId().toString()).remove(homeName);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean setSpawn(Location location, World world) {
        try {
            config.set(config.getString("spawns." + world.getName() + ".world"), location.getWorld().getName());
            config.set("spawns." + world.getName() + ".x", location.getX());
            config.set("spawns." + world.getName() + ".y", location.getY());
            config.set("spawns." + world.getName() + ".z", location.getZ());
            config.set("spawns." + world.getName() + ".yaw",(double) location.getYaw());
            config.set("spawns." + world.getName() + ".pitch", (double) location.getPitch());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateSpawn(Location location, World world) {
        return this.setSpawn(location, world);
    }

    @Override
    public Location getSpawn(World world) {
        if (!config.isSet("spawns." + world.getName())){
            return null;
        }
        return new Location(plugin.getServer().getWorld(config.getString("spawns." + world.getName() + ".world")),
            config.getDouble(config.getString("spawns." + world.getName() + ".x")),
            config.getDouble(config.getString("spawns." + world.getName() + ".y")),
            config.getDouble(config.getString("spawns." + world.getName() + ".z")),
            (float)config.getDouble(config.getString("spawns." + world.getName() + ".yaw")),
            (float)config.getDouble(config.getString("spawns." + world.getName() + ".pitch")));

    }

    @Override
    public List<String> getHomesList(Player player) {
        return config.getStringList("homes." + player.getUniqueId().toString() + "");
    }
}

