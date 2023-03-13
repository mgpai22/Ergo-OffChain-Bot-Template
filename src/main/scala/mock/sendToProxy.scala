package mock

import org.ergoplatform.appkit.{Address, BlockchainContext, ErgoToken, OutBox}
import utils.{InputBoxes, OutBoxes, TransactionHelper}

class sendToProxy(
    ctx: BlockchainContext,
    walletMnemonic: String,
    walletMnemonicPw: String,
    proxyContract: String
) {
  private val txHelper =
    new TransactionHelper(ctx, walletMnemonic, walletMnemonicPw)
  private val input =
    new InputBoxes(ctx).getInputs(List(0.0013), txHelper.senderAddress)
  private val outBox = null.asInstanceOf[OutBox]
  private val unsignedTx =
    txHelper.buildUnsignedTransaction(input, List(outBox))
  private val signedTx = txHelper.signTransaction(unsignedTx)
  println(txHelper.sendTx(signedTx))
  println(signedTx.getOutputsToSpend.get(0).getId.toString)
}
