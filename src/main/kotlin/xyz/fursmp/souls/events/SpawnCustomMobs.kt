package xyz.fursmp.souls.events

import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.CreatureSpawnEvent
import xyz.fursmp.souls.mobs.BabyGhast
import xyz.fursmp.souls.utils.CustomEntity
import xyz.fursmp.souls.utils.Loggers.consoleLog
import xyz.fursmp.souls.utils.Loggers.consoleWarning

class SpawnCustomMobs : Listener {

	@EventHandler
	fun onSpawn(event: CreatureSpawnEvent) {
		if (event.entityType != EntityType.GHAST) return

		consoleLog("Spawning BabyGhast")
		consoleLog("Mob Spawned " + event.entity.name)
		if((event.entity as Entity).customName == "BabyGhast")	return
		if(event.entity.hasMetadata("isCustomMob")) return



		try {
			for (i in 1..3) {
				val rand = Math.random() * 5000
				if (rand > 15) continue
				val mob = BabyGhast()
				val entity = mob.entity as CustomEntity
				entity.spawnAt(event.location.world!!, event.location)
			}

		} catch (e: Exception) {
			println(e)
			consoleWarning("Failed to spawn BabyGhast!")
		}



	}

}