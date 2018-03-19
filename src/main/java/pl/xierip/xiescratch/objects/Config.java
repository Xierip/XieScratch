package pl.xierip.xiescratch.objects;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import pl.xierip.xiescratch.exceptions.ConfigException;
import pl.xierip.xiescratch.utils.Enchantments;
import pl.xierip.xiescratch.utils.IOUtil;
import pl.xierip.xiescratch.utils.ItemBuilder;
import pl.xierip.xiescratch.utils.StringUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Config {
    @Getter
    private final YamlConfiguration fileConfiguration;
    private File file;

    public Config(final Plugin plugin, final String fileName) {
        this.file = new File(plugin.getDataFolder(), fileName);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            final InputStream is = plugin.getResource(file.getName());
            if (is != null) {
                IOUtil.copy(is, file);
            }
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public Location deserializeLocation(final String path) throws ConfigException {
        if (this.isSet(path + ".yaw") && this.isSet(path + ".pitch"))
            return new Location(Bukkit.getWorld(this.getString(path + ".world")), this.getDouble(path + ".x"), this.getDouble(path + ".y"), this.getDouble(path + ".z"), (float) this.getDouble(path + ".yaw"), (float) this.getDouble(path + ".pitch"));
        else
            return new Location(Bukkit.getWorld(this.getString(path + ".world")), this.getDouble(path + ".x"), this.getDouble(path + ".y"), this.getDouble(path + ".z"));
    }

    public Location deserializeSafeLocation(final String path) {
        if (!fileConfiguration.isSet(path + ".world")) {
            return null;
        }
        try {
            return new Location(Bukkit.getWorld(this.getString(path + ".world")), this.getDouble(path + ".x"), this.getDouble(path + ".y"), this.getDouble(path + ".z"), (float) this.getDouble(path + ".yaw"), (float) this.getDouble(path + ".pitch"));
        } catch (final ConfigException e) {
            return null;
        }
    }

    public Object get(final String path) throws ConfigException {
        if (!fileConfiguration.isSet(path)) {
            throw new ConfigException("'" + path + "' isn't set");
        } else

            return fileConfiguration.get(path);
    }

    public boolean getBoolean(final String path) throws ConfigException {
        if (!fileConfiguration.isSet(path)) {
            throw new ConfigException("'" + path + "' isn't set");
        } else if (!fileConfiguration.isBoolean(path)) {
            throw new ConfigException("'" + path + "' isn't boolean");
        } else {
            return fileConfiguration.getBoolean(path);
        }
    }

    public List<Boolean> getBooleanList(final String path) throws ConfigException {
        if (!fileConfiguration.isSet(path)) {
            throw new ConfigException("'" + path + "' isn't set");
        } else if (!fileConfiguration.isList(path)) {
            throw new ConfigException("'" + path + "' isn't list");
        } else {
            return fileConfiguration.getBooleanList(path);
        }
    }

    public List<Byte> getByteList(final String path) throws ConfigException {
        if (!fileConfiguration.isSet(path)) {
            throw new ConfigException("'" + path + "' isn't set");
        } else if (!fileConfiguration.isList(path)) {
            throw new ConfigException("'" + path + "' isn't list");
        } else {
            return fileConfiguration.getByteList(path);
        }
    }

    public String getColoredString(final String path) throws ConfigException {
        return StringUtil.fixColors(getString(path));
    }

    public List<String> getColoredStringList(final String path) throws ConfigException {
        return StringUtil.fixColors(getStringList(path));
    }

    public ConfigurationSection getConfigurationSection(final String path) throws ConfigException {
        if (!fileConfiguration.isSet(path)) {
            throw new ConfigException("'" + path + "' isn't set");
        } else if (!fileConfiguration.isConfigurationSection(path)) {
            throw new ConfigException("'" + path + "' isn't configuration section");
        } else {
            return fileConfiguration.getConfigurationSection(path);
        }
    }

    public double getDouble(final String path) throws ConfigException {
        if (!fileConfiguration.isSet(path)) {
            throw new ConfigException("'" + path + "' isn't set");
        } else if (!fileConfiguration.isDouble(path) && !fileConfiguration.isInt(path)) {
            throw new ConfigException("'" + path + "' isn't double");
        } else {
            return fileConfiguration.getDouble(path);
        }
    }

    public List<Double> getDoubleList(final String path) throws ConfigException {
        if (!fileConfiguration.isSet(path)) {
            throw new ConfigException("'" + path + "' isn't set");
        } else if (!fileConfiguration.isList(path)) {
            throw new ConfigException("'" + path + "' isn't list");
        } else {
            return fileConfiguration.getDoubleList(path);
        }
    }

    public List<Float> getFloatList(final String path) throws ConfigException {
        if (!fileConfiguration.isSet(path)) {
            throw new ConfigException("'" + path + "' isn't set");
        } else if (!fileConfiguration.isList(path)) {
            throw new ConfigException("'" + path + "' isn't list");
        } else {
            return fileConfiguration.getFloatList(path);
        }
    }

    public int getInt(final String path) throws ConfigException {
        if (!fileConfiguration.isSet(path)) {
            throw new ConfigException("'" + path + "' isn't set");
        } else if (!fileConfiguration.isInt(path)) {
            throw new ConfigException("'" + path + "' isn't int");
        } else {
            return fileConfiguration.getInt(path);
        }
    }

    public List<Integer> getIntegerList(final String path) throws ConfigException {
        if (!fileConfiguration.isSet(path)) {
            throw new ConfigException("'" + path + "' isn't set");
        } else if (!fileConfiguration.isList(path)) {
            throw new ConfigException("'" + path + "' isn't list");
        } else {
            return fileConfiguration.getIntegerList(path);
        }
    }

    public List<ItemStack> getItemStackList(final String path) throws ConfigException {
        if (!fileConfiguration.isSet(path)) {
            throw new ConfigException("'" + path + "' isn't set");
        } else if (!fileConfiguration.isList(path)) {
            throw new ConfigException("'" + path + "' isn't list");
        } else {
            return (List<ItemStack>) fileConfiguration.getList(path);
        }
    }

    public Set<String> getKeys(final String path) throws ConfigException {
        return getConfigurationSection(path).getKeys(false);
    }

    public List<?> getList(final String path) throws ConfigException {
        if (!fileConfiguration.isSet(path)) {
            throw new ConfigException("'" + path + "' isn't set");
        } else if (!fileConfiguration.isList(path)) {
            throw new ConfigException("'" + path + "' isn't list");
        } else {
            return fileConfiguration.getList(path);
        }
    }

    public long getLong(final String path) throws ConfigException {
        if (!fileConfiguration.isSet(path)) {
            throw new ConfigException("'" + path + "' isn't set");
        } else if (!fileConfiguration.isLong(path) && !fileConfiguration.isInt(path)) {
            throw new ConfigException("'" + path + "' isn't long");
        } else {
            return fileConfiguration.getLong(path);
        }
    }

    public List<Long> getLongList(final String path) throws ConfigException {
        if (!fileConfiguration.isSet(path)) {
            throw new ConfigException("'" + path + "' isn't set");
        } else if (!fileConfiguration.isList(path)) {
            throw new ConfigException("'" + path + "' isn't list");
        } else {
            return fileConfiguration.getLongList(path);
        }
    }

    public Material getMaterial(final String path) throws ConfigException {
        final List<Material> list = new ArrayList<>();
        final Object o = get(path);
        if (o instanceof String) {
            return Material.getMaterial((String) o);
        } else if (o instanceof Integer) {
            return Material.getMaterial((Integer) o);
        }
        return null;
    }

    public List<Material> getMaterialList(final String path) throws ConfigException {
        final List<Material> list = new ArrayList<>();
        for (final Object o : getList(path)) {
            if (o instanceof String) {
                list.add(Material.getMaterial((String) o));
            } else if (o instanceof Integer) {
                list.add(Material.getMaterial((Integer) o));
            }
        }
        return list;
    }

    public ConfigurationSection getSafeConfigurationSection(final String path) {
        if (!fileConfiguration.isSet(path)) {
            return null;
        } else if (!fileConfiguration.isConfigurationSection(path)) {
            return null;
        } else {
            return fileConfiguration.getConfigurationSection(path);
        }
    }

    public Set<String> getSafeKeys(final String path) {
        if (!fileConfiguration.isSet(path)) {
            return new HashSet<>();
        } else if (!fileConfiguration.isConfigurationSection(path)) {
            return new HashSet<>();
        } else {
            return fileConfiguration.getConfigurationSection(path).getKeys(false);
        }
    }

    public String getString(final String path) throws ConfigException {
        if (!fileConfiguration.isSet(path)) {
            throw new ConfigException("'" + path + "' isn't set");
        } else if (!fileConfiguration.isString(path)) {
            throw new ConfigException("'" + path + "' isn't string");
        } else {
            return fileConfiguration.getString(path);
        }
    }

    public String getString(final String path, final String def) {
        if (!fileConfiguration.isSet(path)) {
            return def;
        } else if (!fileConfiguration.isString(path)) {
            return def;
        } else {
            return fileConfiguration.getString(path);
        }
    }

    public List<String> getStringList(final String path) throws ConfigException {
        if (!fileConfiguration.isSet(path)) {
            throw new ConfigException("'" + path + "' isn't set");
        } else if (!fileConfiguration.isList(path)) {
            throw new ConfigException("'" + path + "' isn't list");
        } else {
            return fileConfiguration.getStringList(path);
        }
    }

    public boolean isConfigurationSection(final String path) {
        return fileConfiguration.isConfigurationSection(path);
    }

    public boolean isInstanceOf(final String path, final Class<?> c) {
        return fileConfiguration.isSet(path) && c.isInstance(fileConfiguration.get(path));
    }

    public boolean isItemStack(final String path) {
        return fileConfiguration.isSet(path + ".id") && fileConfiguration.isInt(path + ".id");
    }

    public boolean isSet(final String path) {
        return fileConfiguration.isSet(path);
    }

    public ItemStack parseItemStack(final String path) throws ConfigException {
        if (!fileConfiguration.isSet(path + ".id")) {
            throw new ConfigException("'" + path + "' isn't set");
        } else if (!fileConfiguration.isInt(path + ".id")) {
            throw new ConfigException("'" + path + "' isn't itemstack");
        } else {
            try {
                final Material material = Material.getMaterial(fileConfiguration.getInt(path + ".id"));
                if (material == null) {
                    throw new ConfigException("'" + path + "' id is invalid");
                }
                int amount = 1;
                if (fileConfiguration.isSet(path + ".amount") && fileConfiguration.isInt(path + ".amount")) {
                    amount = fileConfiguration.getInt(path + ".amount");
                }
                short data = 0;
                if (fileConfiguration.isSet(path + ".data") && fileConfiguration.isInt(path + ".data")) {
                    data = (short) fileConfiguration.getInt(path + ".data");
                }
                List<String> lore = new ArrayList<>();
                if (fileConfiguration.isSet(path + ".lore") && fileConfiguration.isList(path + ".lore")) {
                    lore = this.getColoredStringList(path + ".lore");
                }
                String name = null;
                if (fileConfiguration.isSet(path + ".name") && fileConfiguration.isString(path + ".name")) {
                    name = this.getColoredString(path + ".name");
                }
                final Map<Enchantment, Integer> enchantments = new HashMap<>();
                if (fileConfiguration.isSet(path + ".enchants") && fileConfiguration.isConfigurationSection(path + ".enchants")) {
                    for (final String s : this.getSafeKeys(path + ".enchants")) {
                        final Enchantment enchantment = Enchantments.getEnchantment(s);
                        if (enchantment == null) continue;
                        enchantments.put(enchantment, this.getInt(path + ".enchants." + s));
                    }
                }

                return new ItemBuilder(material).setAmount(amount).setDurability(data).setLore(lore).setName(name).addUnsafeEnchantments(enchantments).toItemStack();
            } catch (final Exception ignore) {
                throw new ConfigException("'" + path + "' isn't itemstack");
            }
        }
    }

    /*public long getTimeMillis(final String path) throws ConfigException {
        if (!fileConfiguration.isSet(path)) {
            throw new ConfigException("'" + path + "' isn't set");
        } else if (!fileConfiguration.isInt(path) && !fileConfiguration.isString(path)) {
            throw new ConfigException("'" + path + "' isn't time");
        } else {
            try {
                return DateUtil.formatToMillis(fileConfiguration.getString(path));
            } catch (final Exception ignore) {
                throw new ConfigException("'" + path + "' isn't time");
            }
        }
    }

    public long getTimeTicks(final String path) throws ConfigException {
        if (!fileConfiguration.isSet(path)) {
            throw new ConfigException("'" + path + "' isn't set");
        } else if (!fileConfiguration.isString(path)) {
            throw new ConfigException("'" + path + "' isn't time");
        } else {
            try {
                return DateUtil.formatToMillis(fileConfiguration.getString(path)) / 50;
            } catch (final Exception ignore) {
                throw new ConfigException("'" + path + "' isn't time");
            }
        }
    }

    public long getTimeSecounds(final String path) throws ConfigException {
        if (!fileConfiguration.isSet(path)) {
            throw new ConfigException("'" + path + "' isn't set");
        } else if (!fileConfiguration.isString(path)) {
            throw new ConfigException("'" + path + "' isn't time");
        } else {
            try {
                return DateUtil.formatToMillis(fileConfiguration.getString(path)) / 1000;
            } catch (final Exception ignore) {
                throw new ConfigException("'" + path + "' isn't time");
            }
        }
    }*/

    public void reload() {
        try {
            fileConfiguration.load(file);
        } catch (final IOException e) {
            e.printStackTrace();
        } catch (final InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            fileConfiguration.save(file);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public void serializeLocation(final String path, final Location location) {
        fileConfiguration.set(path + ".world", location.getWorld().getName());
        fileConfiguration.set(path + ".x", location.getX());
        fileConfiguration.set(path + ".y", location.getY());
        fileConfiguration.set(path + ".z", location.getZ());
        fileConfiguration.set(path + ".yaw", location.getYaw());
        fileConfiguration.set(path + ".pitch", location.getPitch());
    }

    public void set(final String path, final Object o) {
        fileConfiguration.set(path, o);
    }

    /*public List<ItemStack> parseItemStackList(final String path) throws ConfigException {
        if (!fileConfiguration.isSet(path)) {
            throw new ConfigException("'" + path + "' isn't set");
        } else if (!fileConfiguration.isList(path)) {
            throw new ConfigException("'" + path + "' isn't list");
        } else {
            final List<ItemStack> items = new ArrayList<>();
            for (final String item : fileConfiguration.getStringList(path)) {
                try {
                    items.add(Parsers.parseItemStack(item));
                } catch (final Exception ignore) {
                    throw new ConfigException("'" + path + "' isn't itemstack");
                }
            }
            return items;
        }
    }*/
}
