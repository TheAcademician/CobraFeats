package us.drome.cobrafeats;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;

public class Configuration {
    private final CobraFeats plugin;
    
    public boolean SILK_TOUCH_SPAWNERS;
    public boolean AESTHETIC_COMMAND_BLOCKS;
    public boolean RAIN_WATER_BUCKETS;
    public boolean SELF_POWERED_LAMPS;
    public boolean BONE_MEAL_FERNS;
    public boolean INFINITE_LAVA_SOURCES;
    public boolean AMBIENT_HOPPER_BRAZIERS;
    
    public boolean PERMA_DIRT_RECIPE;
    public boolean PODZOL_RECIPE;
    public boolean MYCELIUM_RECIPE;
    public boolean END_PORTAL_FRAME_RECIPE;
    public boolean CRACKED_STONE_BRICK_RECIPE;
    public boolean NAME_TAG_RECIPE;
    public boolean SADDLE_RECIPE;
    public boolean HORSE_ARMOR_RECIPE;
    public boolean DOUBLE_STONE_SLAB_RECIPE;
    public boolean FULL_STONE_SLAB_RECIPE;
    public boolean DEAD_BUSH_RECIPE;
    public boolean COBWEB_RECIPE;
    public boolean BLEACH_WOOL_RECIPE;
    public boolean BLEACH_CLAY_RECIPE;
    public boolean BLEACH_GLASS_RECIPE;
    
    public Configuration(CobraFeats instance) {
        plugin = instance;
    }
    
    public void save() {
        reload();
        
        plugin.saveConfig();
    }
    
    public void load() {
        plugin.reloadConfig();
        
        //Features
        SILK_TOUCH_SPAWNERS = plugin.getConfig().getBoolean("silk-touch-spawners", false);
        AESTHETIC_COMMAND_BLOCKS = plugin.getConfig().getBoolean("aesthetic-command-blocks", false);
        RAIN_WATER_BUCKETS = plugin.getConfig().getBoolean("rain-water-buckets", false);
        SELF_POWERED_LAMPS = plugin.getConfig().getBoolean("self-powered-lamps", false);
        BONE_MEAL_FERNS = plugin.getConfig().getBoolean("bone-meal-ferns", false);
        INFINITE_LAVA_SOURCES = plugin.getConfig().getBoolean("infinite-lava-sources", false);
        AMBIENT_HOPPER_BRAZIERS = plugin.getConfig().getBoolean("ambient-hopper-braziers", false);
        
        //Recipes
        PERMA_DIRT_RECIPE = plugin.getConfig().getBoolean("recipes.perma-dirt", false);
        PODZOL_RECIPE = plugin.getConfig().getBoolean("recipes.podzol", false);
        MYCELIUM_RECIPE = plugin.getConfig().getBoolean("recipes.mycelium", false);
        END_PORTAL_FRAME_RECIPE = plugin.getConfig().getBoolean("recipes.end-portal-frame", false);
        CRACKED_STONE_BRICK_RECIPE = plugin.getConfig().getBoolean("recipes.cracked-stone-brick", false);
        NAME_TAG_RECIPE = plugin.getConfig().getBoolean("recipes.name-tag", false);
        SADDLE_RECIPE = plugin.getConfig().getBoolean("recipes.saddle", false);
        HORSE_ARMOR_RECIPE = plugin.getConfig().getBoolean("recipes.horse-armor", false);
        DOUBLE_STONE_SLAB_RECIPE = plugin.getConfig().getBoolean("recipes.double-stone-slab", false);
        FULL_STONE_SLAB_RECIPE = plugin.getConfig().getBoolean("recipes.full-stone-slab", false);
        DEAD_BUSH_RECIPE = plugin.getConfig().getBoolean("recipes.dead-bush", false);
        COBWEB_RECIPE = plugin.getConfig().getBoolean("recipes.cobweb", false);
        BLEACH_WOOL_RECIPE = plugin.getConfig().getBoolean("recipes.bleach-wool", false);
        BLEACH_CLAY_RECIPE = plugin.getConfig().getBoolean("recipes.bleach-clay", false);
        BLEACH_GLASS_RECIPE = plugin.getConfig().getBoolean("recipes.bleach-glass", false);
    }
    
    public void reload() {
        load();
    }
    
    public String[] printCurrentSettings() {
        List<String> settings = new ArrayList<>();
        settings.add(ChatColor.RED + "-=/" + ChatColor.RESET + "Current Cobra Feature Status" + ChatColor.RED + "\\=-");
        
        //Features
        settings.add(ChatColor.GOLD + "Silk Touch Spawners: " + statusCheck(SILK_TOUCH_SPAWNERS));
        settings.add(ChatColor.GOLD + "Aesthetic Command Blocks: " + statusCheck(AESTHETIC_COMMAND_BLOCKS));
        settings.add(ChatColor.GOLD + "Rain Water Buckets: " + statusCheck(RAIN_WATER_BUCKETS));
        settings.add(ChatColor.GOLD + "Self Powered Lamps: " + statusCheck(SELF_POWERED_LAMPS));
        settings.add(ChatColor.GOLD + "Bone Meal Ferns: " + statusCheck(BONE_MEAL_FERNS));
        settings.add(ChatColor.GOLD + "Infinite Lava Sources: " + statusCheck(INFINITE_LAVA_SOURCES));
        settings.add(ChatColor.GOLD + "Ambient Hopper Braziers: " + statusCheck(AMBIENT_HOPPER_BRAZIERS));
        
        //Recipes
        settings.add(ChatColor.GOLD + "Recipes:");
        settings.add(ChatColor.GOLD + " - Perma-Dirt: " + statusCheck(PERMA_DIRT_RECIPE));
        settings.add(ChatColor.GOLD + " - Podzol: " + statusCheck(PODZOL_RECIPE));
        settings.add(ChatColor.GOLD + " - Mycelium: " + statusCheck(MYCELIUM_RECIPE));
        settings.add(ChatColor.GOLD + " - End Portal Frame: " + statusCheck(END_PORTAL_FRAME_RECIPE));
        settings.add(ChatColor.GOLD + " - Cracked Stone Brick: " + statusCheck(CRACKED_STONE_BRICK_RECIPE));
        settings.add(ChatColor.GOLD + " - Name Tag: " + statusCheck(NAME_TAG_RECIPE));
        settings.add(ChatColor.GOLD + " - Saddle: " + statusCheck(SADDLE_RECIPE));
        settings.add(ChatColor.GOLD + " - Horse Armor: " + statusCheck(HORSE_ARMOR_RECIPE));
        settings.add(ChatColor.GOLD + " - Double Stone Slab: " + statusCheck(DOUBLE_STONE_SLAB_RECIPE));
        settings.add(ChatColor.GOLD + " - Full Stone Slab: " + statusCheck(FULL_STONE_SLAB_RECIPE));
        settings.add(ChatColor.GOLD + " - Dead Bush: " + statusCheck(DEAD_BUSH_RECIPE));
        settings.add(ChatColor.GOLD + " - Cobweb: " + statusCheck(COBWEB_RECIPE));
        settings.add(ChatColor.GOLD + " - Bleach Wool: " + statusCheck(BLEACH_WOOL_RECIPE));
        settings.add(ChatColor.GOLD + " - Bleach Clay: " + statusCheck(BLEACH_CLAY_RECIPE));
        settings.add(ChatColor.GOLD + " - Bleach Glass: " + statusCheck(BLEACH_GLASS_RECIPE));
        
        return settings.toArray(new String[settings.size()]);
    }
    
    public String statusCheck(Boolean toCheck) {
        if(toCheck)
            return (ChatColor.GREEN + "Enabled");
        else
            return (ChatColor.GRAY + "Disabled");
    }
}
