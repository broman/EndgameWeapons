package dev.broman.endgameweapons.examples

import java.util

import dev.broman.endgameweapons.enchantments.CustomEnchantment
import org.bukkit.enchantments.{Enchantment, EnchantmentTarget}
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.inventory.ItemStack

class MyCustomEnchantment extends CustomEnchantment("myenchantname") with Listener {
  /** The chance, from 0 to 1, that a player has of recieving the enchantment. */
  override def getChance: Double = ???

  /**
   * Method called when an item with this enchantment is used to damage an entity.
   *  @param event The bukkit.event.EntityDamageByEntityEvent that caused the call
   *  @param enchants A map of the Enchantment to the enchantment level
   */
  override def onEntityHit(event: EntityDamageByEntityEvent, enchants: util.Map[Enchantment, Integer]): Unit = ???

  /** Deprecated. Returns the string representation of the enchantment. */
  override def getName: String = ???

  /** The maximum level a player can recieve on the enchantment. */
  override def getMaxLevel: Int = ???

  /** The starting level of the enchantment. */
  override def getStartLevel: Int = ???

  /** The EnchantmentTarget of the enchantment. See the Bukkit API for reference. */
  override def getItemTarget: EnchantmentTarget = ???

  /** Whether or not the item is a treasure. */
  override def isTreasure: Boolean = ???

  /** Deprecated. Whether or not the item is a curse. */
  override def isCursed: Boolean = ???

  /**
   * Called when the game is trying to decide whether or not an item can coexist with another enchantment.
   * @param other The other enchantment to compare with.
   * @return whether or not the item can be enchanted. If false, the item will not be enchanted with the new enchantment.
   */
  override def conflictsWith(other: Enchantment): Boolean = ???

  /**
   * Called when an item is being enchanted with this enchantment.
   * @param item The ItemStack attempting to be enchanted.
   * @return Whether or not the item can be enchanted.
   */
  override def canEnchantItem(item: ItemStack): Boolean = ???
}
