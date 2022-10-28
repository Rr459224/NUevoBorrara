package com.example.listacompra;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.listacompra.Fragments.AyudaFragment;
import com.example.listacompra.Fragments.CargarFragment;
import com.example.listacompra.Fragments.CrearFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    public static SQLiteDatabase db;
    public static List<String> lista;
    public static List<String> verLista = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase("MiLista", Context.MODE_PRIVATE, null);

        // db.execSQL("delete from miLista;");

        db.execSQL("CREATE TABLE IF NOT EXISTS miLista(Nombre VARCHAR);");


        // db.execSQL("INSERT INTO MiLista VALUES ('Repollo'),('Tomate'),('Col'),('Lechuga'),('Guisantes'),('Escarola')," +
        //       "('Brocoli'),('Rabano'),('Boniato'),('Rucula'),('Pepino'),('Cebolla'),('Calabaza'),('Zanahoria'),('Calabacin')");
        listar();
        // System.out.println("Lista:"+lista.size());
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        setToolbar();

        //metodo para cargar una opcion por defecto
        setDefaultFragment();

        //declarar listener para cuando se pulsa sobre las distintas opciones del menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;
                boolean fragmentTransaction = false;//controla cuando el usuario ha hecho click sobre alguna opción del menu

                switch (item.getItemId()) {
                    case R.id.crearNuevaLista:
                        fragment = new CrearFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.cargarListaReciente:
                        fragment = new CargarFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.ayuda:
                        fragment = new AyudaFragment();
                        fragmentTransaction = true;
                        break;
                }

                if (fragmentTransaction) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
                    item.setChecked(true);
                    getSupportActionBar().setTitle(item.getTitle());
                    drawerLayout.closeDrawers();
                }
                return true;
            }
        });
    }

    private void setDefaultFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new CrearFragment()).commit();

        MenuItem item = navigationView.getMenu().getItem(0);
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);//icono de menu al toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//que se muestre el icono
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //al hacer click sobre el icono de menu se abre el navigationdrawer
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);//se abre el navigationdrawer desde la izquierda
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void listar() {
        ArrayAdapter<String> adaptador;
        lista = new ArrayList<String>();
        Cursor c = db.rawQuery("SELECT * FROM MiLista", null);
        if (c.getCount() == 0) {

            db.execSQL("INSERT INTO MiLista VALUES ('Repollo'),('Tomate'),('Col'),('Lechuga'),('Guisantes'),('Escarola')," +
                    "('Brocoli'),('Rabano'),('Boniato'),('Rucula'),('Pepino'),('Cebolla'),('Calabaza'),('Zanahoria'),('Calabacin')");
            Cursor c2 = db.rawQuery("SELECT * FROM MiLista", null);

            while (c2.moveToNext())
                lista.add(c.getString(0));
            // lista.add("No hay registros");
        } else {
            while (c.moveToNext())
                lista.add(c.getString(0));
            // System.out.println(c.getString(0));
        }
        System.out.println(lista.size());
        // adaptador=new ArrayAdapter<String>
        //   (getApplicationContext(), android.R.layout.activity_list_item,lista);
        //listaDiscos.setAdapter(adaptador);
        c.close();
    }
}