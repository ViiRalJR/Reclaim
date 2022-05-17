package me.viiral.reclaim.command;

import io.github.scifi9902.fethmusmioma.annotation.Command;
import io.github.scifi9902.fethmusmioma.annotation.Parameter;
import io.github.scifi9902.fethmusmioma.util.UUIDUtil;
import me.viiral.reclaim.ReclaimPlugin;
import me.viiral.reclaim.util.CC;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

public class ResetReclaimCommand {

    private final ReclaimPlugin instance;

    public ResetReclaimCommand(ReclaimPlugin instance) {
        this.instance = instance;
    }

    @Command(label = "resetreclaim", permission = "reclaims.command.resetreclaim")
    public void execute(CommandSender sender, @Parameter(name = "player") String playerName) {
        OfflinePlayer offlinePlayer = UUIDUtil.getOfflinePlayer(playerName);

        if (offlinePlayer == null || !offlinePlayer.hasPlayedBefore()) {
            sender.sendMessage(CC.chat("&cThat player has never joined the server."));
            return;
        }

        if (!this.instance.getReclaimHandler().hasReclaimed(offlinePlayer.getUniqueId())) {
            sender.sendMessage(CC.chat("&cThat player has not reclaimed."));
            return;
        }

        this.instance.getReclaimHandler().resetReclaim(offlinePlayer.getUniqueId());
        sender.sendMessage(CC.chat("&eYou have reset the players reclaim."));
    }

}
