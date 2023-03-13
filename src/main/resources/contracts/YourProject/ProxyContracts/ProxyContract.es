{
    // ===== Contract Description ===== //
    // Name: NFT Buyer Proxy Contract
    // Description: This contract is a proxy contract and ensures funds are used for NFTs or are refunded.
    // Version: 1.0.0
    // Author: mgpai22@github.com

    // ===== Box Registers ===== //
    // R4: SigmaProp => Buyer SigmaProp
    // R5: Coll[Byte] => State Box Singleton

    // ===== Compile Time Constants ===== //
    // _minerFee: Long

    // ===== Context Extension Variables ===== //
    // None

    val isRefund: Boolean = (INPUTS.size == 1)
    val buyerPK: SigmaProp = SELF.R4[SigmaProp].get
    val stateBoxSingleton: Coll[Byte] = SELF.R5[Coll[Byte]].get

    if (!isRefund) {

        val validNFTSaleTx: Boolean = {

            // inputs
            val stateBoxIN: Box = INPUTS(0)

            // outputs
            val issuerBoxOUT: Box = OUTPUTS(0)

            val validStateBox: Boolean = {
                (stateBoxIN.tokens(0)._1 == stateBoxSingleton) // check that the state box has the right singleton value
            }

            val validIssuerBox: Boolean = {
                (issuerBoxOUT.R9[(SigmaProp, Long)].get._1 == buyerPK) // check that issuer box has the buyer sigmaprop
            }

            allOf(Coll(
                validStateBox,
                validIssuerBox
            ))

        }

        sigmaProp(validNFTSaleTx)

    } else {

        val validRefundTx: Boolean = {

            // outputs
            val refundBoxOUT: Boolean = OUTPUTS(0)
            val minerBoxOUT: Box = OUTPUTS(1)

            val validRefundBox: Boolean = {

                allOf(Coll(
                    (refundBoxOUT.value == SELF.value - _minerFee),
                    (refundBoxOUT.propositionBytes == buyerPK.propBytes)
                ))

            }

            val validMinerFee: Boolean = (minerBoxOUT.value == _minerFee)

            allOf(Coll(
                validRefundBox,
                validMinerFee,
                (OUTPUTS.size == 2)
            ))

        }

        sigmaProp(validRefundTx) && buyerPK // buyer must sign tx themself as well

    }

}
