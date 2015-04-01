package me.thefatdemon.plugins.clashhomes.storage;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public interface ClashStorage {

    public Location getHome(Player player);

    // Grab Home of a Player with name
    public Location getHome(Player player, String homeName);

    // Set Home of a Player
    public boolean setHome(Player player, String homeName, Location location);

    public boolean setHome(Player player, String homeName);

    public boolean setHome(Player player);

    public boolean updateHome(Player player);

    public boolean updateHome(Player player, String homeName);

    public boolean updateHome(Player player, String homeName, Location location);

    public boolean setSpawn(Location location, World world);

    public boolean updateSpawn(Location location, World world);

    public Location getSpawn(World world);
}
