package us.drome.cobrafeats;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

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
        Block block = event.getBlock();
        if(block.getType().equals(Material.STATIONARY_LAVA) || block.getType().equals(Material.LAVA) && block.getData() == (byte)0) {
            Block flowedTo = event.getToBlock();
            Block belowFlow = flowedTo.getRelative(BlockFace.DOWN);
            if(belowFlow.getType().equals(Material.AIR) || (belowFlow.getType().equals(Material.LAVA) ||
                belowFlow.getType().equals(Material.STATIONARY_LAVA) && belowFlow.getData() != (byte)0)) {
                return;
            }
            if(utils.isBlockAdjacent(flowedTo, Material.STATIONARY_LAVA, (byte)0, 2) || utils.isBlockAdjacent(flowedTo, Material.LAVA, (byte)0, 2)) {
                flowedTo.setType(Material.LAVA);
                flowedTo.setData((byte)0);
            }
        }
    }
}
