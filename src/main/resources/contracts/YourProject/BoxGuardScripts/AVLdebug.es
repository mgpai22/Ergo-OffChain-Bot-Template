{
    val index = SELF.R5[Long].get
    val tree = SELF.R4[AvlTree].get
    val key = longToByteArray (index)
    val keyHash = blake2b256( key )
    val proof = getVar[Coll[Byte]](0).get
    val valueFromAvlTree: Coll[Byte] = tree.get(keyHash, proof).get

    val dummyCheckValue = blake2b256( OUTPUTS(0).R4[Coll[Byte]].get )


    sigmaProp( valueFromAvlTree == dummyCheckValue )


}
