package me.viiral.reclaim.reclaim;

import me.viiral.reclaim.ReclaimPlugin;
import me.viiral.reclaim.util.Config;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class ReclaimHandler {

    protected final Config config;

    protected final Set<Reclaim> reclaims = new HashSet<>();

    protected final Set<UUID> reclaimed = new HashSet<>();

    public ReclaimHandler(ReclaimPlugin instance) {
        this.config = new Config(instance, "reclaims");
        this.load();
    }

    public Optional<Reclaim> getReclaim(String name) {
        for (Reclaim reclaim : this.reclaims) {
            if (reclaim.getRankName().equalsIgnoreCase(name)) {
                return Optional.of(reclaim);
            }
        }
        return Optional.empty();
    }

    public boolean hasReclaimed(UUID player) {
        return this.reclaimed.contains(player);
    }

    public void resetReclaim(UUID player) {
        this.config.set("reclaimed." + player.toString(), null);
        this.config.save();

        this.reclaimed.remove(player);
    }

    public void setReclaimed(UUID player) {
        this.config.set("reclaimed." + player.toString(), 0);
        this.config.save();

        this.reclaimed.add(player);
    }

    public void load() {
        ConfigurationSection section = this.config.getConfigurationSection("reclaims");

        if (section != null) {
            for (String key : section.getKeys(false)) {
                reclaims.add(new Reclaim(key, section.getStringList(key + ".commands")));
            }
        }

        section = this.config.getConfigurationSection("reclaimed");

        if (section != null) {
            for (String key : section.getKeys(false)) {
                this.reclaimed.add(UUID.fromString(key));
            }
        }
    }
}
