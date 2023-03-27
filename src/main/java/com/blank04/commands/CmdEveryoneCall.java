package com.blank04.commands;

import com.blank04.jdacommands.ICommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CmdEveryoneCall implements ICommand {


    @Override
    public String getName() {
        return "збір";
    }


    @Override
    public void execute(@NotNull MessageReceivedEvent messageReceivedEvent) {
        messageReceivedEvent.getChannel().sendMessage("@everyone:  Збираємось у "+ Objects.requireNonNull(Objects.requireNonNull(Objects.requireNonNull(messageReceivedEvent.getMember()).getVoiceState()).getChannel()).getAsMention()).queue();
    }
}
