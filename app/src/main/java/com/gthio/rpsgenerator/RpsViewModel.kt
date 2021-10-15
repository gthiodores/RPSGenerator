package com.gthio.rpsgenerator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RpsViewModel : ViewModel() {

    private val _selectedWeapon = MutableLiveData<RpsItem>()
    val selectedWeapon: LiveData<RpsItem> = _selectedWeapon

    private val _clickCount = MutableLiveData<Int>()
    val clickCount: LiveData<Int> = _clickCount

    fun selectWeapon(item: RpsItem) {
        when (item == RpsItem.Random) {
            true -> _selectedWeapon.value = RpsItem.values().filter { it != RpsItem.Random }.random()
            false -> _selectedWeapon.value = item
        }
        _clickCount.value = _clickCount.value?.plus(1) ?: 1
    }

}