package org.bukkit.craftbukkit.block;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.TileEntitySign;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.util.BungeeChatUtils;

public class CraftSign extends CraftBlockEntityState<TileEntitySign> implements Sign {

    private BaseComponent[] lines = new BaseComponent[4];

    public CraftSign(final Block block) {
        super(block, TileEntitySign.class);
        importFromNms();
    }

    public CraftSign(final Material material, final TileEntitySign te) {
        super(material, te);
        importFromNms();
    }

    private void importFromNms() {
        lines = new BaseComponent[4];
        for(int i = 0; i < lines.length; i++) {
            lines[i] = getTileEntity().lines.length > i ? BungeeChatUtils.toBungee(getTileEntity().lines[i])
                    : new TextComponent();
        }
    }

    @Override
    public void load(TileEntitySign sign) {
        super.load(sign);

        importFromNms();
    }

    @Override
    public BaseComponent[] lines() {
        return lines;
    }

    @Override
    public BaseComponent line(int index) {
        return lines[index];
    }

    @Override
    public void setLine(int index, BaseComponent line) {
        lines[index] = line;
    }

    @Override
    public String[] getLines() {
        return BaseComponent.toLegacyArray(lines);
    }

    @Override
    public String getLine(int index) throws IndexOutOfBoundsException {
        return lines[index].toLegacyText();
    }

    @Override
    public void setLine(int index, String line) throws IndexOutOfBoundsException {
        lines[index] = TextComponent.fromLegacyToComponent(line, false);
    }

}
