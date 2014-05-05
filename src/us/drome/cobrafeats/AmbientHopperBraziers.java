package us.drome.cobrafeats;

import java.util.ArrayList;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AmbientHopperBraziers implements Listener {
    CobraFeats plugin;
    FeatsUtils utils;
    ArrayList<Location> locations = new ArrayList<>();
    
    public AmbientHopperBraziers(CobraFeats plugin) {
        this.plugin = plugin;
    }
    
    public void registerEvents() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    @EventHandler
    public void onRedstoneUpdate(BlockRedstoneEvent event) {
        if(plugin.config.AMBIENT_HOPPER_BRAZIERS) {
            Block block = event.getBlock();
            plugin.getLogger().info("here");
            if(block.getType().equals(Material.HOPPER) && block.getData() == (byte)8) {
                if(event.getNewCurrent() > 1) {
                    if(block.getRelative(BlockFace.UP).getType().equals(Material.FIRE))
                        block.getRelative(BlockFace.UP).setType(Material.AIR);
                } else {
                    event.setNewCurrent(1);
                    if(block.getRelative(BlockFace.UP).getType().equals(Material.AIR))
                        block.getRelative(BlockFace.UP).setType(Material.FIRE);
                }
            }
        }
    }
    
    @EventHandler
    public void onFireOut(BlockFadeEvent event) {
        Block block = event.getBlock();
        plugin.getLogger().info(block.getType().name());
        if(plugin.config.AMBIENT_HOPPER_BRAZIERS && block.getType().equals(Material.FIRE)) {
            Block relative = block.getRelative(BlockFace.DOWN);
            if(relative.getType().equals(Material.HOPPER) && relative.getData() == (byte)8) {
                plugin.getLogger().info("power" + block.getBlockPower());
                if(relative.getBlockPower() <= 1)
                    event.setCancelled(true);
            }
        }
    }
    
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(event.isCancelled()) {
            return;
        }
        if(plugin.config.AMBIENT_HOPPER_BRAZIERS) {
            Block block = event.getBlock();
            ItemStack item = event.getItemInHand();

            if(item.getType().equals(Material.HOPPER) && item.hasItemMeta() && item.getItemMeta().getLore().contains("Now with Ambience 2.0!")) {
                block.setType(Material.HOPPER);
                block.setData((byte)8);
                if(block.getRelative(BlockFace.UP).getType().equals(Material.AIR)) {
                    block.getRelative(BlockFace.UP).setType(Material.FIRE);
                }
            }
        }
    }
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        
        if(plugin.config.AMBIENT_HOPPER_BRAZIERS && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if(block.getType().equals(Material.HOPPER) && block.getData() == (byte)8) {
                event.setCancelled(true);
            }
        }
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(event.isCancelled()) {
            return;
        }        
        if(plugin.config.AMBIENT_HOPPER_BRAZIERS && event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
            Block block = event.getBlock();
            ItemStack tool = event.getPlayer().getItemInHand();
            
            if(block.getType().equals(Material.HOPPER) && utils.isSilkTouch(tool) && block.getData() == (byte)8) {
                if(locations.contains(block.getLocation())) {
                    locations.remove(block.getLocation());
                } else {
                    locations.add(block.getLocation());
                    plugin.getServer().getPluginManager().callEvent(new BlockBreakEvent(block, event.getPlayer()));
                    ItemStack hopperBrazier = new ItemStack(Material.HOPPER);
                    ItemMeta hopperBrazierMeta = hopperBrazier.getItemMeta();
                    hopperBrazierMeta.setDisplayName("Hopper Brazier");
                    hopperBrazierMeta.setLore(new ArrayList<String>() {{ add("Now with Ambience 2.0!"); }});
                    hopperBrazier.setItemMeta(hopperBrazierMeta);
                    block.getWorld().dropItemNaturally(block.getLocation(), hopperBrazier);
                    block.setType(Material.AIR);
                }
            }
        }
    }
}
