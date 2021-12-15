package xyz.fursmp.souls.mobs

import org.bukkit.entity.EntityType
import xyz.fursmp.souls.mobs.ModelDataIDS.BABY_GHAST
import xyz.fursmp.souls.utils.CustomEntity

class BabyGhast:SoulsMob() {
	override var entity: CustomEntity? = null
	init {
		val goal:Array<String> = arrayOf("FollowParent")
		this.entity = CustomEntity(EntityType.GHAST, "BabyGhast", 10.0, BABY_GHAST,goal)
	}
}