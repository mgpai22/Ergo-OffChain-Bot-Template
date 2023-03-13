package utils

import org.ergoplatform.appkit._
import scorex.crypto.hash

class ContractCompile(ctx: BlockchainContext) {
  def compileProxyContract(
      contract: String,
      minerFee: Long
  ): ErgoContract = {
    this.ctx.compileContract(
      ConstantsBuilder
        .create()
        .item("_minerFee", minerFee)
        .build(),
      contract
    )
  }
}
