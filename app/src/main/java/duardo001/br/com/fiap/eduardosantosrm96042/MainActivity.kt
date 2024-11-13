package duardo001.br.com.fiap.eduardosantosrm96042

import android.content.Intent
import androidx.appcompat.widget.SearchView
import android.os.Bundle
import android.widget.Button

import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
import duardo001.br.com.fiap.eduardosantosrm96042.data.ItemDatabase
import duardo001.br.com.fiap.eduardosantosrm96042.viewmodel.ItemAdapter
import duardo001.br.com.fiap.eduardosantosrm96042.viewmodel.ItemViewModel
import duardo001.br.com.fiap.eduardosantosrm96042.viewmodel.ItemViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewDicas: RecyclerView
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var searchView: SearchView
    private val itemViewModel: ItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewDicas = findViewById(R.id.recyclerViewDicas)
        recyclerViewDicas.layoutManager = LinearLayoutManager(this)

        itemAdapter = ItemAdapter { item -> itemViewModel.removeItem(item) }
        recyclerViewDicas.adapter = itemAdapter

        // Observando os dados no LiveData
        itemViewModel.itemsLiveData.observe(this, Observer { items ->
            // Atualiza a lista de itens no adapter quando os dados mudarem
            itemAdapter.updateData(items)
        })

        // Configuração do SearchView para busca dinâmica
        searchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    itemViewModel.searchItems(it)  // Realiza a busca quando o texto for submetido
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    itemViewModel.searchItems(it)  // Realiza a busca a cada mudança no texto
                }
                return true
            }
        })

        val btnIntegrantes: Button = findViewById(R.id.btnIntegrantes)
        btnIntegrantes.setOnClickListener {
            val intent = Intent(this, IntegrantesActivity::class.java)
            startActivity(intent)
        }
    }
}

