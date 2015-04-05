package me.thefatdemon.plugins.clashhomes;

import com.iciql.Db;
import me.thefatdemon.plugins.clashhomes.commands.base.*;
import me.thefatdemon.plugins.clashhomes.listeners.DisabledListener;
import me.thefatdemon.plugins.clashhomes.storage.ClashStorage;
import me.thefatdemon.plugins.clashhomes.storage.FileStorage;
import me.thefatdemon.plugins.clashhomes.storage.MySQLStorage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class ClashHomes extends JavaPlugin {

    @Override
    public void onEnable(){
        Logger logger = getServer().getLogger();
        logger.info("Begin Enabling of ClashHomes");

        FileConfiguration config = getConfig();
        config.addDefault("storage", "mysql");

        // Add Basic MySQL Stuff
        config.addDefault("mysql.host", "localhost");
        config.addDefault("mysql.user", "username");
        config.addDefault("mysql.pass", "password");
        config.addDefault("mysql.database", "clashhomes");
        config.addDefault("mysql.port", "3306");
        config.addDefault("mysql.configured", false);
        config.options().copyDefaults(true);
        saveConfig();

        if (config.getString("storage").equals("mysql") && !config.getBoolean("mysql.configured")){
            logger.info("Disabling to allow Fixing of Homes.");
            getServer().getPluginManager().registerEvents(new DisabledListener(this), this);
            return;
        }

        getCommand("home").setExecutor(new HomeCommand(this, Permissions.BASIC_HOME.toString()));
        getCommand("homes").setExecutor(new HomesCommand(this, Permissions.BASIC_HOMES_LIST.toString()));
        getCommand("sethome").setExecutor(new SetHomeCommand(this, Permissions.BASIC_SET_HOME.toString()));
        getCommand("setspawn").setExecutor(new SetSpawncommand(this, Permissions.ADMIN_SET_SPAWN.toString()));
        getCommand("spawn").setExecutor(new SpawnCommand(this, Permissions.BASIC_SPAWN.toString()));
    }

    @Override
    public void onDisable(){
        // Literally No reason for this to be here...
        Logger log = this.getLogger();
        log.info("Disabling ClashHomes");
    }

    public ClashStorage getLocationManager(){
        if (getConfig().getString("storage").equals("mysql")){
            return new MySQLStorage(this);
        }else {
            return new FileStorage(this);
        }
    }

    public Db openDatabase(){
        FileConfiguration config = getConfig();
        return Db.open("jdbc:mysql://" +
                config.getString("mysql.host") +
                ":" + config.getString("mysql.port") +
                "/" + config.getString("mysql.database"),
                config.getString("mysql.user"),
                config.getString("mysql.pass"));
    }

    public enum Permissions{
        BASIC_HOME("ch.basic.home"),
        BASIC_HOMES_LIST("ch.basic.list"),
        BASIC_SET_HOME("ch.basic.sethome"),
        BASIC_SPAWN ("ch.basic.spawn"),
        ADMIN_SET_SPAWN ("ch.admin.setspawn"),
        VIP_MULTI ("ch.multi");

        private final String stringValue;
        Permissions(final String s) { stringValue = s; }
        @Override
        public String toString(){ return stringValue; }
    }
}
