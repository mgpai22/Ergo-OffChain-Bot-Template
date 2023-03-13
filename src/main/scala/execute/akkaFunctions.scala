package execute

import AVL.utils.avlUtils
import configs.{AvlJson, conf, serviceOwnerConf}

import java.util.{Map => JMap}
import scala.collection.JavaConverters._
import scala.collection.mutable
import org.ergoplatform.ErgoBox
import org.ergoplatform.appkit.impl.InputBoxImpl
import org.ergoplatform.appkit.{
  Address,
  BlockchainContext,
  ErgoToken,
  ErgoValue,
  InputBox,
  SigmaProp,
  SignedTransaction
}
import org.ergoplatform.explorer.client.model.OutputInfo
import special.collection.Coll
import io.getblok.getblok_plasma.PlasmaParameters
import io.getblok.getblok_plasma.collections.PlasmaMap
import sigmastate.AvlTreeFlags
import utils.{
  BoxAPI,
  BoxJson,
  ContractCompile,
  NodeBoxJson,
  TransactionHelper,
  explorerApi
}

import java.util
import scala.collection.JavaConverters._
import scala.collection.convert.ImplicitConversions.`collection asJava`
import scala.collection.mutable.ListBuffer
import scala.collection.mutable

class akkaFunctions {

  private val client: Client = new Client()
  client.setClient
  private val ctx = client.getContext
  private val serviceFilePath = "serviceOwner.json"
  private val contractConfFilePath = "contracts.json"
  private lazy val serviceConf = serviceOwnerConf.read(serviceFilePath)
  private lazy val contractsConf = conf.read(contractConfFilePath)

  private val exp = new explorerApi(
    DefaultNodeInfo(ctx.getNetworkType).explorerUrl
  )
  private val walletMnemonic = serviceConf.txOperatorMnemonic
  private val walletMnemonicPw = serviceConf.txOperatorMnemonicPw
  private val txHelper =
    new TransactionHelper(this.ctx, walletMnemonic, walletMnemonicPw)

  println("Service Runner Address: " + txHelper.senderAddress)

  def main(): Unit = {

    println("proceeding with execute")
    Thread.sleep(1000)
  }

}
