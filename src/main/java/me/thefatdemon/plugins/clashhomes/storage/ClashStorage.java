package me.thefatdemon.plugins.clashhomes.storage;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;

public interface ClashStorage {

    Location getHome(Player player);

    // Grab Home of a Player with name
    Location getHome(Player player, String homeName);

    // Set Home of a Player
    boolean setHome(Player player, String homeName, Location location);

    boolean setHome(Player player, String homeName);

    boolean setHome(Player player);

    boolean updateHome(Player player);

    boolean updateHome(Player player, String homeName);

    boolean updateHome(Player player, String homeName, Location location);

    boolean setSpawn(Location location, World world);

    boolean updateSpawn(Location location, World world);

    Location getSpawn(World world);

    List<String> getHomesList(Player player);
}
