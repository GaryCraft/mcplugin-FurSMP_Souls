package xyz.fursmp.souls

import org.bukkit.plugin.java.JavaPlugin
import xyz.fursmp.souls.commands.GetCustomItem
import xyz.fursmp.souls.commands.SpawnMob
import xyz.fursmp.souls.events.CustomMechanics
import xyz.fursmp.souls.events.SpawnCustomMobs
import xyz.fursmp.souls.runners.CleanItemHolders
import xyz.fursmp.souls.runners.ItemHolderUpdatePosition
import xyz.fursmp.souls.utils.Loggers.consoleLog

class SoulsPlugin : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        // Register commands
        server.getPluginCommand("spawnmob")?.setExecutor(SpawnMob())
        server.getPluginCommand("getCustomItem")?.setExecutor(GetCustomItem())
        // Register events
        server.pluginManager.registerEvents(SpawnCustomMobs(), this)
        server.pluginManager.registerEvents(CustomMechanics(), this)
        //Register Runnables
        ItemHolderUpdatePosition().runTaskTimer(this, 20, 1)
        CleanItemHolders().runTaskTimer(this, 20, 10)
        consoleLog("Plugin enabled!")

    }

    override fun onDisable() {
        // Plugin shutdown logic
        server.scheduler.cancelTasks(this)
        consoleLog("Plugin disabled!")
    }
}