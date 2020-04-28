package dev.broman.endgameweapons.plugin

import dev.broman.endgameweapons.commands.AddEnchantment
import dev.broman.endgameweapons.enchantments.{CustomEnchantment, Knockup, LifeSteal, PoisonEdge}
import org.bukkit.command.CommandSender
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

object Main {
  var customEnchants: Map[String, CustomEnchantment] = _
  var listeners: List[Listener] = _
  var _instance: Main = _

  def getInstance: Main = _instance
}

class Main extends JavaPlugin {
  override def onLoad(): Unit = {
    Main._instance = this

    Main.customEnchants = Map(
      "lifesteal" -> LifeSteal,
      "knockup" -> Knockup,
      "poisonedge" -> PoisonEdge,
    )
  }

  private val commands = Map(
    "addenchant" -> classOf[AddEnchantment]
  )

  override def onEnable(): Unit = {
    getLogger.info("Registering enchantments...")
    Main.customEnchants.values foreach registerEnchantment
    getLogger.info("Registering listeners...")
    getServer.getPluginManager.registerEvents(new CustomEnchantListener, this)
    getLogger.info("Registration complete!")
  }

  def registerEnchantment(enchantment: Enchantment): Unit = {
    var registered = true
    try {
      val f = classOf[Enchantment].getDeclaredField("acceptingNew")
      f.setAccessible(true)
      f.set(null, true)
      Enchantment.registerEnchantment(enchantment)
    } catch {
      case e: Exception =>
        registered = false
        e.printStackTrace()
    }
    if (registered) {
      getLogger.info(s"Registered ${enchantment.getKey.getKey}")
    }
  }

  override def onCommand(sender: CommandSender, command: org.bukkit.command.Command, label: String, args: Array[String]): Boolean = {
    val x: Class[_] = commands(label)
    x.getMethod("commandInvoked", classOf[CommandSender], classOf[Array[String]])
      .invoke(x.getDeclaredConstructor().newInstance(), sender, args)
      .asInstanceOf[Boolean]
  } // TODO: P3 Error handling
}
