package com.blank04.events;

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class EventJoin extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        if (!event.getGuild().getId().equals("704061102505328651")) return;
        Objects.requireNonNull(event.getGuild().getTextChannelById("759117083648458772")).sendMessage("Вітаю на сервері, " + event.getMember().getAsMention() + "!").queue();
    }
}
