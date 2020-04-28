package dev.broman.endgameweapons.enchantments
import java.net.http.WebSocket.Listener
import java.util

import dev.broman.endgameweapons.plugin.Main
import org.bukkit.enchantments.{Enchantment, EnchantmentTarget}
import org.bukkit.entity.LivingEntity
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.util.Vector

case object Knockup extends CustomEnchantment("knockup") with Listener {
  override def getName: String = "Knockup"

  override def getMaxLevel: Int = 10

  override def getStartLevel: Int = 1

  override def getItemTarget: EnchantmentTarget = EnchantmentTarget.WEAPON

  override def isTreasure: Boolean = false
  override def isCursed: Boolean = false

  override def conflictsWith(other: Enchantment): Boolean = false

  override def canEnchantItem(item: ItemStack): Boolean = true

  override def getChance: Double = -1

  override def onEntityHit(event: EntityDamageByEntityEvent, enchantments: util.Map[Enchantment, Integer]): Unit = {
    new BukkitRunnable() {
      override def run(): Unit = {
        event.getEntity.asInstanceOf[LivingEntity].setVelocity(new Vector(0, 1 + enchantments.get(Knockup) / 10, 0))
      }
    }.runTaskLater(Main.getInstance, 1)
  }
}
