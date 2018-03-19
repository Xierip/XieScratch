package pl.xierip.xiescratch.utils;

import org.bukkit.ChatColor;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by Xierip on 2016-06-25.
 * DarkElite.pl ©
 */
public class StringUtil {
    private static final Pattern ALPHA_NUMERIC_PATTERN = Pattern.compile("^[a-zA-Z0-9_]*$");
    public static Pattern REPLACE_ALL_PATTERN = Pattern.compile("(?<!&)&([0-9a-fk-orA-FK-OR])");
    public static Pattern REPLACE_COLOR_PATTERN = Pattern.compile("(?<!&)&([0-9a-fA-F])");
    public static Pattern REPLACE_FORMAT_PATTERN = Pattern.compile("(?<!&)&([l-orL-OR])");
    public static Pattern REPLACE_MAGIC_PATTERN = Pattern.compile("(?<!&)&([Kk])");
    public static Pattern VANILLA_COLOR_PATTERN = Pattern.compile("§+[0-9A-Fa-f]");
    public static Pattern VANILLA_FORMAT_PATTERN = Pattern.compile("§+[L-ORl-or]");
    public static Pattern VANILLA_MAGIC_PATTERN = Pattern.compile("§+[Kk]");
    public static Pattern VANILLA_PATTERN = Pattern.compile("§+[0-9A-FK-ORa-fk-or]?");

    public static boolean contains(final String word, final String... strings) {
        for (final String string : strings) {
            if (string.equalsIgnoreCase(word))
                return true;
        }
        return false;
    }

    public static String fixColors(final String string) {
        return replace(replace(ChatColor.translateAlternateColorCodes('&', string), ">>", "\u00bb"), "<<", "\u00ab");
    }

    public static List<String> fixColors(final List<String> strings) {
        return strings.stream().map(StringUtil::fixColors).collect(toList());
    }

    public static boolean isAlphaNumeric(final String s) {
        return ALPHA_NUMERIC_PATTERN.matcher(s).matches();
    }

    public static String normalizeString(final String str) {
        final String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        final Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    public static List<String> replace(final List<String> list, String... replace) {
        return list.stream().map(s -> replace(s, replace)).collect(Collectors.toList());
    }

    public static String replace(String text, String... replace) {
        if (replace.length == 0) {
            return text;
        }
        if (replace.length % 2 != 0) {
            replace = Arrays.copyOfRange(replace, 0, replace.length - 1);
        }
        for (int i = 0; i < replace.length; i = i + 2) {
            text = replace(text, replace[i], replace[i + 1]);
        }
        return text;
    }

    public static String replace(final String text, final String searchString, final String replacement) {
        if (text == null || text.isEmpty() || searchString.isEmpty() || replacement == null) {
            return text;
        }
        int start = 0;
        int max = -1;
        int end = text.indexOf(searchString, start);
        if (end == -1) {
            return text;
        }
        final int replacedLength = searchString.length();
        int increase = replacement.length() - replacedLength;
        increase = (increase < 0 ? 0 : increase);
        increase *= (max < 0 ? 16 : (max > 64 ? 64 : max));
        final StringBuilder sb = new StringBuilder(text.length() + increase);
        while (end != -1) {
            sb.append(text.substring(start, end)).append(replacement);
            start = end + replacedLength;
            if (--max == 0) {
                break;
            }
            end = text.indexOf(searchString, start);
        }
        sb.append(text.substring(start));
        return sb.toString();
    }

    public static String stringColors(final String input, final Pattern pattern) {
        if (input == null) {
            return null;
        }
        return pattern.matcher(input).replaceAll("");
    }

    public static String stripColors(final String string) {
        return ChatColor.stripColor(string);
    }

    public static List<String> stripColors(final List<String> strings) {
        return strings.stream().map(StringUtil::stripColors).collect(toList());
    }
}
