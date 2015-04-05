package me.thefatdemon.plugins.clashhomes.storage;

import com.iciql.Db;
import me.thefatdemon.plugins.clashhomes.ClashHomes;
import me.thefatdemon.plugins.clashhomes.models.HomesModel;
import me.thefatdemon.plugins.clashhomes.models.SpawnsModel;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MySQLStorage implements ClashStorage {
    private final ClashHomes plugin;

    public MySQLStorage(ClashHomes clashHomes) {
        this.plugin = clashHomes;
    }

    @Override
    public Location getHome(Player player) {
        return this.getHome(player, "default");
    }

    @Override
    public Location getHome(Player player, String homeName) {
        Db db = plugin.openDatabase();
        try {
            HomesModel model = new HomesModel();
            HomesModel home = db.from(model)
                    .where(model.playerUUID)
                    .is(player.getUniqueId().toString())
                    .and(model.homeName)
                    .is(homeName).selectFirst();
            return new Location(plugin.getServer().getWorld(home.homeWorld), home.homeX, home.homeY, home.homeZ, home.homeYaw, home.homePitch);
        }catch (Exception ignored){
        }finally {
            db.close();
        }
        return null;
    }

    @Override
    public boolean setHome(Player player, String homeName, Location location) {
        Db db = plugin.openDatabase();
        try{
            HomesModel model = new HomesModel(player, homeName);
            db.insert(model);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        db.close();
        return true;
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
        Db db = plugin.openDatabase();
        try {
            HomesModel model = new HomesModel();
            db
                .from(model)
                .set(model.homeName).to(homeName)
                .set(model.homeX).to(location.getX())
                .set(model.homeY).to(location.getY())
                .set(model.homeZ).to(location.getZ())
                .set(model.homePitch).to(location.getPitch())
                .set(model.homeYaw).to(location.getYaw())
                .set(model.homeWorld).to(location.getWorld().getName())
                .where(model.homeName)
                .is(homeName)
                .and(model.playerUUID)
                .is(player.getUniqueId()
                .toString()).update();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    @Override
    public boolean setSpawn(Location location, World world) {
        Db db = plugin.openDatabase();
        try {
            SpawnsModel spawnsModel = new SpawnsModel(world, location);
            db.insert(spawnsModel);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    @Override
    public boolean updateSpawn(Location location, World world) {
        Db db = plugin.openDatabase();
        try {
            SpawnsModel spawnsModel = new SpawnsModel();
            db
                .from(spawnsModel)
                .set(spawnsModel.spawnX).to(location.getX())
                .set(spawnsModel.spawnY).to(location.getY())
                .set(spawnsModel.spawnZ).to(location.getZ())
                .set(spawnsModel.spawnPitch).to(location.getPitch())
                .set(spawnsModel.spawnYaw).to(location.getYaw())
                .where(spawnsModel.worldName)
                .is(world.getName())
                .update();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Location getSpawn(World world) {
        Db db = plugin.openDatabase();
        try {
            SpawnsModel spawnsModel = new SpawnsModel();
            SpawnsModel spawn = db.from(spawnsModel).where(spawnsModel.worldName).is(world.getName()).selectFirst();
            return new Location(
                plugin.getServer().getWorld(spawn.worldName),
                spawn.spawnX,
                spawn.spawnY,
                spawn.spawnZ,
                spawn.spawnYaw,
                spawn.spawnPitch
            );
        }catch (Exception e){
            return null;
        }finally {
            db.close();
        }
    }

    @Override
    public List<String> getHomesList(Player player) {
        Db db = plugin.openDatabase();
        try {
            HomesModel model = new HomesModel();
            return db.
                    from(model).
                    where(model.playerUUID).
                    is(player.getUniqueId().toString()).
                    select(model.homeName);
        }catch (Exception e){
            return new ArrayList<>(0);
        }finally {
            db.close();
        }
    }
}
