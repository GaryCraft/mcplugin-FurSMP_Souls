package xyz.fursmp.souls.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.fursmp.souls.utils.BuildItem
import xyz.fursmp.souls.utils.Loggers

class GetCustomItem:CommandExecutor {
	override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {


		if(sender !is Player) {
			Loggers.consoleLog("You must be a player to use this command!")
			return true
		}
		if(!sender.isOp() && !sender.hasPermission("fursmp.souls.commands.getcustomitem")) {
			sender.sendMessage("You do not have permission to use this command!")
			return true
		}
		if(args.isEmpty()) return false
		val item = BuildItem(args[0].toInt()).getItem()

		sender.inventory.addItem(item)



		return true
	}
}