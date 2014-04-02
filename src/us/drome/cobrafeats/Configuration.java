package us.drome.cobrafeats;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;

public class Configuration {
    private final CobraFeats plugin;
    
    public boolean SILK_TOUCH_SPAWNERS;
    public boolean AESTHETIC_COMMAND_BLOCKS;
    
    public Configuration(CobraFeats instance) {
        plugin = instance;
    }
    
    public void save() {
        reload();
        
        plugin.saveConfig();
    }
    
    public void load() {
        plugin.reloadConfig();
        
        SILK_TOUCH_SPAWNERS = plugin.getConfig().getBoolean("silk-touch-spawners", false);
        AESTHETIC_COMMAND_BLOCKS = plugin.getConfig().getBoolean("aesthetic-command-blocks", false);
    }
    
    public void reload() {
        load();
    }
    
    public String[] printCurrentSettings() {
        List<String> settings = new ArrayList<>();
        settings.add(ChatColor.RED + "-=/" + ChatColor.RESET + "Current Cobra Feature Status" + ChatColor.RED + "\\=-");
        
        settings.add(ChatColor.GOLD + "Silk Touch Spawners: " + (SILK_TOUCH_SPAWNERS ? (ChatColor.GREEN + "Enabled") : (ChatColor.GRAY + "Disabled")));
        settings.add(ChatColor.GOLD + "Aesthetic Command Blocks: " + (AESTHETIC_COMMAND_BLOCKS ? (ChatColor.GREEN + "Enabled") : (ChatColor.GRAY + "Disabled")));
        
        return settings.toArray(new String[settings.size()]);
    }
}
