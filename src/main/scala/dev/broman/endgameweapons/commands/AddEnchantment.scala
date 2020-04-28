package dev.broman.endgameweapons.commands

import java.util

import dev.broman.endgameweapons.plugin.Main
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class AddEnchantment {

  import org.bukkit.enchantments.Enchantment

  private val NUMERALS = Array("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X")

  def returnEnchantmentName(ench: Enchantment, enchLevel: Int): String = {

    if (enchLevel == 1 && ench.getMaxLevel == 1) return ench.getName
    if (enchLevel > 10 || enchLevel <= 0) return ench.getName + " enchantment.level." + enchLevel
    ench.getName + " " + NUMERALS(enchLevel - 1)
  }
  def commandInvoked(sender: CommandSender, args: Array[String]): Boolean = {
    args foreach {Main.getInstance.getLogger.info}
    sender match {
      case _: Player =>
        val item = sender.asInstanceOf[Player].getInventory.getItemInMainHand

        val meta = item.getItemMeta
        var lore = meta.getLore
        if(lore == null) lore = new util.ArrayList[String]()
        lore.add(returnEnchantmentName(Main.customEnchants(args(0)), args(1).toInt))
        meta.setLore(lore)
        item.setItemMeta(meta)
        item.addEnchantment(Main.customEnchants(args(0)), args(1).toInt)
    }
    true
  }
}
