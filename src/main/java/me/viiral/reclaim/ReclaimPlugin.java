package me.viiral.reclaim;

import io.github.scifi9902.fethmusmioma.CommandHandler;
import lombok.Getter;
import me.viiral.reclaim.command.ReclaimCommand;
import me.viiral.reclaim.command.ResetReclaimCommand;
import me.viiral.reclaim.reclaim.ReclaimHandler;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class ReclaimPlugin extends JavaPlugin {

    private ReclaimHandler reclaimHandler;

    private Chat chat;

    public void onEnable() {
        this.reclaimHandler = new ReclaimHandler(this);

        this.chat = this.getServer().getServicesManager().getRegistration(Chat.class).getProvider();

        CommandHandler commandHandler = new CommandHandler(this, "reclaims");

        commandHandler.registerCommand(new ReclaimCommand(this));
        commandHandler.registerCommand(new ResetReclaimCommand(this));


    }

}
