package main

import contracts.YourProjectContracts

object init extends App {
  println("Hello World!")
}

object printContract extends App {
  val proxyContract = YourProjectContracts.ProxyContract.contractScript
  println(proxyContract)
}
