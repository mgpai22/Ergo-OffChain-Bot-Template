package contracts

import enumeratum._

import scala.collection.immutable
import scala.io.Source

sealed trait YourProjectContracts extends EnumEntry {
  // Top Folder
  val domain: String = ""
  // Sub Folder
  val domainType: String = ""
  val contractType: ContractType = ContractTypes.None
  val fileExtension: String = ".es"
  val dirName: String = "contracts"
  val version: Long = 0

  lazy val fileName: String = if (version <= 1) {
    this.toString + fileExtension
  } else {
    this.toString + s"_v$version" + fileExtension
  }
  lazy val contractScript: String = get()

  def getPath: String =
    List(dirName, domain, domainType, contractType.plural, fileName)
      .filter(_.nonEmpty)
      .mkString("/")

  def get(): String = {
    val getViaPath: () => String = () => {
      val fullPath: String = getPath
      try {
        val contractSource =
          Source.fromResource(
            fullPath
          )

        val contractString = contractSource.mkString
        contractSource.close()

        contractString
      } catch {
        case _: NullPointerException =>
          throw new NullPointerException(s"$fullPath not found")
      }
    }

    val contractString: String = getViaPath()

    contractString
  }
}

object YourProjectContracts extends Enum[YourProjectContracts] {
  val values: immutable.IndexedSeq[YourProjectContracts] = findValues
  case object StateContract extends NFTBoxGuardScriptContract
  case object IssuerContract extends NFTBoxGuardScriptContract
  case object CollectionIssuer extends NFTBoxGuardScriptContract
  case object CollectionIssuance extends NFTBoxGuardScriptContract
  case object SingletonIssuer extends NFTBoxGuardScriptContract
  case object SingletonIssuance extends NFTBoxGuardScriptContract
  case object AVLdebug extends NFTBoxGuardScriptContract
  case object ProxyContract extends ProxyContractBoxGuardScriptContract
}

sealed trait NFTContract extends YourProjectContracts {
  override val domain: String = "YourProject"
}

sealed trait ProxyContract extends YourProjectContracts {
  override val domain: String = "YourProject"
}

//</editor-fold>

//<editor-fold desc="Detailed Contract Types">
/** // ===== Detailed Level Contracts =====
  */
sealed trait NFTBoxGuardScriptContract extends NFTContract {
  override val contractType: ContractType = ContractTypes.BoxGuardScript
}

sealed trait ProxyContractBoxGuardScriptContract extends ProxyContract {
  override val contractType: ContractType = ContractTypes.ProxyContract
}
//</editor-fold>

//<editor-fold desc="Contract Type Enum">
/** Describes the different contract types as Enums
  */
sealed trait ContractType extends EnumEntry { val plural: String }

object ContractTypes extends Enum[ContractType] {
  val values: immutable.IndexedSeq[ContractType] = findValues

  case object ProxyContract extends ContractType {
    override val plural = "ProxyContracts"
  }

  case object BoxGuardScript extends ContractType {
    override val plural = "BoxGuardScripts"
  }
  case object None extends ContractType { override val plural = "" }
}

//</editor-fold>
