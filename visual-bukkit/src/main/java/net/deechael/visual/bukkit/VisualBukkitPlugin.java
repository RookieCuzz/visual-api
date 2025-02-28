package net.deechael.visual.bukkit;

import net.deechael.visual.animation.AnimationManagerImpl;
import net.deechael.visual.api.Viewer;
import net.deechael.visual.api.VisualPlatform;
import net.deechael.visual.api.animation.AnimationManager;
import net.deechael.visual.api.curve.CurveManager;
import net.deechael.visual.api.geometry.Geometry;
import net.deechael.visual.curve.CurveManagerImpl;
import net.deechael.visual.geometry.GeometryImpl;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class VisualBukkitPlugin extends JavaPlugin implements VisualPlatform, Listener {

    private   AnimationManager ANIMATION_MANAGER ;
    private   Geometry GEOMETRY;
    private   CurveManager CURVE_MANAGER;

    private  Map<UUID, BukkitViewer> viewers = new HashMap<>();
    private static VisualBukkitPlugin INSTANCE;

    @EventHandler
    public void playerQuit(PlayerQuitEvent event) {
        if (viewers.containsKey(event.getPlayer().getUniqueId())) {
            BukkitViewer viewer = viewers.remove(event.getPlayer().getUniqueId());
            if (!viewer.isReleased())
                viewer.release();
        }
    }

    public static VisualBukkitPlugin getInstance() {
        return INSTANCE;
    }

    public Viewer getViewer(Player player) {

        ItemStack pumpkinHead = new ItemStack(Material.CARVED_PUMPKIN);

        // 将南瓜头放到玩家的头盔槽
        player.getInventory().setHelmet(pumpkinHead);


        if (this.viewers.containsKey(player.getUniqueId()))
            return this.viewers.get(player.getUniqueId());
        BukkitViewer newViewer = new BukkitViewer(player);
        this.viewers.put(player.getUniqueId(), newViewer);
        return newViewer;
    }

    @Override
    public void onEnable() {
        INSTANCE=this;
        GEOMETRY = new GeometryImpl();
        ANIMATION_MANAGER= new AnimationManagerImpl();

        CURVE_MANAGER = new CurveManagerImpl();
        System.out.println("777");
        Bukkit.getPluginManager().registerEvents(this, this);
    }
        //GU
    @Override
    public void onDisable() {
        for (BukkitViewer viewer : this.viewers.values()) {
            if (viewer.isReleased())
                continue;
            viewer.release();
        }
    }

    @Override
    public AnimationManager getAnimationManager() {
        return ANIMATION_MANAGER;
    }

    @Override
    public Geometry getGeometry() {
        return GEOMETRY;
    }

    @Override
    public CurveManager getCurveManager() {
        return CURVE_MANAGER;
    }

}
