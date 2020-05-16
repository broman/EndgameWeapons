package dev.broman.endgameweapons.enchantments
import dev.broman.endgameweapons.plugin.Enchant
import org.bukkit.enchantments.{Enchantment, EnchantmentTarget}
import org.bukkit.entity.LivingEntity
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.{PotionEffect, PotionEffectType}

@Enchant
case object PoisonEdge extends CustomEnchantment("poisonedge") {
  override def getName: String = "Poison Edge"

  override def getMaxLevel: Int = 10

  override def getStartLevel: Int = 1

  override def getItemTarget: EnchantmentTarget = EnchantmentTarget.WEAPON

  override def isTreasure: Boolean = false

  override def isCursed: Boolean = false

  override def conflictsWith(other: Enchantment): Boolean = false

  override def canEnchantItem(item: ItemStack): Boolean = true

  override def getChance: Double = 0.1

  override def onEntityHit(event: EntityDamageByEntityEvent, enchantments: java.util.Map[Enchantment, Integer]): Unit = {
    event.getEntity.asInstanceOf[LivingEntity]
      .addPotionEffect(new PotionEffect(
        PotionEffectType.POISON,
        enchantments.get(PoisonEdge) * 20,
        1
      ))
  }
}
