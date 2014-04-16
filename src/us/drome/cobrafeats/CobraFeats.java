package us.drome.cobrafeats;

import java.io.File;
import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class CobraFeats extends JavaPlugin {
    public final Configuration config = new Configuration(this);
    private FeatsListener listener = new FeatsListener(this);
    
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
        getServer().getPluginManager().registerEvents(listener, this);
        
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
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "[CobraFeats] Config Reloaded");
                } else
                    return false;
            }
        }
        return true;
    }
    
    public void registerRecipes() {
        if(config.AESTHETIC_COMMAND_BLOCKS) {
            ShapedRecipe commandBlock = new ShapedRecipe(new ItemStack(Material.COMMAND))
                .shape("aba", "bcb", "aba")
                .setIngredient('a', Material.HARD_CLAY)
                .setIngredient('b', Material.REDSTONE)
                .setIngredient('c', Material.IRON_INGOT);
            getServer().addRecipe(commandBlock);
        }
    }
    
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
        if(location.getWorld().hasStorm()) {
            if(location.getWorld().getTemperature(location.getBlockX(), location.getBlockZ()) > .15) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public boolean isOutside(Location location) {
        if(location.getWorld().getHighestBlockYAt(location) < location.getBlockY()) {
            return true;
        } else {
            return false;
        }
    }
}
