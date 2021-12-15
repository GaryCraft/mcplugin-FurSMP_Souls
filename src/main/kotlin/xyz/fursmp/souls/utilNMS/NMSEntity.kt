@file:Suppress("SameParameterValue")

package xyz.fursmp.souls.utilNMS

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.event.entity.CreatureSpawnEvent
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method


class NMSEntity{

	fun create(entityType: EntityType, location: Location): Entity? {
		try {
			// We get the craftworld class with nms so it can be used in multiple versions
			val craftWorldClass = getNMSClass("org.bukkit.craftbukkit.", "CraftWorld")

			// Cast the bukkit world to the craftworld
			val craftWorldObject = craftWorldClass.cast(location.world)

			// Create variable with the method that creates the entity
			// https://hub.spigotmc.org/stash/projects/SPIGOT/repos/craftbukkit/browse/src/main/java/org/bukkit/craftbukkit/CraftWorld.java#896
			val createEntityMethod: Method = craftWorldObject.javaClass.getMethod(
				"createEntity",
				Location::class.java,
				Class::class.java
			)

			// Attempt to invoke the method that creates the entity itself. This returns a net.minecraft.server entity
			val entity: Any = createEntityMethod.invoke(craftWorldObject, location, entityType.entityClass)

			// Finally, we run the getBukkitEntity method in the entity class to get a usable object
			return entity.javaClass.getMethod("getBukkitEntity").invoke(entity) as Entity

		} catch (exception: ClassNotFoundException) {
			exception.printStackTrace()
		} catch (exception: NoSuchMethodException) {
			exception.printStackTrace()
		} catch (exception: InvocationTargetException) {
			exception.printStackTrace()
		} catch (exception: IllegalAccessException) {
			exception.printStackTrace()
		}


		// If something went wrong we just return null
		return null
	}
	fun spawn(entity:Entity, location: Location): Entity {

		val craftWorldClass = getNMSClass("org.bukkit.craftbukkit.", "CraftWorld")


		// Cast the bukkit world to the craftworld
		val craftWorldObject = craftWorldClass.cast(location.world)

		val addEntityMethod: Method = craftWorldObject.javaClass.getMethod(
			"addEntity",
			Location::class.java,
			Class::class.java
		)

		val spawned : Any = addEntityMethod.invoke(craftWorldObject, entity as net.minecraft.world.entity.Entity, CreatureSpawnEvent.SpawnReason.CUSTOM)

		//return spawned as Entity
		return spawned.javaClass.getMethod("getBukkitEntity").invoke(spawned) as Entity




	}

	/**
	 *
	 * @param prefix What comes before the version number
	 * @param nmsClassString What comes after the version number
	 * @return Class The class that you tried to access
	 * @throws ClassNotFoundException throws an exception if the class it not found
	 */
	@Throws(ClassNotFoundException::class)
	private fun getNMSClass(prefix: String, nmsClassString: String): Class<*> {
		// Getting the version by splitting the package

		val version = Bukkit.getServer().javaClass.getPackage().name.replace(".", ",").split(",").toTypedArray()[3] + "."

		// Combining the prefix + version + nmsClassString for the full class path
		val name = prefix + version + nmsClassString
		return Class.forName(name)
	}
}

