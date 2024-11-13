package duardo001.br.com.fiap.eduardosantosrm96042.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import duardo001.br.com.fiap.eduardosantosrm96042.data.ItemDao
import duardo001.br.com.fiap.eduardosantosrm96042.data.ItemDatabase
import duardo001.br.com.fiap.eduardosantosrm96042.model.ItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel(application: Application) : AndroidViewModel(application) {

    private val itemDao: ItemDao = ItemDatabase.getDatabase(application).itemDao()

    private val _itemsLiveData = MutableLiveData<List<ItemModel>>()
    val itemsLiveData: LiveData<List<ItemModel>> = _itemsLiveData

    private var allItems: List<ItemModel> = emptyList()

    init {

        mockItems()
    }

    private fun mockItems() {

        val mockData = listOf(
            ItemModel(titulo = "Reutilize materiais", descricao = "Evite o desperdício reutilizando materiais como garrafas e sacolas."),
            ItemModel(titulo = "Plante árvores", descricao = "Plante mais árvores para ajudar a preservar o meio ambiente."),
            ItemModel(titulo = "Economize energia", descricao = "Desligue luzes e aparelhos quando não estiverem em uso."),
            ItemModel(titulo = "Reduza o uso de plásticos", descricao = "Evite produtos descartáveis de plástico.")
        )
        allItems = mockData
        _itemsLiveData.value = allItems
    }


    init {
        fetchAllItems()
    }

    private fun fetchAllItems() {
        viewModelScope.launch(Dispatchers.IO) {
            allItems = itemDao.getAllSync()
            _itemsLiveData.postValue(allItems)
        }
    }

    fun addItem(item: ItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            itemDao.insert(item)
            fetchAllItems()
        }
    }

    fun removeItem(item: ItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            itemDao.delete(item)
            fetchAllItems()
        }
    }

    fun searchItems(query: String) {
        val filteredItems = if (query.isEmpty()) {
            allItems
        } else {
            allItems.filter { it.titulo.contains(query, ignoreCase = true) || it.descricao.contains(query, ignoreCase = true) }
        }
        _itemsLiveData.value = filteredItems
    }
}
