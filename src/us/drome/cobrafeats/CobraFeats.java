package us.drome.cobrafeats;

import java.io.File;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class CobraFeats extends JavaPlugin {
    public final Configuration config = new Configuration(this);
    public final CraftRecipes recipes = new CraftRecipes(this);

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
        
        recipes.register();
        
        getLogger().info("version " + getDescription().getVersion() + " has finished loading.");
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if(cmd.getName().equalsIgnoreCase("cfeats")) {
            if(args.length == 0) {
                sender.sendMessage(config.printCurrentSettings());
            } else {
                if(args[0].equalsIgnoreCase("reload")) {
                    config.reload();
                    recipes.register();
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "[CobraFeats] Config Reloaded");
                } else
                    return false;
            }
        }
        return true;
    }
}
