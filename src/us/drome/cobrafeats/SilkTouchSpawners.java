package us.drome.cobrafeats;

import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SilkTouchSpawners implements Listener {
    CobraFeats plugin;
    FeatsUtils utils;
    
    public SilkTouchSpawners(CobraFeats plugin) {
        this.plugin = plugin;
    }
    
    public void registerEvents() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    public void onBlockBreak(BlockBreakEvent event) {
        if(event.isCancelled()) {
            return;
        }
        Block block = event.getBlock();
        ItemStack tool = event.getPlayer().getItemInHand();
            
        if(plugin.config.SILK_TOUCH_SPAWNERS) {
            if(block.getType().equals(Material.MOB_SPAWNER) && utils.isPickaxe(tool) && utils.isSilkTouch(tool)) {
                ItemStack spawner = new ItemStack(Material.MOB_SPAWNER);
                ItemMeta spawnerMeta = spawner.getItemMeta();
                final String spawnerType = ((CreatureSpawner)block.getState()).getCreatureTypeName();
                spawnerMeta.setLore(new ArrayList<String>(){{ add(spawnerType); add("Place in well lit area!"); }});
                spawnerMeta.setDisplayName(spawnerType + " Spawner");
                spawner.setItemMeta(spawnerMeta);
                
                block.getWorld().dropItem(block.getLocation(), spawner);
            }
        }
    }
    
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        ItemStack item = event.getItemInHand();
            
        if(plugin.config.SILK_TOUCH_SPAWNERS) {
            if(block.getType().equals(Material.MOB_SPAWNER)) {
                if(!item.getItemMeta().getLore().isEmpty()) {
                    CreatureSpawner spawnerState = ((CreatureSpawner)block.getState());
                    spawnerState.setCreatureTypeByName(item.getItemMeta().getLore().get(0));
                    spawnerState.update();
                }
            }
        }
    }
}
