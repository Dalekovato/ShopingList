package com.example.shopinglistmvvmcleanarchitecture.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopinglistmvvmcleanarchitecture.data.ShopListRepositoryImpl
import com.example.shopinglistmvvmcleanarchitecture.domain.AddItemUseCase
import com.example.shopinglistmvvmcleanarchitecture.domain.EditItemUseCase
import com.example.shopinglistmvvmcleanarchitecture.domain.GetItemForId
import com.example.shopinglistmvvmcleanarchitecture.domain.ShopItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class ShopItemViewModel(application: Application): AndroidViewModel(application) {



    private val repository = ShopListRepositoryImpl(application)

    private  val getShopItemUseCase = GetItemForId(repository)
    private  val addShopItemUseCAse = AddItemUseCase(repository)
    private  val editShopItemUseCase = EditItemUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() = _shopItem

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen




    fun getShopItem(shopItemId: Int ){
        viewModelScope.launch {
            val item = getShopItemUseCase.getItemForId(shopItemId)
            _shopItem.value = item
        }

    }

    fun addShopItem (inputName:String? , inputCount: String?){


            val name = parsName(inputName)
            val count = parsCount(inputCount)
            val fieldsValid = validaitInput(name , count)

            if (fieldsValid){
                viewModelScope.launch {
                val shopItem = ShopItem(name , count , true)
                addShopItemUseCAse.addItem(shopItem)
                finishWork()
            }
        }


    }

    fun editShopItem(inputName:String? , inputCount: String?){

            val name = parsName(inputName)
            val count = parsCount(inputCount)
            val fieldsValid = validaitInput(name , count)
            if (fieldsValid) {
                _shopItem.value?.let{
                    viewModelScope.launch {
                    val item = it.copy(name = name, count = count)
                    editShopItemUseCase.editItem(item)
                    finishWork()
                }
            }
        }

    }

    private fun parsCount(inputCount: String?):Int{
        return try {
            inputCount?.trim()?.toInt() ?: 0
        }catch (e:Exception){
            0
        }
    }

    private fun parsName(inputName:String?):String{
        return inputName?.trim() ?: ""
    }


    private fun validaitInput (name: String , count: Int): Boolean {
        var result = true

        if (name.isBlank()){
           _errorInputName.value = true
           result = false
        }
        if(count <=0){
            _errorInputCount.value = true
            result = false
        }
        return  result
    }

    fun resetErrorInputName (){
        _errorInputName.value = false
    }
    fun resetErrorInputCount (){
        _errorInputCount.value = false
    }

    private fun finishWork(){
        _shouldCloseScreen.value = Unit
    }



}