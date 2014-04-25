package us.drome.cobrafeats;

import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class CraftRecipes {
    public CobraFeats plugin;
    
    public CraftRecipes(CobraFeats plugin) {
        this.plugin = plugin;
    }
    
    public void register() {
        plugin.getServer().resetRecipes();
        
        if(plugin.config.AESTHETIC_COMMAND_BLOCKS) 
            plugin.getServer().addRecipe(commandBlock());

        if(plugin.config.PERMA_DIRT_RECIPE) 
            plugin.getServer().addRecipe(permaDirt());

        if(plugin.config.PODZOL_RECIPE) {
            plugin.getServer().addRecipe(podzol());
            plugin.getServer().addRecipe(podzol2());
        }
        
        if(plugin.config.MYCELIUM_RECIPE) 
            plugin.getServer().addRecipe(mycelium());

        if(plugin.config.END_PORTAL_FRAME_RECIPE)
            plugin.getServer().addRecipe(endPortalFrame());
        
        if(plugin.config.CRACKED_STONE_BRICK_RECIPE)
            plugin.getServer().addRecipe(crackedStoneBrick());
        
        if(plugin.config.NAME_TAG_RECIPE)
            plugin.getServer().addRecipe(nameTag());
        
        if(plugin.config.SADDLE_RECIPE)
            plugin.getServer().addRecipe(saddle());
        
        if(plugin.config.HORSE_ARMOR_RECIPE) {
            plugin.getServer().addRecipe(horseArmorIron());
            plugin.getServer().addRecipe(horseArmorGold());
            plugin.getServer().addRecipe(horseArmorDiamond());
        }
    }
    
    ShapedRecipe commandBlock() {
        ShapedRecipe commandBlock = new ShapedRecipe(new ItemStack(Material.COMMAND))
            .shape("aba", "bcb", "aba")
            .setIngredient('a', Material.HARD_CLAY)
            .setIngredient('b', Material.REDSTONE)
            .setIngredient('c', Material.IRON_INGOT);
        
        return commandBlock;
    }
    
    ShapedRecipe permaDirt() {
        ItemStack permaDirtItem = new ItemStack(Material.DIRT, 3);
        ItemMeta permaDirtMeta = permaDirtItem.getItemMeta();
        permaDirtMeta.setDisplayName("Permanent Dirt");
        permaDirtMeta.setLore(new ArrayList<String>() {{ add("Prevents grass, weeds, and mushrooms!"); }});
        permaDirtItem.setItemMeta(permaDirtMeta);
        permaDirtItem.setDurability((short)1);
        ShapedRecipe permaDirt = new ShapedRecipe(permaDirtItem)
            .shape("aaa")
            .setIngredient('a', Material.DIRT);
        
        return permaDirt;
    }
    
    ShapelessRecipe podzol() {
        ItemStack podzolItem = new ItemStack(Material.DIRT);
        podzolItem.setDurability((short)2);
        ShapelessRecipe podzol = new ShapelessRecipe(podzolItem)
            .addIngredient(Material.DIRT)
            .addIngredient(Material.LEAVES, -1);
        
        return podzol;
    }
    
    ShapelessRecipe podzol2() {
        ItemStack podzolItem = new ItemStack(Material.DIRT);
        podzolItem.setDurability((short)2);
        ShapelessRecipe podzol = new ShapelessRecipe(podzolItem)
            .addIngredient(Material.DIRT)
            .addIngredient(Material.LEAVES_2, -1);
        
        return podzol;
    }
    
    ShapelessRecipe mycelium() {
        ShapelessRecipe mycelium = new ShapelessRecipe(new ItemStack(Material.MYCEL))
        .addIngredient(Material.DIRT)
        .addIngredient(Material.MUSHROOM_SOUP);
        
        return mycelium;
    }
    
    ShapedRecipe endPortalFrame() {
        ShapedRecipe endPortalFrame = new ShapedRecipe(new ItemStack(Material.ENDER_PORTAL_FRAME))
            .shape("bdb", "aca", "aaa")
            .setIngredient('a', Material.ENDER_STONE)
            .setIngredient('b', Material.STAINED_CLAY, 13)
            .setIngredient('c', Material.OBSIDIAN)
            .setIngredient('d', Material.AIR);
        return endPortalFrame;
    }
    
    ShapedRecipe crackedStoneBrick() {
        ShapedRecipe crackedStoneBrick = new ShapedRecipe(new ItemStack(Material.SMOOTH_BRICK, 8, (short)2))
            .shape("aaa", "aba", "aaa")
            .setIngredient('a', Material.SMOOTH_BRICK)
            .setIngredient('b', Material.SULPHUR);
        
        return crackedStoneBrick;
    }
    
    ShapedRecipe nameTag() {
        ShapedRecipe nameTag = new ShapedRecipe(new ItemStack(Material.NAME_TAG))
            .shape("abb")
            .setIngredient('a', Material.IRON_INGOT)
            .setIngredient('b', Material.PAPER);
        
        return nameTag;
    }
    
    ShapedRecipe saddle() {
        ShapedRecipe saddle = new ShapedRecipe(new ItemStack(Material.SADDLE))
            .shape("aba", "aaa", "bcb")
            .setIngredient('a', Material.LEATHER)
            .setIngredient('b', Material.AIR)
            .setIngredient('c', Material.IRON_INGOT);
        
        return saddle;
    }
    
    ShapedRecipe horseArmorIron() {
        ShapedRecipe horseArmorIron = new ShapedRecipe(new ItemStack(Material.IRON_BARDING))
            .shape("bba", "aaa", "aba")
            .setIngredient('a', Material.IRON_INGOT)
            .setIngredient('b', Material.AIR);
        
        return horseArmorIron;
    }
    
    ShapedRecipe horseArmorGold() {
        ShapedRecipe horseArmorGold = new ShapedRecipe(new ItemStack(Material.GOLD_BARDING))
            .shape("bba", "aaa", "aba")
            .setIngredient('a', Material.GOLD_INGOT)
            .setIngredient('b', Material.AIR);
        
        return horseArmorGold;
    }
    
    ShapedRecipe horseArmorDiamond() {
        ShapedRecipe horseArmorDiamond = new ShapedRecipe(new ItemStack(Material.DIAMOND_BARDING))
            .shape("bba", "aaa", "aba")
            .setIngredient('a', Material.DIAMOND)
            .setIngredient('b', Material.AIR);
        
        return horseArmorDiamond;
    }
}
