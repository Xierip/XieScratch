package pl.xierip.xiescratch.objects;

import lombok.Getter;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.xierip.xiescratch.configuration.MainConfig;
import pl.xierip.xiescratch.utils.ItemStackUtil;
import pl.xierip.xiescratch.utils.StringUtil;

/**
 * Created by xierip on 28.03.17.
 * Web: http://xierip.pl
 */
public class ItemReward {
    @Getter
    private final double chance;
    @Getter
    private final ItemStack drop, display;
    @Getter
    private FireworkEffect fireworkEffect;

    public ItemReward(final double chance, final ItemStack drop) {
        this.chance = chance;
        this.drop = drop;
        this.display = drop.clone();
        final ItemMeta itemMeta = this.display.getItemMeta();
        itemMeta.setLore(StringUtil.replace(MainConfig.getSubDisplayLore(), "{CHANCE}", String.valueOf(chance)));
        this.display.setItemMeta(itemMeta);
    }

    public void execute(final Player player) {
        ItemStackUtil.giveOrDrop(player, player.getLocation(), drop.clone());
    }

    public void setColor(Color color) {
        if (color != null) {
            fireworkEffect = FireworkEffect.builder().trail(false).flicker(false).withColor(color).withFade(color).with(FireworkEffect.Type.BALL).build();
        }
    }

}
