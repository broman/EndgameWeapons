# EndgameWeapons
##### Quickly add custom enchantments and weapons to your Spigot plugin

## Quickstart
```scala
import java.util

import dev.broman.endgameweapons.enchantments.CustomEnchantment
import org.bukkit.enchantments.{Enchantment, EnchantmentTarget}
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.inventory.ItemStack

class MyCustomEnchantment extends CustomEnchantment("myname") {
  override def getChance  = ???
  override def onEntityHit(event:  EntityDamageByEntityEvent, enchants:  util.Map[Enchantment, Integer]): Unit = {
    // This is where your on hit effects go!
  }
  override def getName  = ???
  override def getMaxLevel  = ???
  override def getStartLevel  = ???
  override def getItemTarget  = ???
  override def isTreasure  = ???
  override def isCursed  = ???
  override def conflictsWith(other: Enchantment)  = ???
  override def canEnchantItem(item: ItemStack)  = ???
} 
```

Further examples can be found in the examples package.