package me.viiral.reclaim.util;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

@UtilityClass
public class CC {

    public String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}
