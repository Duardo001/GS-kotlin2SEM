package duardo001.br.com.fiap.eduardosantosrm96042.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class ItemModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val titulo: String,
    val descricao: String
)