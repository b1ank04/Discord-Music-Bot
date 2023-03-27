package com.blank04.commands.music;

import com.blank04.jdacommands.ICommand;
import com.blank04.lavaplayer.GuildMusicManager;
import com.blank04.lavaplayer.PlayerManager;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Objects;

public class CmdSkip implements ICommand {

    @Override
    public String getName() {
        return "skip";
    }

    @Override
    public void execute(MessageReceivedEvent messageReceivedEvent) {
        final TextChannel channel = messageReceivedEvent.getChannel().asTextChannel();
        final Member self = messageReceivedEvent.getGuild().getMemberById("1057265313592389643");
        final GuildVoiceState selfVoiceState = Objects.requireNonNull(self).getVoiceState();
        if(!Objects.requireNonNull(selfVoiceState).inAudioChannel()){
            channel.sendMessage("Бот повинен бути у голосовому каналі").queue();
            return;
        }
        final Member member = messageReceivedEvent.getMember();
        final GuildVoiceState memberVoiceState = Objects.requireNonNull(member).getVoiceState();
        if(!Objects.requireNonNull(memberVoiceState).inAudioChannel()){
            channel.sendMessage("Треба зайти до голосового каналу").queue();
            return;
        }

        if(!Objects.equals(memberVoiceState.getChannel(), selfVoiceState.getChannel())){
            channel.sendMessage("Треба бути в одному голосовому каналі з ботом").queue();
            return;
        }
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(messageReceivedEvent.getGuild());
        final AudioPlayer audioPlayer = musicManager.audioPlayer;

        if (audioPlayer.getPlayingTrack() == null) {
            channel.sendMessage("Немає треків для пропуску").queue();
            return;
        }
        musicManager.scheduler.nextTrack();
        channel.sendMessage("Трек пропущено. Зараз грає **`" + musicManager.audioPlayer.getPlayingTrack().getInfo().title + "`** від **`" + musicManager.audioPlayer.getPlayingTrack().getInfo().author + "`**.").queue();
    }
}
