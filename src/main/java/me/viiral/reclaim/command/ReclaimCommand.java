package me.viiral.reclaim.command;

import io.github.scifi9902.fethmusmioma.annotation.Command;
import me.viiral.reclaim.ReclaimPlugin;
import me.viiral.reclaim.reclaim.Reclaim;
import me.viiral.reclaim.util.CC;
import org.bukkit.entity.Player;

import java.util.Optional;

public class ReclaimCommand {

    private final ReclaimPlugin instance;

    public ReclaimCommand(ReclaimPlugin instance) {
        this.instance = instance;
    }

    @Command(label = "reclaim")
    public void execute(Player player) {
        Optional<Reclaim> reclaim = this.instance.getReclaimHandler().getReclaim(this.instance.getChat().getPrimaryGroup(player));

        if (!reclaim.isPresent()) {
            player.sendMessage(CC.chat("&cYour rank does not have a reclaim setup for it."));
            return;
        }

        if (this.instance.getReclaimHandler().hasReclaimed(player.getUniqueId())) {
            player.sendMessage(CC.chat("&cYou have already reclaimed your map perks!"));
            return;
        }

        for (String string : reclaim.get().getCommands()) {
            this.instance.getServer().dispatchCommand(this.instance.getServer().getConsoleSender(), string.replace("%player%", player.getName()));
        }

        this.instance.getReclaimHandler().setReclaimed(player.getUniqueId());
        player.sendMessage(CC.chat("&eYou have reclaimed your map perks!"));
    }

}
