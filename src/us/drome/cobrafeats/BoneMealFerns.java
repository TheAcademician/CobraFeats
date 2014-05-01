package us.drome.cobrafeats;

import java.util.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BoneMealFerns implements Listener {
    CobraFeats plugin;
    FeatsUtils utils;
    Random randomizer = new Random();
    
    public BoneMealFerns(CobraFeats plugin) {
        this.plugin = plugin;
    }
    
    public void registerEvents() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        ItemStack item = (event.hasItem() ? event.getItem() : new ItemStack(Material.AIR));
            
        
        if(plugin.config.BONE_MEAL_FERNS && event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && block.getType().equals(Material.GRASS) &&
            item.isSimilar(new ItemStack(Material.INK_SACK, 1, (short)15))) {

            if(utils.biomeMatcher("Jungle") || utils.biomeMatcher("Taiga")) {
                Location growLoc = block.getLocation();
                growLoc.setY(growLoc.getY() + 1);

                for (int x = -3; x <= 3; x++) {
                    for(int z = -3; z <= 3; z++) {
                        int tester = randomizer.nextInt(100);
                        if(tester <= 10) {
                            Location testLoc = growLoc.getBlock().getRelative(x, 0, z).getLocation();
                            if(testLoc.getBlock().getType().equals(Material.AIR) && testLoc.getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.GRASS)) {
                                testLoc.getBlock().setType(Material.LONG_GRASS);
                                testLoc.getBlock().setData((byte)2);
                            }
                        }
                    }
                }
            }
        }
    }
}
