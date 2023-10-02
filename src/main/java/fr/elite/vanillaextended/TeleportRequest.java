package fr.elite.vanillaextended;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class TeleportRequest implements CommandExecutor {

    private Map<Player, Player> teleportRequests = new HashMap<Player, Player>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

                if(command.getName().equalsIgnoreCase("tpa")) {
                    if(args.length == 1) {
                        if(Bukkit.getOnlinePlayers().stream().anyMatch(p -> p.getName().equalsIgnoreCase(args[0]))) {
                            player.sendMessage("Teleport request sent!");
                            Bukkit.getPlayer(args[0]).sendMessage(player.getName() + " wants to teleport to you! /tpok to accept.");
                            teleportRequests.put(Bukkit.getPlayer(args[0]), player);
                        } else {
                            player.sendMessage("The player doesn't exist or is not connected.");
                        }
                    } else {
                        player.sendMessage("Correct format: /tpa <Player>");
                    }
                } else if(command.getName().equalsIgnoreCase("tpok")) {
                    if(args.length == 0) {
                        Player requester = teleportRequests.remove(player);
                        if(requester != null) {
                            requester.teleport(player);
                        }
                    } else {
                        player.sendMessage("Correct format: /tpok");
                    }
                }
        }

        return true;
    }

}
