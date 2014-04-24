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
        
        if(plugin.config.AESTHETIC_COMMAND_BLOCKS) {
            plugin.getServer().addRecipe(commandBlock());
        }
        
        if(plugin.config.PERMA_DIRT_RECIPE) {
            plugin.getServer().addRecipe(permaDirt());
        }
        
        if(plugin.config.PODZOL_RECIPE) {
            plugin.getServer().addRecipe(podzol());
            plugin.getServer().addRecipe(podzol2());
        }
        
        if(plugin.config.MYCELIUM_RECIPE) {
            plugin.getServer().addRecipe(mycelium());
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
            .shape("aba", "bcb", "aba")
            .setIngredient('a', Material.ENDER_STONE)
            .setIngredient('b', Material.EYE_OF_ENDER)
            .setIngredient('c', Material.ENDER_PEARL);
        return endPortalFrame;
    }
    
}
