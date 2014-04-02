package us.drome.cobrafeats;

import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FeatsListener implements Listener {
    private final CobraFeats plugin;
    
    FeatsListener(CobraFeats instance) {
        plugin = instance;
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(plugin.config.SILK_TOUCH_SPAWNERS) {
            Block block = event.getBlock();
            ItemStack tool = event.getPlayer().getItemInHand();
            if(block.getType().equals(Material.MOB_SPAWNER) && isPickaxe(tool) && isSilkTouch(tool)) {
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
        if(plugin.config.SILK_TOUCH_SPAWNERS) {
            Block block = event.getBlock();
            ItemStack item = event.getItemInHand();
            if(block.getType().equals(Material.MOB_SPAWNER)) {
                if(!item.getItemMeta().getLore().isEmpty()) {
                    CreatureSpawner spawnerState = ((CreatureSpawner)block.getState());
                    spawnerState.setCreatureTypeByName(item.getItemMeta().getLore().get(0));
                    spawnerState.update();
                }
            }
        }
    }
    
    private boolean isPickaxe(ItemStack tool) {
        Material toolType = tool.getType();
        if(toolType.equals(Material.WOOD_PICKAXE) || toolType.equals(Material.STONE_PICKAXE) || toolType.equals(Material.IRON_PICKAXE) ||
                toolType.equals(Material.GOLD_PICKAXE) || toolType.equals(Material.DIAMOND_PICKAXE)) {
            return true;
        } else {
            return false;
        }
    }
    
    private boolean isSilkTouch(ItemStack tool) {
        return (tool.getEnchantments().containsKey(Enchantment.SILK_TOUCH) ? true : false);
    }
}
