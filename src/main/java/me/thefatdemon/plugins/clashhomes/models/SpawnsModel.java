package me.thefatdemon.plugins.clashhomes.models;

import com.iciql.Iciql.*;
import org.bukkit.Location;
import org.bukkit.World;

@IQTable(name = "clashhomes_spawns")
public class SpawnsModel {
    @IQColumn(name = "id", primaryKey = true, autoIncrement = true)
    public int id;

    @IQColumn(name = "world_name", length = 20)
    public String worldName;

    @IQColumn(name = "spawn_x")
    public double spawnX;

    @IQColumn(name = "spawn_y")
    public double spawnY;

    @IQColumn(name = "spawn_z")
    public double spawnZ;

    @IQColumn(name = "spawn_pitch")
    public float spawnPitch;

    @IQColumn(name = "spawn_yaw")
    public float spawnYaw;

    public SpawnsModel(){
        // Default Constructor
    }

    public SpawnsModel(World world, Location location){
        this.worldName = world.getName();
        this.spawnX = location.getX();
        this.spawnY = location.getY();
        this.spawnZ = location.getZ();
        this.spawnPitch = location.getPitch();
        this.spawnYaw = location.getYaw();
    }
}
