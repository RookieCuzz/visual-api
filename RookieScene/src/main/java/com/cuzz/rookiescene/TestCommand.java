package com.cuzz.rookiescene;

import net.deechael.visual.api.geometry.position.Coordinate3d;
import net.deechael.visual.geometry.position.Point3dImpl;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import net.deechael.visual.api.VisualPlatform;
import net.deechael.visual.api.animation.AnimationSet;
import net.deechael.visual.api.geometry.position.Coordinate3d;
import net.deechael.visual.api.geometry.position.Point3d;
import net.deechael.visual.api.geometry.position.Vector3d;
import net.deechael.visual.bukkit.VisualBukkitPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length != 12 || !(sender instanceof Player))
            return false; // 返回 false 以显示错误用法

        int[] startXYZ = CommandUtils.parse(args, 0);
        int[] endXYZ = CommandUtils.parse(args, 3);
        int[] end2XYZ = CommandUtils.parse(args, 6);
        int[] end3XYZ = CommandUtils.parse(args, 9);

        Coordinate3d root = VisualPlatform.getPlatform().getGeometry().getRootCoordinate();
        Point3d point1 = root.getPoint(startXYZ[0], -startXYZ[2], startXYZ[1]);
        Point3d point2 = root.getPoint(endXYZ[0], -endXYZ[2], endXYZ[1]);
        Point3d point3 = root.getPoint(end2XYZ[0], -end2XYZ[2], end2XYZ[1]);
        Point3d point4 = root.getPoint(end3XYZ[0], -end3XYZ[2], end3XYZ[1]);

        Point3d looking = new Point3dImpl(point1.getCoordinate(),
                (point1.x() + point2.x()) / 2,
                (point1.y() + point2.y()) / 2,
                ((point1.z() + point2.z()) / 2));

        // 使用 AnimationFactory 来创建动画
        AnimationSet testSet = AnimationFactory.createComplexAnimation(point1, point2, point3, point4, looking);
        testSet.play(VisualBukkitPlugin.getInstance().getViewer((Player) sender));
        return true;
    }
}


