package main

import contracts.YourProjectContracts

object Playground extends App {
  println("Hello World!")
}

object printContract extends App {
  val proxyContract = YourProjectContracts.ProxyContract.contractScript
  println(proxyContract)
}
