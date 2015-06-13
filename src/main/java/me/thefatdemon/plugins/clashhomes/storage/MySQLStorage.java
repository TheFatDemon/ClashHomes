package me.thefatdemon.plugins.clashhomes.storage;

import com.iciql.Db;
import com.iciql.util.Utils;
import me.thefatdemon.plugins.clashhomes.ClashHomes;
import me.thefatdemon.plugins.clashhomes.models.HomesModel;
import me.thefatdemon.plugins.clashhomes.models.SpawnsModel;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import javax.rmi.CORBA.Util;
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
        try (Db db = plugin.openDatabase()) {
            ThreadLocal<HomesModel> tableSafe = Utils.newThreadLocal(HomesModel.class);
            HomesModel home = db.from(tableSafe.get())
                    .where(tableSafe.get().playerUUID)
                    .is(player.getUniqueId().toString())
                    .and(tableSafe.get().homeName)
                    .is(homeName).selectFirst();
            return new Location(plugin.getServer().getWorld(home.homeWorld), home.homeX, home.homeY, home.homeZ, home.homeYaw, home.homePitch);
        } catch (Exception ignored) {
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
        try (Db db = plugin.openDatabase()) {
            ThreadLocal<HomesModel> threadLocal = Utils.newThreadLocal(HomesModel.class);
            HomesModel model = threadLocal.get();
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
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteHome(Player player) {
        return deleteHome(player, "default");
    }

    @Override
    public boolean deleteHome(Player player, String homeName) {
        try (Db db = plugin.openDatabase()) {
            ThreadLocal<HomesModel> threadLocal = Utils.newThreadLocal(HomesModel.class);
            db.from(threadLocal.get())
                    .where(threadLocal.get().playerUUID)
                    .is(player.getUniqueId().toString())
                    .and(threadLocal.get().homeName)
                    .is(homeName)
                    .delete();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean setSpawn(Location location, World world) {
        try (Db db = plugin.openDatabase()) {
            SpawnsModel spawnsModel = new SpawnsModel(world, location);
            db.insert(spawnsModel);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateSpawn(Location location, World world) {
        Db db = plugin.openDatabase();
        try {
            ThreadLocal<SpawnsModel> threadLocal = Utils.newThreadLocal(SpawnsModel.class);
            db
                .from(threadLocal.get())
                .set(threadLocal.get().spawnX).to(location.getX())
                .set(threadLocal.get().spawnY).to(location.getY())
                .set(threadLocal.get().spawnZ).to(location.getZ())
                .set(threadLocal.get().spawnPitch).to(location.getPitch())
                .set(threadLocal.get().spawnYaw).to(location.getYaw())
                .where(threadLocal.get().worldName)
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
        try (Db db = plugin.openDatabase()) {
            ThreadLocal<SpawnsModel> threadLocal = Utils.newThreadLocal(SpawnsModel.class);
            SpawnsModel spawn = db.from(threadLocal.get()).where(threadLocal.get().worldName).is(world.getName()).selectFirst();
            return new Location(
                    plugin.getServer().getWorld(spawn.worldName),
                    spawn.spawnX,
                    spawn.spawnY,
                    spawn.spawnZ,
                    spawn.spawnYaw,
                    spawn.spawnPitch
            );
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<String> getHomesList(Player player) {
        try (Db db = plugin.openDatabase()) {
            ThreadLocal<HomesModel> threadLocal = Utils.newThreadLocal(HomesModel.class);
            return db.
                    from(threadLocal.get()).
                    where(threadLocal.get().playerUUID).
                    is(player.getUniqueId().toString()).
                    select(threadLocal.get().homeName);
        } catch (Exception e) {
            return new ArrayList<>(0);
        }
    }
}
