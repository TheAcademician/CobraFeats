package us.drome.cobrafeats;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class InfiniteLavaSources implements Listener {
    CobraFeats plugin;
    FeatsUtils utils;
    
    public InfiniteLavaSources(CobraFeats plugin) {
        this.plugin = plugin;
    }
    
    public void registerEvents() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    @EventHandler
    public void onLavaFlow(BlockFromToEvent event) {
        if(event.getBlock().getType().equals(Material.STATIONARY_LAVA)) {
            Block flowedTo = event.getToBlock();
            Block belowFlow = flowedTo.getRelative(BlockFace.DOWN);
            if(belowFlow.getType().equals(Material.AIR) || (belowFlow.getType().equals(Material.LAVA) ||
                belowFlow.getType().equals(Material.STATIONARY_LAVA) && belowFlow.getData() != (byte)0)) {
                return;
            }
            if(utils.isBlockAdjacent(flowedTo, Material.STATIONARY_LAVA, (byte)0, 2) && flowedTo.getType().equals(Material.LAVA)) {
                flowedTo.setType(Material.STATIONARY_LAVA);
                flowedTo.setData((byte)0);
            }
        }
    }
    
    //testing something...
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.hasItem() && event.getItem().getType().equals(Material.STICK)) {
            event.getPlayer().sendMessage(event.getClickedBlock().getType().name());
        }
    }
}
