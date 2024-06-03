package com.cathares.egyptiantreasures.data

data class GameUiState(
    val balance: Int = 1000000,
    val winAmount: Int = 0,
    val betSize: Int,
    val field: List<List<Int>>
)
