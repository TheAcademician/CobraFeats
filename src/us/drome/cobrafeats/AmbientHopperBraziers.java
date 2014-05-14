package us.drome.cobrafeats;

import java.util.ArrayList;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Hopper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AmbientHopperBraziers implements Listener {
    CobraFeats plugin;
    FeatsUtils utils;
    ArrayList<Location> locations = new ArrayList<>();
    ItemStack identifier;
    
    public AmbientHopperBraziers(CobraFeats plugin) {
        this.plugin = plugin;
        identifier = new ItemStack(Material.FIREBALL);
        ItemMeta idmeta = identifier.getItemMeta();
        idmeta.setLore(new ArrayList<String>() {{ add("isBrazier"); }});
        identifier.setItemMeta(idmeta);
    }
        
    public void registerEvents() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    @EventHandler
    public void onItemMove(InventoryMoveItemEvent event) {
        if(event.getItem().equals(identifier) || event.getDestination().contains(identifier)) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onBlockUpdate(BlockPhysicsEvent event) {
        Block block = event.getBlock();
        if(plugin.config.AMBIENT_HOPPER_BRAZIERS && block.getType().equals(Material.HOPPER) && ((Hopper)block.getState()).getInventory().contains(identifier)) {
            if(block.isBlockPowered() || block.isBlockIndirectlyPowered()) {
                if(block.getRelative(BlockFace.UP).getType().equals(Material.FIRE)) {
                    block.getRelative(BlockFace.UP).setType(Material.AIR);
                }
            } else {
                if(block.getRelative(BlockFace.UP).getType().equals(Material.AIR)) {
                    block.getRelative(BlockFace.UP).setType(Material.FIRE);
                }
            }
        }
    }
    
    @EventHandler
    public void onFireOut(BlockFadeEvent event) {
        Block block = event.getBlock();
        if(plugin.config.AMBIENT_HOPPER_BRAZIERS && block.getType().equals(Material.FIRE)) {
            Block relative = block.getRelative(BlockFace.DOWN);
            if(relative.getType().equals(Material.HOPPER) && ((Hopper)relative.getState()).getInventory().contains(identifier)) {
                if(!relative.isBlockPowered())
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
                Hopper state = (Hopper)block.getState();
                state.getInventory().addItem(identifier);
                state.update();
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
            if(block.getType().equals(Material.HOPPER) && ((Hopper)block.getState()).getInventory().contains(identifier)) {
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
            
            if(block.getType().equals(Material.HOPPER) && utils.isSilkTouch(tool) && ((Hopper)block.getState()).getInventory().contains(identifier)) {
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
    
    @EventHandler
    public void onItemSpawn(ItemSpawnEvent event) {
        if(event.getEntity().getItemStack().equals(identifier))
            event.setCancelled(true);
    }
}
