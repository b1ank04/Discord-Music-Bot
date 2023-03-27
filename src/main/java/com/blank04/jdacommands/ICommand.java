package com.blank04.jdacommands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface ICommand {
    String getName();

    void execute(MessageReceivedEvent var1);
}
