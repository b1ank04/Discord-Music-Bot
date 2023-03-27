package com.blank04;

import com.blank04.jdacommands.*;
import com.blank04.commands.CmdEveryoneCall;
import com.blank04.commands.music.CmdPause;
import com.blank04.commands.music.CmdPlay;
import com.blank04.commands.music.CmdSkip;
import com.blank04.commands.music.CmdStop;
import com.blank04.events.EventJoin;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import java.util.Arrays;

public class Bot {

    protected static final GatewayIntent[] INTENTS = GatewayIntent.values();

    public static void main(String[] args){
        JDACommands  jdaCommands = new JDACommands("!");
        jdaCommands.registerCommand(new CmdEveryoneCall());
        jdaCommands.registerCommand(new CmdPlay());
        jdaCommands.registerCommand(new CmdStop());
        jdaCommands.registerCommand(new CmdPause());
        jdaCommands.registerCommand(new CmdSkip());
        JDA jda = JDABuilder.create("", Arrays.asList(INTENTS)) // pass bot token
                .enableCache(CacheFlag.VOICE_STATE)
                .setActivity(Activity.playing("Слава Україні!"))
                .setStatus(OnlineStatus.ONLINE)
                .addEventListeners(new EventJoin())
                .addEventListeners(jdaCommands)
                .build();
    }
}
