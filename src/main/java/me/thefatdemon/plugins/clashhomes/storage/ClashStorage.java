package me.thefatdemon.plugins.clashhomes.storage;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;

public interface ClashStorage {

    Location getHome(Player player);

    // Grab Home of a Player with name
    Location getHome(Player player, String homeName);

    // Get Homes List of Player
    List<String> getHomesList(Player player);

    // Set Home of a Player
    boolean setHome(Player player);

    boolean setHome(Player player, String homeName);

    boolean setHome(Player player, String homeName, Location location);

    // Change Home of a Player
    boolean updateHome(Player player);

    boolean updateHome(Player player, String homeName);

    boolean updateHome(Player player, String homeName, Location location);

    // Delete a Player Home
    boolean deleteHome(Player player);

    boolean deleteHome(Player player, String homeName);

    // Set Spawn for World
    boolean setSpawn(Location location, World world);

    // Change Spawn for World
    boolean updateSpawn(Location location, World world);

    // Get Spawn for World
    Location getSpawn(World world);
}
