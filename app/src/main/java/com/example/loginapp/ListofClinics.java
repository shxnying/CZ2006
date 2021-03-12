package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListofClinics extends AppCompatActivity {
    ListView listView;
    EditText SearchFilter;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listof_clinics);

        listView = (ListView)findViewById(R.id.listview);
        SearchFilter = (EditText)findViewById(R.id.searchFilter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//arraylist of items to be on the list
        //to be changed with database of clinics
        ArrayList<String> arrayList=new ArrayList<>();

        arrayList.add("Some cool clinic");
        arrayList.add("Chua Clinic");
        arrayList.add("Some cooler clinic");
        arrayList.add("Some better clinic");
        arrayList.add("Some clinic");
        arrayList.add("Best clinic");
        arrayList.add("Neo Clinic");
        arrayList.add("Chang clinic");
        arrayList.add("Goh clinic");
        arrayList.add("Running out of ideas clinic");
        arrayList.add("Need real clinics clinic");
        arrayList.add("Test clinic");

        arrayAdapter=new ArrayAdapter(ListofClinics.this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

//pop up message when an item on the list is clicked
        //to be changed such that clicking on item redirects user to page containing info on clinic and queue
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Toast.makeText(ListofClinics.this, "clicked item"+i+" "+arrayList.get(i).toString(),Toast.LENGTH_SHORT).show();
            }
        });

        SearchFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (ListofClinics.this).arrayAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
   /* @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        return true;
    }*/
}