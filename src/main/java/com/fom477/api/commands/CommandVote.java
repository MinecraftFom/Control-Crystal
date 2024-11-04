package com.fom477.api.commands;

import com.fom477.api.logic.CCFunctions;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.chat.Component;


public class CommandVote {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher)
    {
        dispatcher.register(Commands.literal("vote").then(Commands.argument("target", EntityArgument.player())).executes(context -> {
            Player target = EntityArgument.getPlayer(context, "target");
            CCFunctions ccFunctions = null;
            Component message = Component.literal(Component.translatable("cc.commands.vote.error.player_is_active").toString());
            Component message2 = Component.literal(Component.translatable("cc.commands.vote.error.player_not_found").toString());
            if (!ccFunctions.isPlayerActivated(target)) {
                throw new CommandSyntaxException(new SimpleCommandExceptionType(message), message);
            }
            if (!ccFunctions.isPlayerPlaying(target)) {
                throw new CommandSyntaxException(new SimpleCommandExceptionType(message2), message2);
            }
            ccFunctions.votePlayer(target);
            return Command.SINGLE_SUCCESS;
        }));
    };
}
