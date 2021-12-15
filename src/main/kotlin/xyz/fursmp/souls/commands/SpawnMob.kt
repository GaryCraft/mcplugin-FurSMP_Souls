package xyz.fursmp.souls.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.fursmp.souls.mobs.BabyGhast
import xyz.fursmp.souls.mobs.SoulsMob
import xyz.fursmp.souls.utils.CustomEntity
import xyz.fursmp.souls.utils.Loggers.consoleLog
import xyz.fursmp.souls.utils.Loggers.consoleWarning

class SpawnMob : CommandExecutor {

	override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {


		if(sender !is Player) {
			consoleLog("You must be a player to use this command!")
			return true
		}
		if(!sender.isOp() && !sender.hasPermission("fursmp.souls.commands.spawnmob")) {
			sender.sendMessage("You do not have permission to use this command!")
			return true
		}
		if(args.isEmpty()) return false

		val mob = findMob(args[0])
		if(mob == null) {
			sender.sendMessage("Unknown mob type!")
			return true
		}
		if(mob is SoulsMob) {
			val entity = mob.entity as CustomEntity
			try {
				entity.spawnAt(sender.location.world!!, sender.location)

			} catch (e: Exception) {
				println(e)
				consoleWarning("Failed to spawn mob ${args[0]}!")
			}
		}


		return true
	}
	private fun findMob(mob: String): Any? {
		return when(mob) {
			"babyghast" -> BabyGhast()
			else -> null
		}
	}
}