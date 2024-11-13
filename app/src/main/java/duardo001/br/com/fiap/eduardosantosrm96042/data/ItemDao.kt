package duardo001.br.com.fiap.eduardosantosrm96042.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import duardo001.br.com.fiap.eduardosantosrm96042.model.ItemModel

@Dao
interface ItemDao {

    @Query("SELECT * FROM item_table")
    fun getAll(): LiveData<List<ItemModel>>

    @Query("SELECT * FROM item_table")
    fun getAllSync(): List<ItemModel>

    @Insert
    fun insert(item: ItemModel)

    @Delete
    fun delete(item: ItemModel)
}