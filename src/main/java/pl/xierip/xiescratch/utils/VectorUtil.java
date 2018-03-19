package pl.xierip.xiescratch.utils;

import org.bukkit.util.Vector;

/**
 * Created by xierip on 12.04.17.
 * Web: http://xierip.pl
 */
public class VectorUtil {

    public static Vector getDirection(final double rotX) {
        final Vector vector = new Vector();
        final double rotY = 0;
        vector.setY(-Math.sin(Math.toRadians(rotY)));
        final double xz = Math.cos(Math.toRadians(rotY));
        vector.setX(-xz * Math.sin(Math.toRadians(rotX)));
        vector.setZ(xz * Math.cos(Math.toRadians(rotX)));
        return vector;
    }
}
