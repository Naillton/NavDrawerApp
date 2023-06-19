package com.example.navdrawerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // definindo drawer layout
        drawerLayout = findViewById(R.id.drawer_layout)
        // definindo navView
        val navView: NavigationView = findViewById(R.id.navigationView)

        // criando acao de gaveta alternada
        toggle = ActionBarDrawerToggle(
            this@MainActivity,
            drawerLayout,
            R.string.open,
            R.string.close,
        )

        // adcionando ouvinte ao drawerlayout passando o toggle criado como parametro
        drawerLayout.addDrawerListener(toggle)
        // sincronizando o estado do drawer
        toggle.syncState()

        // definindo acao de suporte para colocar a home da nossa interface de usuario como sendo
        // a primeira a ser vizualizada
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // criando listener do NavigationView para ouvir itens selecionados
        navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                // usando o id dos fragmentos de layout para especifar qual mostrar ao clicar
                R.id.nav_home -> {
                    replaceFragment(FragmentHome(), it.title.toString())
                }
                R.id.nav_Message -> {
                    replaceFragment(FragmentMessage(), it.title.toString())
                }
                R.id.nav_Settings -> {
                    replaceFragment(FrgmentSettings(), it.title.toString())
                }
                R.id.nav_login -> {
                    replaceFragment(FragmentLogin(), it.title.toString())
                }
            }
            true
        }
    }

    // criando funcao de subistituicao de fragmentos
    private fun replaceFragment(fragment: Fragment, title: String) {
        // criando gerenciador de fragmentos
        val fragmentManager = supportFragmentManager
        // iniciando transacao de fragmentos
        val fragmentTransaction = fragmentManager.beginTransaction()
        // definindo que o fragmento subistituira o nosso frame layout
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
    }

    // sobreescrevendo funcao de opcao selecionada
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // usando condicional para saber se a opcao esta selecionada se sim retorna true
        // se nao retorna a super classe com options com o parametro selecionado
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}