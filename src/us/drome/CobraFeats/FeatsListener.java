package us.drome.CobraFeats;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

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
            if(block.getType().equals(Material.MOB_SPAWNER) && isPickaxe(tool) && tool.getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
                ItemStack spawner = new ItemStack(Material.MOB_SPAWNER);
                Short spawnerType = ((CreatureSpawner)block.getState()).getSpawnedType().getTypeId();
                //Since type ID is depreceated. Attempt to set spawner mob class to the Lore in ItemMeta.
                //When spawner is placed, detect Lore values and set spawner type to correct mob type.
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
}
