package me.viiral.reclaim.reclaim;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Reclaim {

    private final String rankName;

    private final List<String> commands;

    public Reclaim(String rankName, List<String> commands) {
        this.rankName = rankName;
        this.commands = commands;
    }
}
