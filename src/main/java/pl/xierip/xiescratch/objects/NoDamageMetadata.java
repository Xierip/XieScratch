package pl.xierip.xiescratch.objects;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import pl.xierip.xiescratch.XieScratch;

/**
 * Created by xierip on 25.07.17.
 * Web: http://xierip.pl
 */
public class NoDamageMetadata implements MetadataValue {
    @Override
    public boolean asBoolean() {
        return true;
    }

    @Override
    public byte asByte() {
        return 1;
    }

    @Override
    public double asDouble() {
        return 1;
    }

    @Override
    public float asFloat() {
        return 1;
    }

    @Override
    public int asInt() {
        return 1;
    }

    @Override
    public long asLong() {
        return 1;
    }

    @Override
    public short asShort() {
        return 1;
    }

    @Override
    public String asString() {
        return "true";
    }

    @Override
    public Plugin getOwningPlugin() {
        return XieScratch.getInstance();
    }

    @Override
    public void invalidate() {

    }

    @Override
    public Object value() {
        return true;
    }
}
