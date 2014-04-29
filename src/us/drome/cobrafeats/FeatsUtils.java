package us.drome.cobrafeats;

import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class FeatsUtils {
    enum tools { IRON_SPADE, IRON_PICKAXE, IRON_AXE, IRON_HOE,
        WOOD_SPADE, WOOD_PICKAXE, WOOD_AXE, WOOD_HOE,
        STONE_SPADE, STONE_PICKAXE, STONE_AXE, STONE_HOE,
        DIAMOND_SPADE, DIAMOND_PICKAXE, DIAMOND_AXE, DIAMOND_HOE,
        GOLD_SPACE, GOLD_PICKAXE, GOLD_AXE, GOLD_HOE, SHEARS };
    
    enum spades { IRON_SPADE, WOOD_SPADE, STONE_SPADE, DIAMOND_SPADE, GOLD_SPADE };
    
    enum pickaxes { IRON_PICKAXE, WOOD_PICKAXE, STONE_PICKAXE, DIAMOND_PICKAXE, GOLD_PICKAXE };
    
    enum axes { IRON_AXE, WOOD_AXE, STONE_AXE, DIAMOND_AXE, GOLD_AXE };
    
    enum hoes { IRON_HOE, WOOD_HOE, STONE_HOE, DIAMOND_HOE, GOLD_HOE };
    
    public static boolean isPickaxe(ItemStack tool) {
        for(pickaxes pickType : pickaxes.values()) {
            if(pickType.name().equals(tool.getType().name())) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isTool(ItemStack tool) {
        for(tools toolType : tools.values()) {
            if(toolType.name().equals(tool.getType().name())) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isSilkTouch(ItemStack tool) {
        if(isTool(tool)) {
            return (tool.containsEnchantment(Enchantment.SILK_TOUCH) ? true : false);
        } else {
            return false;
        }
    }
    
    public static boolean isRaining(Location location) {
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
    
    public static boolean isSnowing(Location location) {
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
    
    public static boolean isOutside(Location location) {
        if(location.getWorld().getHighestBlockYAt(location) < location.getBlockY()) {
            return true;
        } else {
            return false;
        }
    }
}
