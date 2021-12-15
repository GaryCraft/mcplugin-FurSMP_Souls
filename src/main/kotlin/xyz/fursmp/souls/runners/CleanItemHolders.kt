package xyz.fursmp.souls.runners

import org.bukkit.Bukkit
import org.bukkit.entity.ArmorStand
import org.bukkit.scheduler.BukkitRunnable




class CleanItemHolders: BukkitRunnable() {
	override fun run() {

		val worlds = Bukkit.getWorlds()
		for (world in worlds) {
			if (world == null) continue
			val itemHolders = world.getEntitiesByClass(ArmorStand::class.java)
			for (itemHolder in itemHolders) {
				if (itemHolder == null) continue
				if (!itemHolder.isInsideVehicle) {
					itemHolder.remove()
				}
			}
			val customMobs = world.entities
			for (mob in customMobs) {
				if(mob == null) continue
				if(mob.hasMetadata("isCustomMob")) {
					if (mob.passengers.isEmpty()) {
						mob.remove()
					}
				}
			}
		}
	}
}