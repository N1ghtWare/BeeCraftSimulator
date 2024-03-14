package pl.nightware.beecraftsimulator.misc;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class MutationGainFormula {
    public static void execute(LevelAccessor world, Entity entity) {
        if (entity == null)
            return;
        String attack_damage = "";
        String attack_speed = "";
        String max_health = "";
        String movement_speed = "";
        String max_health_command = "";
        String movement_speed_command = "";
        String attack_damage_command = "";
        String attack_speed_command = "";
        {
            Entity _ent = entity;
            if (!_ent.level().isClientSide() && _ent.getServer() != null) {
                _ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
                        _ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "attribute @s minecraft:generic.attack_damage modifier remove 3-8-2-1-7");
            }
        }
        {
            Entity _ent = entity;
            if (!_ent.level().isClientSide() && _ent.getServer() != null) {
                _ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
                        _ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "attribute @s minecraft:generic.attack_speed modifier remove 3-8-2-1-7");
            }
        }
        {
            Entity _ent = entity;
            if (!_ent.level().isClientSide() && _ent.getServer() != null) {
                _ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
                        _ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "attribute @s minecraft:generic.max_health modifier remove 3-8-2-1-7");
            }
        }
        {
            Entity _ent = entity;
            if (!_ent.level().isClientSide() && _ent.getServer() != null) {
                _ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
                        _ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "attribute @s minecraft:generic.movement_speed modifier remove 3-8-2-1-7");
            }
        }
        if (Math.random() <= 0.125) {
            max_health = new java.text.DecimalFormat("##.##").format(Mth.nextInt(RandomSource.create(), 1, 16));
            max_health_command = "attribute @s minecraft:generic.max_health modifier add 3-8-2-1-7 Mutation (max health) add".replace("(max health)", max_health);
            {
                Entity _ent = entity;
                if (!_ent.level().isClientSide() && _ent.getServer() != null) {
                    _ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
                            _ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), max_health_command);
                }
            }
            if (!world.isClientSide() && world.getServer() != null)
                world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("You gained mutation: +(max health) Max Health".replace("(max health)", max_health))), false);
        } else if (Math.random() <= 0.225) {
            attack_damage = new java.text.DecimalFormat("##.##").format(Mth.nextInt(RandomSource.create(), 1, 8));
            attack_damage_command = "attribute @s minecraft:generic.attack_damage modifier add 3-8-2-1-7 Mutation (attack damage) add".replace("(attack damage)", attack_damage);
            {
                Entity _ent = entity;
                if (!_ent.level().isClientSide() && _ent.getServer() != null) {
                    _ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
                            _ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), attack_damage_command);
                }
            }
            if (!world.isClientSide() && world.getServer() != null)
                world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("You gained mutation: +(attack damage) Attack Damage".replace("(attack damage)", attack_damage))), false);
        } else if (Math.random() <= 0.375) {
            attack_speed = new java.text.DecimalFormat("##.##").format(Mth.nextInt(RandomSource.create(), 1, 3));
            attack_speed_command = "attribute @s minecraft:generic.attack_speed modifier add 3-8-2-1-7 Mutation (attack speed) add".replace("(attack speed)", attack_speed);
            {
                Entity _ent = entity;
                if (!_ent.level().isClientSide() && _ent.getServer() != null) {
                    _ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
                            _ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), attack_speed_command);
                }
            }
            if (!world.isClientSide() && world.getServer() != null)
                world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("You gained mutation: +(attack speed) Attack Speed".replace("(attack speed)", attack_speed))), false);
        } else {
            movement_speed = new java.text.DecimalFormat("##.##").format(Mth.nextDouble(RandomSource.create(), 0.01, 0.05));
            movement_speed_command = "attribute @s minecraft:generic.movement_speed modifier add 3-8-2-1-7 Mutation (movement speed) add".replace("(movement speed)", movement_speed);
            {
                Entity _ent = entity;
                if (!_ent.level().isClientSide() && _ent.getServer() != null) {
                    _ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
                            _ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), movement_speed_command);
                }
            }
            if (!world.isClientSide() && world.getServer() != null)
                world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("You gained mutation: +(movement speed) Movement Speed".replace("(movement speed)", movement_speed))), false);
        }
    }
}

