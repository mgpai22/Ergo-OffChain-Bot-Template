package utils

import org.ergoplatform.appkit.impl.{Eip4TokenBuilder, ErgoTreeContract}
import org.ergoplatform.appkit._
import org.ergoplatform.sdk.ErgoToken
import work.lithos.plasma.collections.LocalPlasmaMap

import java.nio.charset.StandardCharsets

class OutBoxes(ctx: BlockchainContext) {

  private val minAmount = 1000000L
  private val txBuilder = this.ctx.newTxBuilder()

  def pictureNFTHelper(
      inputBox: InputBox,
      name: String,
      description: String,
      imageLink: String,
      sha256: Array[Byte]
  ): Eip4Token = {
    val tokenID = inputBox.getId.toString
    Eip4TokenBuilder.buildNftPictureToken(
      tokenID,
      1,
      name,
      description,
      0,
      sha256,
      imageLink
    )

  }

  def tokenHelper(
      inputBox: InputBox,
      name: String,
      description: String,
      tokenAmount: Long,
      tokenDecimals: Int
  ): Eip4Token = {
    new Eip4Token(
      inputBox.getId.toString,
      tokenAmount,
      name,
      description,
      tokenDecimals
    )
  }

  def collectionTokenHelper(
      inputBox: InputBox,
      name: String,
      description: String,
      tokenAmount: Long,
      tokenDecimals: Int
  ): Eip4Token = {

    Eip4TokenBuilder.buildNftPictureToken(
      inputBox.getId.toString,
      tokenAmount,
      name,
      description,
      tokenDecimals,
      Array(0.toByte),
      "link"
    )
  }

  def NFToutBox(
      nft: Eip4Token,
      receiver: Address,
      amount: Long = minAmount
  ): OutBox = {
    this.txBuilder
      .outBoxBuilder()
      .value(amount)
      .mintToken(nft)
      .contract(
        new ErgoTreeContract(
          receiver.getErgoAddress.script,
          this.ctx.getNetworkType
        )
      )
      .build()
  }

  def tokenMintOutBox(
      token: Eip4Token,
      receiver: Address,
      amount: Long = minAmount
  ): OutBox = {
    this.txBuilder
      .outBoxBuilder()
      .value(amount)
      .mintToken(token)
      .contract(
        new ErgoTreeContract(
          receiver.getErgoAddress.script,
          this.ctx.getNetworkType
        )
      )
      .build()
  }

  def tokenOutBox(
      token: Seq[ErgoToken],
      receiver: Address,
      amount: Long = minAmount
  ): OutBox = {
    this.txBuilder
      .outBoxBuilder()
      .value(amount)
      .tokens(token: _*)
      .contract(
        new ErgoTreeContract(
          receiver.getErgoAddress.script,
          this.ctx.getNetworkType
        )
      )
      .build()
  }

  def optionalTokenOutBox(
      token: Seq[ErgoToken],
      receiver: Address,
      amount: Long = minAmount
  ): OutBox = {
    val box = this.txBuilder
      .outBoxBuilder()
      .value(amount)
      .contract(
        new ErgoTreeContract(
          receiver.getErgoAddress.script,
          this.ctx.getNetworkType
        )
      )

    if (token.nonEmpty) {
      box.tokens(token: _*)
    }
    box.build()
  }

  def payoutBox(
      receiver: Address,
      amount: Long = minAmount
  ): OutBox = {
    this.txBuilder
      .outBoxBuilder()
      .value(amount)
      .contract(
        new ErgoTreeContract(
          receiver.getErgoAddress.script,
          this.ctx.getNetworkType
        )
      )
      .build()
  }

  def AVLDebugBox[K, V](
      contract: ErgoContract,
      metaDataMap: LocalPlasmaMap[K, V],
      index: Long,
      amount: Long = minAmount
  ): OutBox = {
    this.txBuilder
      .outBoxBuilder()
      .value(amount)
      .registers(
        metaDataMap.ergoValue,
        ErgoValue.of(index)
      )
      .contract(contract)
      .build()
  }

  def AVLDebugBoxSpending(
      senderAddress: Address,
      description: String,
      amount: Long = minAmount
  ): OutBox = {
    this.txBuilder
      .outBoxBuilder()
      .value(amount)
      .registers(ErgoValue.of(description.getBytes(StandardCharsets.UTF_8)))
      .contract(
        new ErgoTreeContract(
          senderAddress.getErgoAddress.script,
          this.ctx.getNetworkType
        )
      )
      .build()
  }

  def genericContractBox(
      contract: ErgoContract,
      amount: Long = minAmount
  ): OutBox = {
    this.txBuilder
      .outBoxBuilder()
      .value(amount)
      .contract(contract)
      .build()
  }

  def simpleOutBox(
      senderAddress: Address,
      amount: Long = minAmount
  ): OutBox = {
    this.txBuilder
      .outBoxBuilder()
      .value(amount)
      .contract(
        new ErgoTreeContract(
          senderAddress.getErgoAddress.script,
          this.ctx.getNetworkType
        )
      )
      .build()
  }

}
