package us.drome.cobrafeats;

import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
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
        
        if(plugin.config.SELF_POWERED_LAMPS)
            plugin.getServer().addRecipe(selfPoweredLamp());

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
        
        if(plugin.config.DOUBLE_STONE_SLAB_RECIPE)
            plugin.getServer().addRecipe(doubleStoneSlab());
        
        if(plugin.config.FULL_STONE_SLAB_RECIPE)
            plugin.getServer().addRecipe(fullStoneSlab());
        
        if(plugin.config.DEAD_BUSH_RECIPE)
            plugin.getServer().addRecipe(deadBush());
        
        if(plugin.config.COBWEB_RECIPE)
            plugin.getServer().addRecipe(cobweb());
        
        if(plugin.config.BLEACH_WOOL_RECIPE)
            plugin.getServer().addRecipe(bleachWool());
        
        if(plugin.config.BLEACH_CLAY_RECIPE)
            plugin.getServer().addRecipe(bleachClay());
        
        if(plugin.config.BLEACH_GLASS_RECIPE)
            plugin.getServer().addRecipe(bleachGlass());
        
    }
    
    ShapedRecipe commandBlock() {
        return new ShapedRecipe(new ItemStack(Material.COMMAND))
            .shape("aba", "bcb", "aba")
            .setIngredient('a', Material.HARD_CLAY)
            .setIngredient('b', Material.REDSTONE)
            .setIngredient('c', Material.IRON_INGOT);
    }
    
    ShapedRecipe selfPoweredLamp() {
        ItemStack poweredLamp = new ItemStack(Material.REDSTONE_LAMP_OFF, 8);
        ItemMeta poweredLampMeta = poweredLamp.getItemMeta();
        poweredLampMeta.setDisplayName("Powered Lamp");
        poweredLampMeta.setLore(new ArrayList<String>() {{ add("Rumored to be powered by Element 115!"); }});
        poweredLamp.setItemMeta(poweredLampMeta);
        return new ShapedRecipe(poweredLamp)
            .shape("aaa", "aba", "aaa")
            .setIngredient('a', Material.REDSTONE_LAMP_OFF)
            .setIngredient('b', Material.REDSTONE_BLOCK);
    }
    
    ShapedRecipe permaDirt() {
        ItemStack permaDirtItem = new ItemStack(Material.DIRT, 3);
        ItemMeta permaDirtMeta = permaDirtItem.getItemMeta();
        permaDirtMeta.setDisplayName("Permanent Dirt");
        permaDirtMeta.setLore(new ArrayList<String>() {{ add("Prevents grass, weeds, and mushrooms!"); }});
        permaDirtItem.setItemMeta(permaDirtMeta);
        permaDirtItem.setDurability((short)1);
        return new ShapedRecipe(permaDirtItem)
            .shape("aaa")
            .setIngredient('a', Material.DIRT);
    }
    
    ShapelessRecipe podzol() {
        ItemStack podzolItem = new ItemStack(Material.DIRT);
        podzolItem.setDurability((short)2);
        return new ShapelessRecipe(podzolItem)
            .addIngredient(Material.DIRT)
            .addIngredient(Material.LEAVES, -1);
    }
    
    ShapelessRecipe podzol2() {
        ItemStack podzolItem = new ItemStack(Material.DIRT);
        podzolItem.setDurability((short)2);
        return new ShapelessRecipe(podzolItem)
            .addIngredient(Material.DIRT)
            .addIngredient(Material.LEAVES_2, -1);
    }
    
    ShapelessRecipe mycelium() {
        return new ShapelessRecipe(new ItemStack(Material.MYCEL))
        .addIngredient(Material.DIRT)
        .addIngredient(Material.MUSHROOM_SOUP);
    }
    
    ShapedRecipe endPortalFrame() {
        return new ShapedRecipe(new ItemStack(Material.ENDER_PORTAL_FRAME))
            .shape("b b", "aca", "aaa")
            .setIngredient('a', Material.ENDER_STONE)
            .setIngredient('b', Material.STAINED_CLAY, 13)
            .setIngredient('c', Material.OBSIDIAN);
    }
    
    ShapedRecipe crackedStoneBrick() {
        return new ShapedRecipe(new ItemStack(Material.SMOOTH_BRICK, 8, (short)2))
            .shape("aaa", "aba", "aaa")
            .setIngredient('a', Material.SMOOTH_BRICK)
            .setIngredient('b', Material.SULPHUR);
    }
    
    ShapedRecipe nameTag() {
        return new ShapedRecipe(new ItemStack(Material.NAME_TAG))
            .shape("abb")
            .setIngredient('a', Material.IRON_INGOT)
            .setIngredient('b', Material.PAPER);
    }
    
    ShapedRecipe saddle() {
        return new ShapedRecipe(new ItemStack(Material.SADDLE))
            .shape("a a", "aaa", " b ")
            .setIngredient('a', Material.LEATHER)
            .setIngredient('b', Material.IRON_INGOT);
    }
    
    ShapedRecipe horseArmorIron() {
        return new ShapedRecipe(new ItemStack(Material.IRON_BARDING))
            .shape("  a", "aba")
            .setIngredient('a', Material.IRON_BLOCK)
            .setIngredient('b', Material.LEATHER);
    }
    
    ShapedRecipe horseArmorGold() {
        return new ShapedRecipe(new ItemStack(Material.GOLD_BARDING))
            .shape("  a", "aba")
            .setIngredient('a', Material.GOLD_BLOCK)
            .setIngredient('b', Material.LEATHER);
    }
    
    ShapedRecipe horseArmorDiamond() {
        return new ShapedRecipe(new ItemStack(Material.DIAMOND_BARDING))
            .shape("  a", "aba")
            .setIngredient('a', Material.DIAMOND_BLOCK)
            .setIngredient('b', Material.LEATHER);
    }
    
    ShapedRecipe doubleStoneSlab() {
        return new ShapedRecipe(new ItemStack(Material.DOUBLE_STEP))
            .shape("a", "a")
            .setIngredient('a', Material.STEP);
    }
    
    ShapedRecipe fullStoneSlab() {
        return new ShapedRecipe(new ItemStack(Material.DOUBLE_STEP, 2, (short)8))
            .shape("aa", "aa")
            .setIngredient('a', Material.STEP);
    }
    
    ShapelessRecipe deadBush() {
        return new ShapelessRecipe(new ItemStack(Material.DEAD_BUSH))
            .addIngredient(Material.SAPLING)
            .addIngredient(Material.BLAZE_POWDER);
    }
    
    ShapedRecipe cobweb() {
        return new ShapedRecipe(new ItemStack(Material.WEB))
            .shape("aaa", "aba", "aaa")
            .setIngredient('a', Material.STRING)
            .setIngredient('b', Material.SLIME_BALL);
    }
    
    ShapelessRecipe bleachWool() {
        return new ShapelessRecipe(new ItemStack(Material.WOOL))
            .addIngredient(Material.WOOL, -1)
            .addIngredient(Material.INK_SACK, 15);
    }
    
    FurnaceRecipe bleachClay() {
        return new FurnaceRecipe(new ItemStack(Material.HARD_CLAY), Material.STAINED_CLAY, -1);
    }

    FurnaceRecipe bleachGlass() {
        return new FurnaceRecipe(new ItemStack(Material.GLASS), Material.STAINED_GLASS, -1);
    }
}
