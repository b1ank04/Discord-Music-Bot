package com.blank04.commands.music;

import com.blank04.jdacommands.ICommand;
import com.blank04.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CmdPause implements ICommand {
    @Override
    public String getName() {
        return "pause";
    }

    @Override
    public void execute(MessageReceivedEvent messageReceivedEvent) {
        PlayerManager.getInstance().getMusicManager(messageReceivedEvent.getGuild()).scheduler.audioPlayer.setPaused(!PlayerManager.getInstance().getMusicManager(messageReceivedEvent.getGuild()).scheduler.audioPlayer.isPaused());
    }
}
