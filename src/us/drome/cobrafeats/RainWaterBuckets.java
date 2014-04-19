package us.drome.cobrafeats;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RainWaterBuckets implements Listener {
    private CobraFeats plugin;
    private FeatsUtils utils;
    
    public RainWaterBuckets(CobraFeats plugin) {
        this.plugin = plugin;
    }
    
    public void registerEvents() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(!event.hasItem())
            return;
        
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        
        if(plugin.config.RAIN_WATER_BUCKETS && event.hasItem()) {
            if(item.getType().equals(Material.BUCKET) && item.getAmount() == 1) {
                if(event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
                    if(utils.isRaining(player.getLocation()) && utils.isOutside(player.getEyeLocation())) {
                        ItemMeta itemMeta = item.getItemMeta();
                        List<String> bucketLore = itemMeta.getLore();
                        String fillLore = "";
                        if(bucketLore == null) {
                            bucketLore = new ArrayList<String>() {{ add("Fill Level: 1"); }};
                            itemMeta.setLore(bucketLore);
                            item.setItemMeta(itemMeta);
                        } else {
                            for(String lore : bucketLore) {
                                if(lore.contains("Fill Level:")) {
                                fillLore = lore;
                                }
                            }
                            
                            if(!fillLore.isEmpty()) {
                                int fillLevel = Integer.parseInt(fillLore.split(":")[1].trim());
                                if(fillLevel == 99) {
                                    item.setType(Material.WATER_BUCKET);
                                    bucketLore.remove(fillLore);
                                    itemMeta.setLore(bucketLore);
                                    item.setItemMeta(itemMeta);
                                } else {
                                    bucketLore.set(bucketLore.indexOf(fillLore), "Fill Level: " + (fillLevel + 1));
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
                }
            }
        }
    }
}
