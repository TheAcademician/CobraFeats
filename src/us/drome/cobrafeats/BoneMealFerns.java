package us.drome.cobrafeats;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class BoneMealFerns implements Listener {
    CobraFeats plugin;
    FeatsUtils utils;
    
    public BoneMealFerns(CobraFeats plugin) {
        this.plugin = plugin;
    }
    
    public void registerEvents() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if(plugin.config.BONE_MEAL_FERNS) {
            if(event.getClickedBlock().getType().equals(Material.GRASS) && event.hasItem() &&
                event.getItem().getType().equals(Material.INK_SACK) && event.getItem().getDurability() == (short)15) {
                
            }
        }
    }
}
