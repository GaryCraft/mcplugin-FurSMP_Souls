package xyz.fursmp.souls.utils

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class BuildItem(private val modelData:Int) {
	private val item : ItemStack = ItemStack(Material.LEATHER_HORSE_ARMOR)
	fun getItem() :ItemStack {
		val meta = item.itemMeta
		meta!!.setCustomModelData(modelData)
		item.itemMeta = meta
		return item
	}

}