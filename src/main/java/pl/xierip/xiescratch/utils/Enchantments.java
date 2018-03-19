package pl.xierip.xiescratch.utils;

import org.bukkit.enchantments.Enchantment;

import java.util.Map;
import java.util.TreeMap;

public class Enchantments {
    public static Map<String, Enchantment> ENCHANTMENTS;

    public static void enable() {
        Enchantments.ENCHANTMENTS = new TreeMap<>();
        for (final Enchantment enchantment : Enchantment.values()) {
            ENCHANTMENTS.put(enchantment.getName().toLowerCase().replace("_", ""), enchantment);
            ENCHANTMENTS.put(String.valueOf(enchantment.getId()), enchantment);
        }
        Enchantments.ENCHANTMENTS.put("alldamage", Enchantment.DAMAGE_ALL);
        Enchantments.ENCHANTMENTS.put("alldmg", Enchantment.DAMAGE_ALL);
        Enchantments.ENCHANTMENTS.put("sharpness", Enchantment.DAMAGE_ALL);
        Enchantments.ENCHANTMENTS.put("sharp", Enchantment.DAMAGE_ALL);
        Enchantments.ENCHANTMENTS.put("dal", Enchantment.DAMAGE_ALL);
        Enchantments.ENCHANTMENTS.put("ardmg", Enchantment.DAMAGE_ARTHROPODS);
        Enchantments.ENCHANTMENTS.put("baneofarthropods", Enchantment.DAMAGE_ARTHROPODS);
        Enchantments.ENCHANTMENTS.put("baneofarthropod", Enchantment.DAMAGE_ARTHROPODS);
        Enchantments.ENCHANTMENTS.put("arthropod", Enchantment.DAMAGE_ARTHROPODS);
        Enchantments.ENCHANTMENTS.put("dar", Enchantment.DAMAGE_ARTHROPODS);
        Enchantments.ENCHANTMENTS.put("undeaddamage", Enchantment.DAMAGE_UNDEAD);
        Enchantments.ENCHANTMENTS.put("smite", Enchantment.DAMAGE_UNDEAD);
        Enchantments.ENCHANTMENTS.put("du", Enchantment.DAMAGE_UNDEAD);
        Enchantments.ENCHANTMENTS.put("digspeed", Enchantment.DIG_SPEED);
        Enchantments.ENCHANTMENTS.put("efficiency", Enchantment.DIG_SPEED);
        Enchantments.ENCHANTMENTS.put("minespeed", Enchantment.DIG_SPEED);
        Enchantments.ENCHANTMENTS.put("cutspeed", Enchantment.DIG_SPEED);
        Enchantments.ENCHANTMENTS.put("ds", Enchantment.DIG_SPEED);
        Enchantments.ENCHANTMENTS.put("eff", Enchantment.DIG_SPEED);
        Enchantments.ENCHANTMENTS.put("durability", Enchantment.DURABILITY);
        Enchantments.ENCHANTMENTS.put("dura", Enchantment.DURABILITY);
        Enchantments.ENCHANTMENTS.put("unbreaking", Enchantment.DURABILITY);
        Enchantments.ENCHANTMENTS.put("d", Enchantment.DURABILITY);
        Enchantments.ENCHANTMENTS.put("unb", Enchantment.DURABILITY);
        Enchantments.ENCHANTMENTS.put("ub", Enchantment.DURABILITY);
        Enchantments.ENCHANTMENTS.put("thorns", Enchantment.THORNS);
        Enchantments.ENCHANTMENTS.put("highcrit", Enchantment.THORNS);
        Enchantments.ENCHANTMENTS.put("thorn", Enchantment.THORNS);
        Enchantments.ENCHANTMENTS.put("highercrit", Enchantment.THORNS);
        Enchantments.ENCHANTMENTS.put("t", Enchantment.THORNS);
        Enchantments.ENCHANTMENTS.put("fireaspect", Enchantment.FIRE_ASPECT);
        Enchantments.ENCHANTMENTS.put("fire", Enchantment.FIRE_ASPECT);
        Enchantments.ENCHANTMENTS.put("meleefire", Enchantment.FIRE_ASPECT);
        Enchantments.ENCHANTMENTS.put("meleeflame", Enchantment.FIRE_ASPECT);
        Enchantments.ENCHANTMENTS.put("fa", Enchantment.FIRE_ASPECT);
        Enchantments.ENCHANTMENTS.put("knockback", Enchantment.KNOCKBACK);
        Enchantments.ENCHANTMENTS.put("kback", Enchantment.KNOCKBACK);
        Enchantments.ENCHANTMENTS.put("kb", Enchantment.KNOCKBACK);
        Enchantments.ENCHANTMENTS.put("knock", Enchantment.KNOCKBACK);
        Enchantments.ENCHANTMENTS.put("k", Enchantment.KNOCKBACK);
        Enchantments.ENCHANTMENTS.put("blockslootbonus", Enchantment.LOOT_BONUS_BLOCKS);
        Enchantments.ENCHANTMENTS.put("fortune", Enchantment.LOOT_BONUS_BLOCKS);
        Enchantments.ENCHANTMENTS.put("fort", Enchantment.LOOT_BONUS_BLOCKS);
        Enchantments.ENCHANTMENTS.put("lbb", Enchantment.LOOT_BONUS_BLOCKS);
        Enchantments.ENCHANTMENTS.put("mobslootbonus", Enchantment.LOOT_BONUS_MOBS);
        Enchantments.ENCHANTMENTS.put("mobloot", Enchantment.LOOT_BONUS_MOBS);
        Enchantments.ENCHANTMENTS.put("looting", Enchantment.LOOT_BONUS_MOBS);
        Enchantments.ENCHANTMENTS.put("lbm", Enchantment.LOOT_BONUS_MOBS);
        Enchantments.ENCHANTMENTS.put("oxygen", Enchantment.OXYGEN);
        Enchantments.ENCHANTMENTS.put("respiration", Enchantment.OXYGEN);
        Enchantments.ENCHANTMENTS.put("breathing", Enchantment.OXYGEN);
        Enchantments.ENCHANTMENTS.put("breath", Enchantment.OXYGEN);
        Enchantments.ENCHANTMENTS.put("o", Enchantment.OXYGEN);
        Enchantments.ENCHANTMENTS.put("protection", Enchantment.PROTECTION_ENVIRONMENTAL);
        Enchantments.ENCHANTMENTS.put("prot", Enchantment.PROTECTION_ENVIRONMENTAL);
        Enchantments.ENCHANTMENTS.put("protect", Enchantment.PROTECTION_ENVIRONMENTAL);
        Enchantments.ENCHANTMENTS.put("p", Enchantment.PROTECTION_ENVIRONMENTAL);
        Enchantments.ENCHANTMENTS.put("explosionsprotection", Enchantment.PROTECTION_EXPLOSIONS);
        Enchantments.ENCHANTMENTS.put("explosionprotection", Enchantment.PROTECTION_EXPLOSIONS);
        Enchantments.ENCHANTMENTS.put("expprot", Enchantment.PROTECTION_EXPLOSIONS);
        Enchantments.ENCHANTMENTS.put("blastprotection", Enchantment.PROTECTION_EXPLOSIONS);
        Enchantments.ENCHANTMENTS.put("blastprotect", Enchantment.PROTECTION_EXPLOSIONS);
        Enchantments.ENCHANTMENTS.put("pe", Enchantment.PROTECTION_EXPLOSIONS);
        Enchantments.ENCHANTMENTS.put("fallprotection", Enchantment.PROTECTION_FALL);
        Enchantments.ENCHANTMENTS.put("fallprot", Enchantment.PROTECTION_FALL);
        Enchantments.ENCHANTMENTS.put("featherfall", Enchantment.PROTECTION_FALL);
        Enchantments.ENCHANTMENTS.put("featherfalling", Enchantment.PROTECTION_FALL);
        Enchantments.ENCHANTMENTS.put("pfa", Enchantment.PROTECTION_FALL);
        Enchantments.ENCHANTMENTS.put("fireprotection", Enchantment.PROTECTION_FIRE);
        Enchantments.ENCHANTMENTS.put("flameprotection", Enchantment.PROTECTION_FIRE);
        Enchantments.ENCHANTMENTS.put("fireprotect", Enchantment.PROTECTION_FIRE);
        Enchantments.ENCHANTMENTS.put("flameprotect", Enchantment.PROTECTION_FIRE);
        Enchantments.ENCHANTMENTS.put("fireprot", Enchantment.PROTECTION_FIRE);
        Enchantments.ENCHANTMENTS.put("flameprot", Enchantment.PROTECTION_FIRE);
        Enchantments.ENCHANTMENTS.put("pf", Enchantment.PROTECTION_FIRE);
        Enchantments.ENCHANTMENTS.put("projectileprotection", Enchantment.PROTECTION_PROJECTILE);
        Enchantments.ENCHANTMENTS.put("projprot", Enchantment.PROTECTION_PROJECTILE);
        Enchantments.ENCHANTMENTS.put("pp", Enchantment.PROTECTION_PROJECTILE);
        Enchantments.ENCHANTMENTS.put("silktouch", Enchantment.SILK_TOUCH);
        Enchantments.ENCHANTMENTS.put("softtouch", Enchantment.SILK_TOUCH);
        Enchantments.ENCHANTMENTS.put("st", Enchantment.SILK_TOUCH);
        Enchantments.ENCHANTMENTS.put("waterworker", Enchantment.WATER_WORKER);
        Enchantments.ENCHANTMENTS.put("aquaaffinity", Enchantment.WATER_WORKER);
        Enchantments.ENCHANTMENTS.put("watermine", Enchantment.WATER_WORKER);
        Enchantments.ENCHANTMENTS.put("ww", Enchantment.WATER_WORKER);
        Enchantments.ENCHANTMENTS.put("firearrow", Enchantment.ARROW_FIRE);
        Enchantments.ENCHANTMENTS.put("flame", Enchantment.ARROW_FIRE);
        Enchantments.ENCHANTMENTS.put("flamearrow", Enchantment.ARROW_FIRE);
        Enchantments.ENCHANTMENTS.put("af", Enchantment.ARROW_FIRE);
        Enchantments.ENCHANTMENTS.put("arrowdamage", Enchantment.ARROW_DAMAGE);
        Enchantments.ENCHANTMENTS.put("power", Enchantment.ARROW_DAMAGE);
        Enchantments.ENCHANTMENTS.put("arrowpower", Enchantment.ARROW_DAMAGE);
        Enchantments.ENCHANTMENTS.put("ad", Enchantment.ARROW_DAMAGE);
        Enchantments.ENCHANTMENTS.put("arrowknockback", Enchantment.ARROW_KNOCKBACK);
        Enchantments.ENCHANTMENTS.put("arrowkb", Enchantment.ARROW_KNOCKBACK);
        Enchantments.ENCHANTMENTS.put("punch", Enchantment.ARROW_KNOCKBACK);
        Enchantments.ENCHANTMENTS.put("arrowpunch", Enchantment.ARROW_KNOCKBACK);
        Enchantments.ENCHANTMENTS.put("ak", Enchantment.ARROW_KNOCKBACK);
        Enchantments.ENCHANTMENTS.put("infinitearrows", Enchantment.ARROW_INFINITE);
        Enchantments.ENCHANTMENTS.put("infarrows", Enchantment.ARROW_INFINITE);
        Enchantments.ENCHANTMENTS.put("infinity", Enchantment.ARROW_INFINITE);
        Enchantments.ENCHANTMENTS.put("infinite", Enchantment.ARROW_INFINITE);
        Enchantments.ENCHANTMENTS.put("unlimited", Enchantment.ARROW_INFINITE);
        Enchantments.ENCHANTMENTS.put("unlimitedarrows", Enchantment.ARROW_INFINITE);
        Enchantments.ENCHANTMENTS.put("ai", Enchantment.ARROW_INFINITE);
    }

    public static Enchantment getEnchantment(final String string) {
        final String enchantmentName = string.toLowerCase();
        if (Enchantments.ENCHANTMENTS.containsKey(enchantmentName)) {
            return Enchantments.ENCHANTMENTS.get(enchantmentName);
        }
        return null;
    }
}
