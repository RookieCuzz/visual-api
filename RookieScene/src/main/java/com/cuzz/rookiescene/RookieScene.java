package com.cuzz.rookiescene;

import net.deechael.visual.api.VisualPlatform;
import net.deechael.visual.api.geometry.position.Coordinate3d;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class RookieScene extends JavaPlugin {


    @Override
    public void onEnable() {
        // 注册命令处理器
        getCommand("rline").setExecutor((CommandExecutor) new LineCommand());
        getCommand("rtest").setExecutor((CommandExecutor) new TestCommand());
    }
        @Override
        public void onDisable(){

        }

}
