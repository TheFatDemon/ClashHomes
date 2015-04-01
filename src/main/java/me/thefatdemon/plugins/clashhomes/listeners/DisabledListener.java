package me.thefatdemon.plugins.clashhomes.listeners;

import me.thefatdemon.plugins.clashhomes.ClashHomes;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class DisabledListener implements Listener {
    private final ClashHomes plugin;
    public DisabledListener(ClashHomes clashHomes) {
        this.plugin = clashHomes;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if (player.hasPermission("ch.admin.reload")){
            player.sendMessage(ChatColor.GOLD + "[ClashHomes] " + ChatColor.RED + "You have selected to use MySQL as a storage System but you haven't configured it yet. Fix that to enable this plugin.");
        }
    }
}
