package xyz.fursmp.souls.utils

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.metadata.FixedMetadataValue


@Suppress("DEPRECATION")
class CustomEntity(private var type: EntityType, private var name: String, private var maxHealth: Double, private var customModelData:Int, private var goals:Array<String>  ) {

	fun spawnAt(world: World, location: Location) {
		//val entity = NMSEntity().create(type,location)
		val entity = world.spawnEntity(location, type)

		entity.setMetadata(
			"isCustomMob",
			FixedMetadataValue(Bukkit.getPluginManager().getPlugin("SoulsPlugin")!!, true)
		)

		val defaultArmorStand = world.spawnEntity(location.subtract(0.0, 2.0, 0.0), EntityType.ARMOR_STAND)

			if (entity is LivingEntity) {
				entity.maxHealth = maxHealth
				entity.health = maxHealth
				entity.isInvisible = false
				entity.isCustomNameVisible = false
				entity.isCollidable = true
				entity.customName = name

				//NMSEntity().spawn(entity, location)
				if (defaultArmorStand is ArmorStand) {
					defaultArmorStand.equipment!!.helmet = BuildItem(customModelData).getItem()
					defaultArmorStand.isVisible = true
					defaultArmorStand.isSmall = false
					defaultArmorStand.isInvulnerable = true
					defaultArmorStand.isGlowing = false
					defaultArmorStand.customName = "ItemHolder"
					defaultArmorStand.isCustomNameVisible = false

				}
				entity.addPassenger(defaultArmorStand)
			}


			


	}

}