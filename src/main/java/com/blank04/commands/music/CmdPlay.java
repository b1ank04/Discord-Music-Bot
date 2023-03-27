package com.blank04.commands.music;

import com.blank04.jdacommands.ICommand;
import com.blank04.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import org.apache.commons.validator.routines.UrlValidator;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CmdPlay implements ICommand {

    private static TextChannel textChannel;
    @Override
    public String getName() {
        return "play";
    }

    @Override
    public void execute(@NotNull MessageReceivedEvent messageReceivedEvent) {
        if (!Objects.requireNonNull(Objects.requireNonNull(messageReceivedEvent.getMember()).getVoiceState()).inAudioChannel()) {
            messageReceivedEvent.getChannel().asTextChannel().sendMessage("Треба бути у голосовому каналі").queue();
            return;
        }
        else {
            final AudioManager audioManager = messageReceivedEvent.getGuild().getAudioManager();
            final VoiceChannel memberChannel = (VoiceChannel) messageReceivedEvent.getMember().getVoiceState().getChannel();
            audioManager.openAudioConnection(memberChannel);
        }
        textChannel = messageReceivedEvent.getChannel().asTextChannel();
        String link = messageReceivedEvent.getMessage().getContentDisplay();
        link = link.substring(link.indexOf(" ")+1);
        System.out.println(link);
        System.out.println(link.length());
        if (link.replace(" ","").equals("!play")) {
            PlayerManager.getInstance().getMusicManager(messageReceivedEvent.getGuild()).scheduler.audioPlayer.setPaused(false);
            messageReceivedEvent.getChannel().asTextChannel().sendMessage("Програвання відновлено").queue();
            return;
        }
        if (! UrlValidator.getInstance().isValid(link)) {
            link = "ytsearch: " + link + " audio";
        }
        PlayerManager.getInstance().loadAndPlay(messageReceivedEvent.getChannel().asTextChannel(), link);
        System.out.println(UrlValidator.getInstance().isValid(link));
        System.out.println(link);
    }
    public static TextChannel getTextChannel() {
        return textChannel;
    }
}
