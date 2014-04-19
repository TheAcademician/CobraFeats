package us.drome.cobrafeats;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class FeatsUtils {
        public boolean isPickaxe(ItemStack tool) {
        Material toolType = tool.getType();
        if(toolType.equals(Material.WOOD_PICKAXE) || toolType.equals(Material.STONE_PICKAXE) || toolType.equals(Material.IRON_PICKAXE) ||
                toolType.equals(Material.GOLD_PICKAXE) || toolType.equals(Material.DIAMOND_PICKAXE)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isSilkTouch(ItemStack tool) {
        return (tool.getEnchantments().containsKey(Enchantment.SILK_TOUCH) ? true : false);
    }
    
    public boolean isRaining(Location location) {
        double blockTemp = location.getBlock().getTemperature();
        
        if(location.getWorld().hasStorm()) {
            if(blockTemp > 0.15 && blockTemp < 1.0) {
                if(blockTemp < .5 && location.getBlockY() <= 95) {
                    return true;
                } else if(blockTemp > .5) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean isSnowing(Location location) {
        double blockTemp = location.getBlock().getTemperature();
        
        if(location.getWorld().hasStorm()) {
            if(blockTemp < 1.0) { 
                if(blockTemp <= .15) {
                    return true;
                } else if(blockTemp > .15 && location.getBlockY() >= 95) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean isOutside(Location location) {
        if(location.getWorld().getHighestBlockYAt(location) < location.getBlockY()) {
            return true;
        } else {
            return false;
        }
    }
}
