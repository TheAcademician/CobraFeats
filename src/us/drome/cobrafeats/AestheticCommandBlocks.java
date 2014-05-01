package us.drome.cobrafeats;

import java.util.ArrayList;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.ItemStack;

public class AestheticCommandBlocks implements Listener{
    CobraFeats plugin;
    
    ArrayList<Location> locations = new ArrayList<>();
    
    public AestheticCommandBlocks(CobraFeats plugin) {
        this.plugin = plugin;
    }
    
    public void registerEvents() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    @EventHandler
    public void onBlockDamage(BlockDamageEvent event) {
        Block block = event.getBlock();
        
        if(plugin.config.AESTHETIC_COMMAND_BLOCKS) {
            if(block.getType().equals(Material.COMMAND) && event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
                plugin.getServer().getPluginManager().callEvent(new BlockBreakEvent(block, event.getPlayer()));
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event) {
        if(event.isCancelled()) {
            return;
        }
        
        Block block = event.getBlock();
        
        if(plugin.config.AESTHETIC_COMMAND_BLOCKS) {
            if(block.getType().equals(Material.COMMAND) && event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
                if(locations.contains(block.getLocation())) {
                    locations.remove(block.getLocation());
                } else {
                    locations.add(block.getLocation());
                    plugin.getServer().getPluginManager().callEvent(new BlockBreakEvent(block, event.getPlayer()));
                    block.breakNaturally();
                    block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.COMMAND));
                }
            }
        }
    }
}
