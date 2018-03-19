package pl.xierip.xiescratch.utils;

import org.bukkit.Color;

import java.lang.reflect.Field;

/**
 * Created by xierip on 25.07.17.
 * Web: http://xierip.pl
 */
public class ColorUtil {
    public static Color getColorByName(String name) {
        for (Field field : Color.class.getDeclaredFields()) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            if (!field.getType().isAssignableFrom(Color.class)) {
                continue;
            }
            if (field.getName().equalsIgnoreCase(name)) {
                try {
                    return (Color) field.get(null);
                } catch (IllegalAccessException e) {
                    return null;
                }
            }
        }
        return null;
    }
}
