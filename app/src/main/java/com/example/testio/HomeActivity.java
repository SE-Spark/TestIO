package com.example.testio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.graphics.Color;


import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collection;


public class HomeActivity  extends AppCompatActivity {
    DatabaseHelper db;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    String[] presidents;
    Button mark;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        db = new DatabaseHelper(this);
        listView = findViewById(R.id.lv_showData);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setTextFilterEnabled(true);
        // List<Student> students=new ArrayList<String>();
        // listView.setAdapter(students);
        //ArrayList<Student> students= new ArrayList<>();
        presidents= getResources().getStringArray(R.array.user_type);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked,presidents);
       /* for (Student student : students = db.DisplayAllStudents()) {
            adapter.addAll((Collection<? extends String>) student);
        }*/

        listView.setAdapter(adapter);
        //menu hooks
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView =(NavigationView)findViewById(R.id.nav_view);

        mark = findViewById(R.id.btn_mark);
        mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView = findViewById(R.id.lv_showData);
                String selected="selected items: \n";
                for(int i=0; i<=listView.getCount(); i++){
                    if(listView.isItemChecked(i)){
                        selected += listView.getItemAtPosition(i) + "\n";
                    }
                }
                Toast.makeText(getApplicationContext(),selected,Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                Toast.makeText(getApplicationContext(),"logout",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.restart_app:
                Toast.makeText(getApplicationContext(),"restart app",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.profile_settings:
                Toast.makeText(getApplicationContext(),"profile settings",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.help:
                Toast.makeText(getApplicationContext(),"help",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.search:
                Toast.makeText(getApplicationContext(),"search",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.new_student:
                // findViewById(R.id.student_container).setVisibility(VISIBLE);
                startActivity(new Intent(getApplicationContext(),StudentActivity.class));
                return true;
            case R.id.share:
                Toast.makeText(getApplicationContext(),"share",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.rate:
                Toast.makeText(getApplicationContext(),"rate us",Toast.LENGTH_SHORT).show();
                return true;
            default:
                Toast.makeText(getApplicationContext(),"welcome",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


}