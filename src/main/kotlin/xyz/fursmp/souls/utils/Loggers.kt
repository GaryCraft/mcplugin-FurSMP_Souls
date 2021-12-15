package xyz.fursmp.souls.utils

import org.bukkit.Bukkit

object Loggers{
    private val PREFIX = "[FurSMP Souls]§r"

    fun consoleLog(message: String) {
        Bukkit.getConsoleSender().sendMessage("$PREFIX $message")
    }
    fun consoleWarning(message: String) {
        Bukkit.getLogger().warning("$PREFIX $message")
    }
    fun consoleError(message: String) {
        Bukkit.getLogger().severe("$PREFIX $message")
    }

}