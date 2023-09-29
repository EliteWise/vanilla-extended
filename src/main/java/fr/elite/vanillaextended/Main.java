package fr.elite.vanillaextended;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        TeleportRequest teleportRequest = new TeleportRequest();
        this.getCommand("tpa").setExecutor(teleportRequest);
        this.getCommand("tpok").setExecutor(teleportRequest);
        this.getCommand("spawn").setExecutor(new SpawnTeleport());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public String formatBiomeName(String biomeName) {
        return biomeName.substring(0, 1).toUpperCase() + biomeName.substring(1).toLowerCase();
    }

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        String playerBiome = player.getLocation().getBlock().getBiome().toString();
        player.setPlayerListName("[" + formatBiomeName(playerBiome) + "] " + player.getName());
    }

    private final Map<Player, Biome> previousBiomes = new HashMap<>();

    @EventHandler
    public void PlayerChangeBiome(PlayerMoveEvent e) {
        Player movingPlayer = e.getPlayer();
        Biome currentBiome = movingPlayer.getLocation().getBlock().getBiome();
        Biome previousBiome = previousBiomes.get(movingPlayer);

        if(previousBiome != null && previousBiome == currentBiome) return;

        movingPlayer.setPlayerListName("[" + formatBiomeName(currentBiome.toString()) + "] " + movingPlayer.getName());

        previousBiomes.put(movingPlayer, currentBiome);
    }

}
