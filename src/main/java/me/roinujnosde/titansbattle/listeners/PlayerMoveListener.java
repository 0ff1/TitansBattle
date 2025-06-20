package me.roinujnosde.titansbattle.listeners;

import me.roinujnosde.titansbattle.BaseGame;
import me.roinujnosde.titansbattle.TitansBattle;
import me.roinujnosde.titansbattle.types.Warrior;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerMoveListener extends TBListener {
    public PlayerMoveListener(@NotNull TitansBattle plugin) {
        super(plugin);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Location from = event.getFrom();
        Location to = event.getTo();

        if (from.getX() == to.getX() && from.getY() == to.getY() && from.getZ() == to.getZ())
            return;

        var player = event.getPlayer();

        BaseGame game = plugin.getBaseGameFrom(player);

        if (game == null || !game.inPreparation())
            return;

        Warrior warrior = plugin.getDatabaseManager().getWarrior(player);

        if (!game.getConfig().getFreezePlayer() || !game.isInBattle(warrior))
            return;

        to.setX(from.getX());
        to.setY(from.getY());
        to.setZ(from.getZ());
    }
}
