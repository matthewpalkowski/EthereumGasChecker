//TODO See if this can be moved to be an inner class of the Etherscan class
data class GasPriceReturn(
    val jsonrpc: String,
    val id: Int,
    val result: Double)
