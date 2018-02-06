package me.robpizza.core.commands;

import me.robpizza.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Msg implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("msg")) {
                String Prefix = ChatColor.translateAlternateColorCodes('&',Main.configs().getMessagesConfig().getString("Message-prefix") + " ");
                if (args.length <= 1) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cUse /msg <player> <message>!"));
                    return true;
                }
                Player t = Bukkit.getServer().getPlayer(args[0]);
                if (t == null) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',Prefix + "&cPlayer &f " + args[0] + " &cnot found!"));
                    return true;
                }
                StringBuilder str = new StringBuilder();
                for(int i = 1; i < args.length; i++) {
                    str.append(args[i] + " ");
                }
                String msg = str.toString();
                String messagesender = Main.configs().getMessagesConfig().getString("Msg-sender");
                messagesender = messagesender.replace("{player}", t.getName());
                String messagereceiver = Main.configs().getMessagesConfig().getString("Msg-receiver");
                messagereceiver = messagereceiver.replace("{player}", p.getName());

                t.sendMessage(ChatColor.translateAlternateColorCodes('&',messagereceiver + "&r: " + msg));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',messagesender + "&r: " + msg));
                return true;
            }
        } else {
            Bukkit.getLogger().severe("You can only run this command as player!");
            return true;
        }
        return false;
    }
}
