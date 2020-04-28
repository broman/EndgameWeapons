package dev.broman.endgameweapons.plugin

import org.bukkit.entity.{LivingEntity, Player}
import org.bukkit.event.entity.{EntityDamageByEntityEvent, EntityShootBowEvent}
import org.bukkit.event.{EventHandler, Listener}
import org.bukkit.metadata.FixedMetadataValue

class CustomEnchantListener extends Listener {
  @EventHandler
  def onEntityHit(event: EntityDamageByEntityEvent): Unit = {
    (event.getDamager, event.getEntity) match {
      case (a: Player, v: LivingEntity) =>
        Main.customEnchants.values foreach { e =>
          val items = a.getInventory.getItemInMainHand.getEnchantments
          if(items.containsKey(e)) {
            e.onEntityHit(event, items)
          }
        }
    }
  }

  @EventHandler
  def onBowFire(event: EntityShootBowEvent): Unit = {
    Main.customEnchants.values foreach { e =>
      val enchants = event.getBow.getEnchantments
      if(enchants.containsKey(e)) {
        event.getProjectile.setMetadata(
          "dev.broman:EndgameWeapons-customenchantments",
          new FixedMetadataValue(Main.getInstance, e))
      }
    }
  }
}
