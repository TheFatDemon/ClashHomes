package me.thefatdemon.plugins.clashhomes.commands.base;

import me.thefatdemon.plugins.clashhomes.ClashHomes;
import me.thefatdemon.plugins.clashhomes.commands.BaseCommand;
import me.thefatdemon.plugins.clashhomes.commands.CommandResult;
import me.thefatdemon.plugins.clashhomes.storage.ClashStorage;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelHomeCommand extends BaseCommand {
    public DelHomeCommand(ClashHomes clashHomes, String s) {
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

        ClashStorage storage = plugin.getLocationManager();

        if (args.length != 0){
            if (storage.deleteHome(player, args[0])){
                player.sendMessage(ChatColor.GOLD + "[ClashHomes] Home Deleted");
                return CommandResult.SUCCESS;
            }else {
                return CommandResult.ERROR;
            }
        }else {
            if (storage.deleteHome(player)){
                player.sendMessage(ChatColor.GOLD + "[ClashHomes] Home Deleted");
                return CommandResult.SUCCESS;
            }else {
                return CommandResult.ERROR;
            }
        }
    }
}
