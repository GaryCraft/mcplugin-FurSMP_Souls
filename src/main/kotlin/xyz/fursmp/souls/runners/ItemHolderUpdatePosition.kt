package xyz.fursmp.souls.runners

import org.bukkit.Bukkit
import org.bukkit.entity.ArmorStand
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.util.EulerAngle


class ItemHolderUpdatePosition: BukkitRunnable() {
	override fun run() {

		val worlds = Bukkit.getWorlds()
		for (world in worlds) {
			if (world == null) continue
			val itemHolders = world.getEntitiesByClass(ArmorStand::class.java)
			for (itemHolder in itemHolders) {
				if (itemHolder == null) continue
				if (!itemHolder.isInsideVehicle) continue
				val actualPos = itemHolder.vehicle!!.location.yaw
				itemHolder.bodyPose = EulerAngle(0.0, Math.toRadians(actualPos.toDouble()), 0.0)
				//itemHolder.location.yaw = actualPos
			}
		}
	}
}