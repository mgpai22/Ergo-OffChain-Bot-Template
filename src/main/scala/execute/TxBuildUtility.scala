package execute

import io.getblok.getblok_plasma.collections.{LocalPlasmaMap, PlasmaMap, Proof}
import org.bouncycastle.util.encoders.Hex
import org.ergoplatform.ErgoBox.R9
import org.ergoplatform.{ErgoBox, ErgoScriptPredef}
import org.ergoplatform.appkit.{ErgoValue, _}
import org.ergoplatform.appkit.impl.{Eip4TokenBuilder, ErgoTreeContract}
import org.ergoplatform.appkit.scalaapi.ErgoValueBuilder
import scalan.RType.LongType
import scorex.crypto.encode.Base16
import sigmastate.basics.DLogProtocol
import sigmastate.eval.Colls
import special.collection.Coll
import utils.{OutBoxes, TransactionHelper, explorerApi}

import java.util
import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class TxBuildUtility(
    val ctx: BlockchainContext,
    txOperatorMnemonic: String,
    txOperatorMnemonicPw: String
) {
  private val txPropBytes =
    Base16.decode(ErgoScriptPredef.feeProposition(720).bytesHex).get
  private val api = new explorerApi(
    DefaultNodeInfo(ctx.getNetworkType).explorerUrl
  )
  private val outBoxObj = new OutBoxes(ctx)
  private val txHelper = new TransactionHelper(
    ctx = ctx,
    walletMnemonic = txOperatorMnemonic,
    mnemonicPassword = txOperatorMnemonicPw
  )

}
