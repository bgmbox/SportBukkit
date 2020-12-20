package org.bukkit.craftbukkit.util;

import net.minecraft.server.MinecraftKey;
import org.bukkit.NamespacedKey;

public final class CraftNamespacedKey {

    public CraftNamespacedKey() {
    }

    public static NamespacedKey fromString(String string) {
        return fromMinecraft(new MinecraftKey(string));
    }

    public static NamespacedKey fromMinecraft(MinecraftKey minecraft) {
        return new NamespacedKey(minecraft.b(), minecraft.getKey());
    }

    public static MinecraftKey toMinecraft(NamespacedKey key) {
        return new MinecraftKey(key.getNamespace(), key.getKey());
    }
}
