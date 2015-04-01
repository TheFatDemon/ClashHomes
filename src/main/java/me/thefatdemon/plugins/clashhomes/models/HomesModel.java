package me.thefatdemon.plugins.clashhomes.models;

import com.iciql.Iciql.*;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@IQTable(name = "clashhomes_homes")
public class HomesModel {
    @IQColumn(name = "id", primaryKey = true, autoIncrement = true)
    public int id;

    @IQColumn(name = "player_uuid", length = 36)
    public String playerUUID;

    @IQColumn(name = "home_name", length = 20)
    public String homeName;

    @IQColumn(name = "home_world", length = 20)
    public String homeWorld;

    @IQColumn(name = "home_x")
    public double homeX;

    @IQColumn(name = "home_y")
    public double homeY;

    @IQColumn(name = "home_z")
    public double homeZ;

    @IQColumn(name = "home_pitch")
    public float homePitch;

    @IQColumn(name = "home_yaw")
    public float homeYaw;

    public HomesModel(){
        // Default Constructor
    }

    public HomesModel(Player player, String homeName){

        Location location = player.getLocation();

        this.playerUUID = player.getUniqueId().toString();
        this.homeName = homeName;
        this.homeX = location.getX();
        this.homeY = location.getY();
        this.homeZ = location.getZ();
        this.homePitch = location.getPitch();
        this.homeYaw = location.getYaw();
        this.homeWorld = location.getWorld().getName();
    }
}
