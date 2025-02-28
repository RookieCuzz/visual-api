package com.cuzz.rookiescene;

import net.deechael.visual.api.VisualPlatform;
import net.deechael.visual.api.curve.Curve;

public class CommandUtils {

    public static int[] parse(String[] args, int index) {
        int[] ints = new int[3];
        for (int i = index; i < index + 3; i++) {
            ints[i - index] = Integer.parseInt(args[i]);
        }
        return ints;
    }

    public static Curve getCurve(String curveType) {
        return switch (curveType) {
            case "linear" -> VisualPlatform.getPlatform().getCurveManager().linear();
            case "ease" -> VisualPlatform.getPlatform().getCurveManager().ease();
            case "easeIn" -> VisualPlatform.getPlatform().getCurveManager().easeIn();
            case "easeOut" -> VisualPlatform.getPlatform().getCurveManager().easeOut();
            case "easeInOut" -> VisualPlatform.getPlatform().getCurveManager().easeInOut();
            default -> VisualPlatform.getPlatform().getCurveManager().cubicBezier(.06, .88, .21, .95);
        };
    }
}
