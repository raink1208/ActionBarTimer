package com.github.rain1208.timer

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.util.StringUtil

object TimerCommand:CommandExecutor, TabCompleter {
    private val commands = arrayListOf("set", "stop")

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String> {
        val completions = arrayListOf<String>()
        if (command.name != "timer") return completions
        if (args.size > 1) return completions
        StringUtil.copyPartialMatches(args[0],commands,completions)
        completions.sort()
        return completions
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) return true
        if (args[0].isEmpty() || args[0] == "") return true
        when (args[0]) {
            "set" -> {
                if (args[1].isNotEmpty()) {
                    if (args[1].toIntOrNull() != null) Timer.instance.createTimer(args[1].toInt())
                    if (args[1].contains(':')) {
                        val time = args[1].split(':').map { it.toIntOrNull() }
                        if (time.contains(null)) {
                            sender.sendMessage("数値以外が含まれています")
                            return true
                        }
                        Timer.instance.createTimer(time[0]!!*60+ time[1]!!)
                    }
                }
            }
            "stop" -> Timer.instance.removeTimer()
            else -> return true
        }
        return true
    }
}