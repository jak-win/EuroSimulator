package pl.wincenciuk.eurosimulator.data.model

data class EuroMatchResult(
    val scoreA: Int, val scoreB: Int
) {
    constructor() : this(0,0)
}
