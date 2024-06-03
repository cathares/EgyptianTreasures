package com.cathares.egyptiantreasures.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cathares.egyptiantreasures.data.GameUiState
import com.cathares.egyptiantreasures.data.SharedPreferencesManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState(
        field = initField(),
        betSize = initBet(),
        balance = initBalance())
    )
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private fun initField(): List<List<Int>> {
        return if (SharedPreferencesManager.getString("bet", "") != "") {
            SharedPreferencesManager.getField("field", "")
        }
        else List(50) { List(5) { (Random.nextInt(until = 5)) } }
    }
    private fun initBet(): Int {
        return if (SharedPreferencesManager.getString("bet", "") != "") {
            SharedPreferencesManager.getString("bet", "").toInt()
        }
        else 1000
    }
    private fun initBalance(): Int {
        return if (SharedPreferencesManager.getString("balance", "") != "") {
            SharedPreferencesManager.getString("balance", "").toInt()
        }
        else 1000000
    }
    fun updateField() {
        val newField: MutableList<List<Int>> = _uiState.value.field.subList(47, 50).toMutableList()
        for (i in 0..46) {
            newField.add(List(5) { (Random.nextInt(until = 5)) })
        }
        val winSize = calculateWinSize(newField.subList(0, 3))
        Log.d("Field", newField.subList(0,3).toString())
        _uiState.update { currentState ->
            currentState.copy(
                field = newField,
                winAmount = winSize,
                balance = _uiState.value.balance + winSize
            )
        }
        saveProgress()
    }
    fun updateBet(increment: Int) {
        val currentBetSize = _uiState.value.betSize
        if (currentBetSize + increment >= 1000) {
            _uiState.update { currentState ->
                currentState.copy(
                    betSize = currentBetSize + increment
                )
            }
        }
    }
    fun initSpin() {
        val bet = _uiState.value.betSize
        _uiState.update { currentState ->
            currentState.copy(
                balance = _uiState.value.balance - bet
            )
        }
    }
    fun calculateWinSize(currentSlotPos: List<List<Int>>): Int {
        currentSlotPos.forEach(){
            for (i in 0..3) {
                if (currentSlotPos[i] != currentSlotPos[i+1]) {
                    break
                }
                return _uiState.value.betSize * 100
            }
        }
        if (currentSlotPos[0][0] == currentSlotPos[1][1] &&
            currentSlotPos[0][0] == currentSlotPos[2][2] &&
            currentSlotPos[0][0] == currentSlotPos[1][3] &&
            currentSlotPos[0][0] == currentSlotPos[0][4] ||
            currentSlotPos[2][0] == currentSlotPos[1][1] &&
            currentSlotPos[2][0] == currentSlotPos[0][2] &&
            currentSlotPos[2][0] == currentSlotPos[1][3] &&
            currentSlotPos[2][0] == currentSlotPos[2][4]
            ) return _uiState.value.betSize * 50
        if (currentSlotPos[1][0] == currentSlotPos[0][1] &&
            currentSlotPos[1][0] == currentSlotPos[1][2] &&
            currentSlotPos[1][0] == currentSlotPos[0][3] &&
            currentSlotPos[1][0] == currentSlotPos[1][4] ||
            currentSlotPos[1][0] == currentSlotPos[2][1] &&
            currentSlotPos[1][0] == currentSlotPos[1][2] &&
            currentSlotPos[1][0] == currentSlotPos[2][3] &&
            currentSlotPos[1][0] == currentSlotPos[1][4]
            ) return _uiState.value.betSize * 25
        if (currentSlotPos[0][0] == currentSlotPos[0][1] &&
            currentSlotPos[0][0] == currentSlotPos[1][2] &&
            currentSlotPos[0][0] == currentSlotPos[2][3] &&
            currentSlotPos[0][0] == currentSlotPos[2][4] ||
            currentSlotPos[2][0] == currentSlotPos[2][1] &&
            currentSlotPos[2][0] == currentSlotPos[1][2] &&
            currentSlotPos[2][0] == currentSlotPos[0][3] &&
            currentSlotPos[2][0] == currentSlotPos[0][4]
            ) return _uiState.value.betSize * 10
        if (currentSlotPos[1][0] == currentSlotPos[2][1] &&
            currentSlotPos[1][0] == currentSlotPos[1][2] &&
            currentSlotPos[1][0] == currentSlotPos[0][3] &&
            currentSlotPos[1][0] == currentSlotPos[1][4]
        ) return _uiState.value.betSize * 5
        return 0
    }
    fun saveProgress() {
        viewModelScope.launch {
            val bet = _uiState.value.betSize
            val balance = _uiState.value.balance
            val field = _uiState.value.field
            SharedPreferencesManager.saveString("bet", bet.toString())
            SharedPreferencesManager.saveString("balance", balance.toString())
            SharedPreferencesManager.saveField("field", field)
        }
    }
}