package us.drome.cobrafeats;

import java.util.ArrayList;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
        Player player = event.getPlayer();
        
        if(plugin.config.RAIN_WATER_BUCKETS) {
            if(item.getType().equals(Material.BUCKET)) {
                if(block.getType().equals(Material.AIR) && player.getWorld().isThundering()) {
                    //if used on AIR and it is raining...
                } else if(block.getType().equals(Material.WATER)) {
                    
                }
            }
            
            if(block.getType().equals(Material.AIR) && item.getType().equals(Material.BUCKET)) {
                ItemMeta bucketMeta = item.getItemMeta();
                if(bucketMeta.hasLore()) {
                    
                } else {
                    bucketMeta.setLore(new ArrayList<String>(){{ add("Fill: 1/5");}});
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
