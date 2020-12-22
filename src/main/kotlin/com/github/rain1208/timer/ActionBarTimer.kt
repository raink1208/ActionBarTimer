package com.github.rain1208.timer

import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class ActionBarTimer(var time:Int = 0):BukkitRunnable() {
    val players = mutableListOf<Player>()

    init {
        players.addAll(Timer.instance.server.onlinePlayers)
    }

    override fun run() {
        val text = TextComponent("残り時間 : ${getTimeText()}")
        for (player in players) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, text)
        }
        if (time <= 0) remove() else time--
    }

    fun getTimeText():String {
        var text = ""
        text += if (time/60 < 10) "0${time/60}" else "${time/60}"
        text += ":"
        text += if (time%60 < 10) "0${time%60}" else "${time%60}"
        return text
    }

    fun remove() {
        if (!isCancelled) cancel()
    }
}