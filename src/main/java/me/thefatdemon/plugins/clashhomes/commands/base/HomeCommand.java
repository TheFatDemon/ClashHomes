package me.thefatdemon.plugins.clashhomes.commands.base;

import me.thefatdemon.plugins.clashhomes.ClashHomes;
import me.thefatdemon.plugins.clashhomes.commands.BaseCommand;
import me.thefatdemon.plugins.clashhomes.commands.CommandResult;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand extends BaseCommand {
    public HomeCommand(ClashHomes clashHomes, String s) {
        super(clashHomes, s);
    }

    @Override
    protected CommandResult runCommand(CommandSender commandSender, Command command, String[] args) {
        if (!(commandSender instanceof Player)){
            return CommandResult.PLAYER_ONLY;
        }
        Player player = (Player) commandSender;
        if (args.length == 0){
            if (!player.hasPermission(permission)){
                return CommandResult.NO_PERMISSIONS;
            }
            Location home = plugin.getLocationManager().getHome(player);
            if (home == null){
                player.sendMessage(ChatColor.RED + "[ClashHomes] Your Home Doesn't Exist");
                return CommandResult.SILENT_ERROR;
            }
            player.teleport(home);
            player.sendMessage(ChatColor.GOLD + "[ClashHomes] Home Sweet Home");
            return CommandResult.SUCCESS;
        }else if(args.length == 1 ){
            if (!player.hasPermission(ClashHomes.Permissions.VIP_MULTI.toString())){
                return CommandResult.NO_PERMISSIONS;
            }
            Location home = plugin.getLocationManager().getHome(player, args[0]);
            if (home == null){
                player.sendMessage(ChatColor.RED + "[ClashHomes] Home " + args[0] + " doesn't exist");
                return CommandResult.SILENT_ERROR;
            }
            player.teleport(home);
            player.sendMessage(ChatColor.GOLD + "[ClashHomes] Home Sweet Home");
            return CommandResult.SUCCESS;
        }else {
            return CommandResult.SILENT_ERROR;
        }
    }
}
