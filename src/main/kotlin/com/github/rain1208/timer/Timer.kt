package com.github.rain1208.timer

import org.bukkit.plugin.java.JavaPlugin

class Timer : JavaPlugin() {
    companion object {
        lateinit var instance:Timer
        private set
    }

    var timer:ActionBarTimer? = null

    override fun onEnable() {
        instance = this

        getCommand("timer")?.setExecutor(TimerCommand)
                ?: logger.info("timerコマンドの読み込みに失敗しました")
    }

    fun createTimer(time:Int) {
        removeTimer()
        timer = ActionBarTimer(time)
        timerStart()
    }

    fun timerStart() {
        timer?.runTaskTimer(this,0,20)
    }

    fun removeTimer() {
        timer?.remove()
        timer = null
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}