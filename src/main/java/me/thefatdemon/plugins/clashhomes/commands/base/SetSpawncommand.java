package me.thefatdemon.plugins.clashhomes.commands.base;

import me.thefatdemon.plugins.clashhomes.ClashHomes;
import me.thefatdemon.plugins.clashhomes.commands.BaseCommand;
import me.thefatdemon.plugins.clashhomes.commands.CommandResult;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawncommand extends BaseCommand {
    public SetSpawncommand(ClashHomes clashHomes, String s) {
        super(clashHomes, s);
    }

    @Override
    protected CommandResult runCommand(CommandSender commandSender, Command command, String[] args) {
        if (!(commandSender instanceof Player)){
            return CommandResult.PLAYER_ONLY;
        }
        Player player = (Player) commandSender;
        if (!player.hasPermission(permission)){
            return CommandResult.NO_PERMISSIONS;
        }
        if (!plugin.getLocationManager().setSpawn(player.getLocation(), player.getWorld())){
            return CommandResult.ERROR;
        }
        player.sendMessage(ChatColor.GOLD + "[ClashHomes] Spawn for " + player.getWorld().getName() + " set");
        return CommandResult.SUCCESS;
    }
}
