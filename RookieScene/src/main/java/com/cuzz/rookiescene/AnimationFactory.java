package com.cuzz.rookiescene;

import net.deechael.visual.api.VisualPlatform;
import net.deechael.visual.api.animation.AnimationSet;
import net.deechael.visual.api.curve.Curve;
import net.deechael.visual.api.geometry.position.Point3d;
import net.deechael.visual.api.geometry.position.Vector3d;
import net.deechael.visual.geometry.position.Point2dImpl;

public class AnimationFactory {

    /**
     * 创建一条线性动画。
     *
     * @param point1    起点
     * @param point2    终点
     * @param direction 动画的方向
     * @param curveType 曲线类型
     * @return 动画集
     */
    public static AnimationSet createLineAnimation(Point3d point1, Point3d point2, Vector3d direction, String curveType) {
        Curve curve = switch (curveType) {
            case "linear" -> VisualPlatform.getPlatform().getCurveManager().linear();
            case "ease" -> VisualPlatform.getPlatform().getCurveManager().ease();
            case "easeIn" -> VisualPlatform.getPlatform().getCurveManager().easeIn();
            case "easeOut" -> VisualPlatform.getPlatform().getCurveManager().easeOut();
            case "easeInOut" -> VisualPlatform.getPlatform().getCurveManager().easeInOut();
            default -> VisualPlatform.getPlatform().getCurveManager().cubicBezier(.06, .88, .21, .95);
        };

        return VisualPlatform.getPlatform().getAnimationManager()
                .createAnimationSet()
                .append(
                        VisualPlatform.getPlatform().getAnimationManager()
                                .createAnimation()
                                .start(point1)
                                .end(point2)
                                .duration(3)
                                .framerate(999)
                                .perspective(direction, false)
                                .path(VisualPlatform.getPlatform().getGeometry().createLine(0, 0, 0), false)
                                .curve(curve)
                                .build()
                )
                .build();
    }

    /**
     * 创建复杂动画集。
     *
     * @param point1 起点1
     * @param point2 起点2
     * @param point3 起点3
     * @param point4 起点4
     * @param looking 视角点
     * @return 动画集
     */
    public static AnimationSet createComplexAnimation(Point3d point1, Point3d point2, Point3d point3, Point3d point4, Point3d looking) {
        return VisualPlatform.getPlatform().getAnimationManager()
                .createAnimationSet()
                .append(
                        VisualPlatform.getPlatform().getAnimationManager()
                                .createAnimation()
                                .duration(2)
                                .framerate(2999)
                                .start(point1)
                                .end(point2)
                                .perspective(looking.add(0, 0, 10), false)
                                .path(VisualPlatform.getPlatform().getGeometry().createLine(0, 0, 0), false)
                                .curve(VisualPlatform.getPlatform().getCurveManager().linear())
                                .build()
                )
                .append(
                        VisualPlatform.getPlatform().getAnimationManager()
                                .createAnimation()
                                .duration(3)
                                .framerate(2999)
                                .start(point2)
                                .end(point3)
                                .perspective(looking.add(0, 0, 10), false)
                                .path(VisualPlatform.getPlatform().getGeometry().createEllipse(new Point2dImpl(0, 0), 10, 5), false)
                                .curve(VisualPlatform.getPlatform().getCurveManager().cubicBezier(.32, .17, .2, .97))
                                .build()
                )
                .append(
                        VisualPlatform.getPlatform().getAnimationManager()
                                .createAnimation()
                                .duration(2)
                                .framerate(2999)
                                .start(point3)
                                .end(point4)
                                .perspective(looking.add(0, 0, 10), false)
                                .path(VisualPlatform.getPlatform().getGeometry().createEllipse(new Point2dImpl(0, 0), 10, 10), true)
                                .curve(VisualPlatform.getPlatform().getCurveManager().easeInOut())
                                .build()
                )
                .build();
    }
}
