# Ergo Off-Chain Bot Template

This is a template that can be cloned to implement your off-chain ergo transaction logic!


# Included Packages
- Ergo Appkit v5.0.1
- Lithos Plasma v1.0.2
- Exle Edge v0.1-SNAPSHOT
- Akka v2.8.0
- Apache Http
- Gson Json Serialization
- scalatest v3.2.15
- mockwebserver v3.12.0

# Usage
- Add contracts in src/main/scala/resources
- Add boxes in src/main/scala/utils/Outboxes or /InputBoxes
- Add api calls in src/main/scala/utils/explorerApi
- Build complete transactions in src/main/scala/execute/TxBuildUtility
- Write code which will be driven by akka in src/main/scala/execute/akkaFunctions
- Start and configure akka in src/main/scala/app/Main
- Run modules/test code in src/test/scala/
- Add node and mnemonic information in serviceOwner.json
- If you want to add more items in the serviceOwner.json make sure to add to the ServiceOwnerConfig case class in  src/main/scala/configs/conf
