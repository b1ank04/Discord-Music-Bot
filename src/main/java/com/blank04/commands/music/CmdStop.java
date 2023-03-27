package com.blank04.commands.music;

import com.blank04.jdacommands.ICommand;
import com.blank04.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Objects;

public class CmdStop implements ICommand {

    @Override
    public String getName() {
        return "stop";
    }

    @Override
    public void execute(MessageReceivedEvent messageReceivedEvent) {
        String botId = "1057265313592389643";
        if (!Objects.requireNonNull(Objects.requireNonNull(messageReceivedEvent.getMember()).getVoiceState()).inAudioChannel()) {
            messageReceivedEvent.getChannel().asTextChannel().sendMessage("Треба бути у голосовому каналі").queue();
            return;
        }

        if (!Objects.requireNonNull(Objects.requireNonNull(messageReceivedEvent.getGuild().getMemberById(botId)).getVoiceState()).inAudioChannel()
                ||
            !Objects.equals(Objects.requireNonNull(Objects.requireNonNull(messageReceivedEvent.getGuild().getMemberById(botId)).getVoiceState()).getChannel(), messageReceivedEvent.getMember().getVoiceState().getChannel())
            ) {
            messageReceivedEvent.getChannel().asTextChannel().sendMessage("Бот повинен бути у каналі").queue();
            return;
        }
        if (Objects.equals(messageReceivedEvent.getMember().getVoiceState().getChannel(), Objects.requireNonNull(Objects.requireNonNull(messageReceivedEvent.getGuild().getMemberById("1057265313592389643")).getVoiceState()).getChannel())) {
            PlayerManager.getInstance().getMusicManager(messageReceivedEvent.getGuild()).scheduler.audioPlayer.stopTrack();
            PlayerManager.getInstance().getMusicManager(messageReceivedEvent.getGuild()).scheduler.queue.clear();
            messageReceivedEvent.getGuild().getAudioManager().closeAudioConnection();
            messageReceivedEvent.getChannel().asTextChannel().sendMessage("Плеєр був зупинений та черга очищена").queue();
        }
    }
}
