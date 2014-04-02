package us.drome.cobrafeats;

import java.io.File;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class CobraFeats extends JavaPlugin {
    public final Configuration config = new Configuration(this);
    private FeatsListener listener = new FeatsListener(this);
    
    public void onDisable() {
        getLogger().info("version " + getDescription().getVersion() + " has begun unloading...");
        config.save();
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
        getLogger().info("version " + getDescription().getVersion() + " has finished loading.");
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if(cmd.getName().equalsIgnoreCase("cfeats")) {
            if(args.length == 0) {
                sender.sendMessage(config.printCurrentSettings());
            } else {
                if(args[0].equalsIgnoreCase("reload")) {
                    config.reload();
                } else
                    return false;
            }
        }
        return true;
    }
}
