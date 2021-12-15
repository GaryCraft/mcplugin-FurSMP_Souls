package xyz.fursmp.souls.events

import org.bukkit.entity.EntityType
import org.bukkit.entity.SmallFireball
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileLaunchEvent

class CustomMechanics :Listener {

	// This Will Make Fireball launched by a BabyGhast spawn twice
	@EventHandler
	fun onGhastFire(event: ProjectileLaunchEvent) {
		val projectile = event.entity
		if (projectile.type != EntityType.FIREBALL) return
		/*
		if(projectile.shooter == null) return
		if (projectile.shooter is Player) return
		if ((projectile.shooter as Entity).getMetadata("isCustomMob").contains(FixedMetadataValue(Bukkit.getPluginManager().getPlugin("SoulsPlugin")!!, true))) return
		*/
		if (projectile is SmallFireball) return


		for (i in 0..2) {
			val second = projectile.world.spawnEntity(projectile.location, EntityType.SMALL_FIREBALL)
			if (second is SmallFireball) {
				second.teleport(projectile)
				second.direction = projectile.velocity
				second.velocity = projectile.velocity
				second.shooter = projectile.shooter
			}
		}
	}

}