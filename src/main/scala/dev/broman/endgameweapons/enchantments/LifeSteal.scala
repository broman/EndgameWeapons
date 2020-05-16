package dev.broman.endgameweapons.enchantments
import java.util

import dev.broman.endgameweapons.plugin.Enchant
import org.bukkit.ChatColor
import org.bukkit.enchantments.{Enchantment, EnchantmentTarget}
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.inventory.ItemStack

@Enchant
case object LifeSteal extends CustomEnchantment("lifesteal") {
  override def getName: String = s"${ChatColor.RESET}${ChatColor.GRAY}Life Steal"

  override def getMaxLevel: Int = 10

  override def getStartLevel: Int = 1

  override def getItemTarget: EnchantmentTarget = EnchantmentTarget.WEAPON

  override def isTreasure: Boolean = false

  override def isCursed: Boolean = false

  override def conflictsWith(other: Enchantment): Boolean = false

  override def canEnchantItem(item: ItemStack): Boolean = true

  override def getChance: Double = 0.3

  override def onEntityHit(event: EntityDamageByEntityEvent, enchantments: util.Map[Enchantment, Integer]): Unit = {
    val p = event.getDamager.asInstanceOf[Player]
    val candidate = p.getHealth + (event.getDamage * enchantments.get(LifeSteal) / 10)
    p.setHealth(if (candidate > 20) 20 else candidate)
  }
}
