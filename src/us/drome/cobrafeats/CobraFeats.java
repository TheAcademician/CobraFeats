package us.drome.cobrafeats;

import java.io.File;
import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;

public class CobraFeats extends JavaPlugin {
    public final Configuration config = new Configuration(this);

    public void onDisable() {
        getLogger().info("version " + getDescription().getVersion() + " has begun unloading...");
        config.save();
        getServer().clearRecipes();
        getLogger().info("version " + getDescription().getVersion() + " has finished unloading.");
    }
    
    public void onEnable() {
        getLogger().info("version " + getDescription().getVersion() + " has begun loading...");
        File configFile = new File(getDataFolder(), "config.yml");
        if(!configFile.exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
        config.load();
        
        (new SilkTouchSpawners(this)).registerEvents();
        (new AestheticCommandBlocks(this)).registerEvents();
        (new RainWaterBuckets(this)).registerEvents();
        
        registerRecipes();
        
        getLogger().info("version " + getDescription().getVersion() + " has finished loading.");
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if(cmd.getName().equalsIgnoreCase("cfeats")) {
            if(args.length == 0) {
                sender.sendMessage(config.printCurrentSettings());
            } else {
                if(args[0].equalsIgnoreCase("reload")) {
                    config.reload();
                    registerRecipes();
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "[CobraFeats] Config Reloaded");
                } else
                    return false;
            }
        }
        return true;
    }
    
    public void registerRecipes() {
        getServer().resetRecipes();
        
        if(config.AESTHETIC_COMMAND_BLOCKS) {
            ShapedRecipe commandBlock = new ShapedRecipe(new ItemStack(Material.COMMAND))
                .shape("aba", "bcb", "aba")
                .setIngredient('a', Material.HARD_CLAY)
                .setIngredient('b', Material.REDSTONE)
                .setIngredient('c', Material.IRON_INGOT);
            getServer().addRecipe(commandBlock);
        }
        
        if(config.PERMA_DIRT_RECIPE) {
            ItemStack permaDirtItem = new ItemStack(Material.DIRT, 3);
            ItemMeta permaDirtMeta = permaDirtItem.getItemMeta();
            permaDirtMeta.setDisplayName("Permanent Dirt");
            permaDirtMeta.setLore(new ArrayList<String>() {{ add("Prevents grass, weeds, and mushrooms!"); }});
            permaDirtItem.setItemMeta(permaDirtMeta);
            permaDirtItem.setDurability((short)1);
            ShapedRecipe permaDirt = new ShapedRecipe(permaDirtItem)
                    .shape("aaa")
                    .setIngredient('a', Material.DIRT);
            getServer().addRecipe(permaDirt);
        }
        
        if(config.PODZOL_RECIPE) {
            ItemStack podzolItem = new ItemStack(Material.DIRT);
            podzolItem.setDurability((short)2);
            ShapelessRecipe podzol = new ShapelessRecipe(podzolItem)
                    .addIngredient(Material.DIRT)
                    .addIngredient(Material.LEAVES);
            getServer().addRecipe(podzol);
        }
        
        if(config.MYCELIUM_RECIPE) {
            ShapelessRecipe mycelium = new ShapelessRecipe(new ItemStack(Material.MYCEL))
                    .addIngredient(Material.DIRT)
                    .addIngredient(Material.MUSHROOM_SOUP);
            getServer().addRecipe(mycelium);
        }
    }
}
