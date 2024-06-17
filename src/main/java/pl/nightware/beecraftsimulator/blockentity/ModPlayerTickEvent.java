package pl.nightware.beecraftsimulator.blockentity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;

public class ModPlayerTickEvent extends TickEvent.PlayerTickEvent {
    public ModPlayerTickEvent(Phase phase, Player player) {
        super(phase, player);

        player.sendSystemMessage(Component.literal("Player ticked!"));
    }
}
