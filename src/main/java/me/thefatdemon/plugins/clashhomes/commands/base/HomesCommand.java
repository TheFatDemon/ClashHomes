package me.thefatdemon.plugins.clashhomes.commands.base;

import me.thefatdemon.plugins.clashhomes.ClashHomes;
import me.thefatdemon.plugins.clashhomes.commands.BaseCommand;
import me.thefatdemon.plugins.clashhomes.commands.CommandResult;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class HomesCommand extends BaseCommand {
    public HomesCommand(ClashHomes clashHomes, String s) {
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

        List<String> homes = plugin.getLocationManager().getHomesList(player);
        if (homes.size() == 0 || homes.isEmpty()){
            player.sendMessage(ChatColor.GOLD + "[ClashHomes] No Homes in Database");
            return CommandResult.SUCCESS;
        }

        player.sendMessage(ChatColor.GOLD + "[ClashHomes] List Of Homes");

        for (String home : homes){
            player.sendMessage(ChatColor.GOLD + "[ClashHomes] - " + home);
        }

        return CommandResult.SUCCESS;
    }
}
