package us.drome.cobrafeats;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FeatsListener implements Listener {
    private final CobraFeats plugin;
    
    FeatsListener(CobraFeats instance) {
        plugin = instance;
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        ItemStack tool = event.getPlayer().getItemInHand();
            
        if(plugin.config.SILK_TOUCH_SPAWNERS) {
            if(block.getType().equals(Material.MOB_SPAWNER) && plugin.isPickaxe(tool) && plugin.isSilkTouch(tool)) {
                ItemStack spawner = new ItemStack(Material.MOB_SPAWNER);
                ItemMeta spawnerMeta = spawner.getItemMeta();
                final String spawnerType = ((CreatureSpawner)block.getState()).getCreatureTypeName();
                spawnerMeta.setLore(new ArrayList<String>(){{ add(spawnerType); add("Place in well lit area!"); }});
                spawnerMeta.setDisplayName(spawnerType + " Spawner");
                spawner.setItemMeta(spawnerMeta);
                
                block.getWorld().dropItem(block.getLocation(), spawner);
            }
        }
        
        if(plugin.config.AESTHETIC_COMMAND_BLOCKS) {
            if(block.getType().equals(Material.COMMAND) && event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
                if(block.breakNaturally()) {
                    block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.COMMAND));
                }
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
    
    @EventHandler
    public void onBlockDamage(BlockDamageEvent event) {
        Block block = event.getBlock();
        
        if(plugin.config.AESTHETIC_COMMAND_BLOCKS) {
            if(block.getType().equals(Material.COMMAND) && event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
                plugin.getServer().getPluginManager().callEvent(new BlockBreakEvent(block, event.getPlayer()));
            }
        }
    }
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        ItemStack item = event.getItem();
        ItemMeta itemMeta = item.getItemMeta();
        Player player = event.getPlayer();
        
        if(plugin.config.RAIN_WATER_BUCKETS) {
            if(item.getType().equals(Material.BUCKET)) {
                if(event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
                    if(plugin.isRaining(player.getLocation()) && plugin.isOutside(player.getEyeLocation())) {
                        List<String> bucketLore = itemMeta.getLore();
                        String fillLore = "";
                        if(bucketLore == null) {
                            bucketLore.add("Fill Level: 1");
                            itemMeta.setLore(bucketLore);
                            item.setItemMeta(itemMeta);
                        } else {
                            for(String lore : bucketLore) {
                                if(lore.contains("Fill Level:")) {
                                fillLore = lore;
                                }
                            }
                            
                            if(!fillLore.isEmpty()) {
                                int fillLevel = Integer.parseInt(fillLore.split(":")[1]);
                                if(fillLevel == 100) {
                                    item.setType(Material.WATER_BUCKET);
                                } else {
                                    bucketLore.set(bucketLore.indexOf(fillLore), "Fill Level: " + fillLevel++);
                                    itemMeta.setLore(bucketLore);
                                    item.setItemMeta(itemMeta);
                                }
                            } else {
                                bucketLore.add("Fill Level: 1");
                                itemMeta.setLore(bucketLore);
                                item.setItemMeta(itemMeta);
                            }
                        }
                    }
                } else if(block.getType().equals(Material.WATER) || block.getType().equals(Material.LAVA)) {
                    itemMeta.setLore(null);
                }
            }
        }
    }
}
