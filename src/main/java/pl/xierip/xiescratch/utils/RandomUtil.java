package pl.xierip.xiescratch.utils;

import org.apache.commons.lang.Validate;

import java.util.Random;

/**
 * Created by xierip on 30.03.17.
 * Web: http://xierip.pl
 */
public class RandomUtil {

    private static final Random random = new Random();

    public static boolean getChance(final double chance) {
        return !(chance <= 0) && (chance >= 100 || (chance >= getRandomDouble(0, 100)));
    }

    public static int getIntBetween(final int min, int max) {
        max++;
        return random.nextInt(max - min) + min;
    }

    private static double getRandomDouble(final double min, final double max) throws IllegalArgumentException {
        Validate.isTrue(max > min, "Max can't be smaller than min!");
        return (random.nextDouble() * (max - min) + min);
    }
}
