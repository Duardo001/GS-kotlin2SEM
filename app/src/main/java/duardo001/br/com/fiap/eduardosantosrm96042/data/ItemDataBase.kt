package duardo001.br.com.fiap.eduardosantosrm96042.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import duardo001.br.com.fiap.eduardosantosrm96042.model.ItemModel

@Database(entities = [ItemModel::class], version = 1, exportSchema = false)
abstract class ItemDatabase : RoomDatabase() {

    // Expõe o DAO que será usado para interações com o banco de dados
    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var INSTANCE: ItemDatabase? = null

        // Função para obter uma instância do banco de dados
        fun getDatabase(context: Context): ItemDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemDatabase::class.java,
                    "item_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}