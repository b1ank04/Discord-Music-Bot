package com.blank04.jdacommands;

import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class JDACommands extends ListenerAdapter {
    private List<ICommand> commands = new ArrayList<>();
    private final String prefix;

    public JDACommands(String prefix) {
        this.prefix = prefix;
    }

    public List<ICommand> getCommands() {
        return this.commands;
    }

    public void setCommands(List<ICommand> commands) {
        this.commands = commands;
    }

    public void registerCommand(ICommand command) {
        this.commands.add(command);
    }

    private void init(MessageReceivedEvent event) {

        for (ICommand command : this.commands) {
            String var10000 = event.getMessage().getContentRaw();
            if (var10000.startsWith(this.prefix + command.getName())) {
                command.execute(event);
                break;
            }
        }

    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        if (event.getMessage().getContentRaw().startsWith(this.prefix)) {
            this.init(event);
        }

    }
}
