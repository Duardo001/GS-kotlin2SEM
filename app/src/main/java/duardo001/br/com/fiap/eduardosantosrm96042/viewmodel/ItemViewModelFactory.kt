package duardo001.br.com.fiap.eduardosantosrm96042.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ItemViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ItemViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return ItemViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}