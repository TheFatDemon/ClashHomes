package me.thefatdemon.plugins.clashhomes.commands.base;

import me.thefatdemon.plugins.clashhomes.ClashHomes;
import me.thefatdemon.plugins.clashhomes.commands.BaseCommand;
import me.thefatdemon.plugins.clashhomes.commands.CommandResult;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCommand extends BaseCommand {
    public SetHomeCommand(ClashHomes clashHomes, String s) {
        super(clashHomes, s);
    }

    @Override
    protected CommandResult runCommand(CommandSender commandSender, Command command, String[] args) {
        if(!(commandSender instanceof Player)){
            return CommandResult.PLAYER_ONLY;
        }
        Player player = (Player) commandSender;
        if (!player.hasPermission(permission)){
            return CommandResult.NO_PERMISSIONS;
        }
        if (args.length == 0){
            if (plugin.getLocationManager().getHome(player) == null){
                plugin.getLocationManager().setHome(player);
                player.sendMessage(ChatColor.GOLD + "[ClashHomes] Default Home Set");
                return CommandResult.SUCCESS;
            }else {
                plugin.getLocationManager().updateHome(player);
                player.sendMessage(ChatColor.GOLD + "[ClashHomes] Default Home Updated");
                return CommandResult.SUCCESS;
            }
        }if (args.length > 0){
            if (!player.hasPermission(ClashHomes.Permissions.VIP_MULTI.toString())){
                return CommandResult.NO_PERMISSIONS;
            }
            String homeName = args[0];
            Location homeLocation = plugin.getLocationManager().getHome(player, homeName);
            if (homeLocation == null){
                player.sendMessage(ChatColor.DARK_RED + "[ChatHomes] " + homeName + " Set");
                plugin.getLocationManager().setHome(player, homeName);
                return CommandResult.SUCCESS;
            }
            plugin.getLocationManager().updateHome(player, homeName);
            player.sendMessage(ChatColor.GOLD + "[ChatHomes] " + homeName + " Updated");
            return CommandResult.SUCCESS;
        }
        return CommandResult.SILENT_ERROR;
    }
}
