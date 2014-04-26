package us.drome.cobrafeats;

import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SelfPoweredLamps implements Listener {
    CobraFeats plugin;
    FeatsUtils utils;
    
    public SelfPoweredLamps(CobraFeats plugin) {
        this.plugin = plugin;
    }
    
    public void registerEvents() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    @EventHandler
    public void onRedstoneUpdate(BlockRedstoneEvent event) {
        if(plugin.config.SELF_POWERED_LAMPS) {
            Block block = event.getBlock();
            if(block.getType().equals(Material.REDSTONE_LAMP_ON) && block.getData() == (byte)2) {
                event.setNewCurrent(5);
            }
        }
    }
    
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(event.isCancelled()) {
            return;
        }
        if(plugin.config.SELF_POWERED_LAMPS) {
            Block block = event.getBlock();
            ItemStack item = event.getItemInHand();

            if(item.getType().equals(Material.REDSTONE_LAMP_OFF) && item.getDurability() == (short)2) {
                block.setType(Material.REDSTONE_LAMP_ON);
                block.setData((byte)2);
            }
        }
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(event.isCancelled()) {
            return;
        }
        if(plugin.config.SELF_POWERED_LAMPS) {
            Block block = event.getBlock();
            ItemStack tool = event.getPlayer().getItemInHand();
            
            if(block.getType().equals(Material.REDSTONE_LAMP_ON) && utils.isSilkTouch(tool) && block.getData() == (byte)2) {
                ItemStack poweredLamp = new ItemStack(Material.REDSTONE_LAMP_OFF, 1, (short)2);
                ItemMeta lampMeta = poweredLamp.getItemMeta();
                lampMeta.setLore(new ArrayList<String>() {{ add("Rumored to be powered by Element 115!"); }});
                lampMeta.setDisplayName("Powered Lamp");
                poweredLamp.setItemMeta(lampMeta);
                
                event.setCancelled(true);
                
                block.getWorld().dropItemNaturally(block.getLocation(), poweredLamp);
            }
        }
    }
}
