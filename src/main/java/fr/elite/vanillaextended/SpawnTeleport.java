package fr.elite.vanillaextended;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnTeleport implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if(command.getName().equalsIgnoreCase("spawn")) {
                player.teleport(Bukkit.getWorld("world").getSpawnLocation());
                return true;
            }
        }
        return true;
    }
}
