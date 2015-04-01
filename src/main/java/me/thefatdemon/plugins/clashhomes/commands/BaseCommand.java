package me.thefatdemon.plugins.clashhomes.commands;

import me.thefatdemon.plugins.clashhomes.ClashHomes;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public abstract class BaseCommand implements CommandExecutor {

    protected final ClashHomes plugin;
    protected final String permission;

    public BaseCommand(ClashHomes clashHomes, String permission) {
        this.plugin = clashHomes;
        this.permission = permission;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args){
        switch (runCommand(commandSender, command, args)){
            case NO_PERMISSIONS:
                commandSender.sendMessage(ChatColor.DARK_RED + "[ClashHomes] No Permission");
                break;
            case PLAYER_ONLY:
                commandSender.sendMessage(ChatColor.DARK_RED + "[ClashHomes] Player Only Command");
                break;
            case CONSOLE_ONLY:
                commandSender.sendMessage(ChatColor.DARK_RED + "[ClashHomes] This command can only be run by console");
                break;
            case ERROR:
                commandSender.sendMessage(ChatColor.DARK_RED + "[ClashHomes] ClashHomes as experienced an Internal Error");
                break;
            case SILENT_ERROR:
            case SUCCESS:
            default:
                break;
        }
        return true;
    }

    protected abstract CommandResult runCommand(CommandSender commandSender, Command command, String[] args);
}
