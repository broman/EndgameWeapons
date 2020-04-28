package dev.broman.endgameweapons.enchantments

import dev.broman.endgameweapons.plugin.Main
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.entity.EntityDamageByEntityEvent

abstract class CustomEnchantment(namespace: String) extends Enchantment(new NamespacedKey(Main.getInstance, namespace)) {
  def getChance: Double
  def onEntityHit(event: EntityDamageByEntityEvent, enchants: java.util.Map[Enchantment, Integer])
}
