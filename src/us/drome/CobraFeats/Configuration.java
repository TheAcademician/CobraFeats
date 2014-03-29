package us.drome.CobraFeats;

public class Configuration {
    private final CobraFeats plugin;
    
    public boolean SILK_TOUCH_SPAWNERS;
    
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
    }
    
    public void reload() {
        load();
    }
}
