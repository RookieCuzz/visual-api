package com.cuzz.rookiescene;

import net.deechael.visual.api.VisualPlatform;
import net.deechael.visual.api.animation.AnimationSet;
import net.deechael.visual.api.geometry.position.Coordinate3d;
import net.deechael.visual.api.geometry.position.Point3d;
import net.deechael.visual.api.geometry.position.Vector3d;
import net.deechael.visual.bukkit.VisualBukkitPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LineCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length != 7 || !(sender instanceof Player)) {
            return false; // 返回 false 以显示错误用法
        }

        int[] startXYZ = CommandUtils.parse(args, 0);
        int[] endXYZ = CommandUtils.parse(args, 3);
        String curveType = args[6];

        Coordinate3d root = VisualPlatform.getPlatform().getGeometry().getRootCoordinate();
        Point3d point1 = root.getPoint(startXYZ[0], -startXYZ[2], startXYZ[1]);
        Point3d point2 = root.getPoint(endXYZ[0], -endXYZ[2], endXYZ[1]);

        Vector3d vector = root.getVector(point1, point2);
        Vector3d direction = root.getVector(vector.y(), -vector.x(), 0);

        // 使用 AnimationFactory 来创建动画
        AnimationSet testSet = AnimationFactory.createLineAnimation(point1, point2, direction, curveType);
        testSet.play(VisualBukkitPlugin.getInstance().getViewer((Player) sender));
        return true;
    }
}
