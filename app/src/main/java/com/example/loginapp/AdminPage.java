package com.example.loginapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class AdminPage extends AppCompatActivity {



    ArrayList<User> User = new ArrayList<>();

    AdminController mAdminController;
    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);



        //initialize the views
        listView = (ListView) findViewById(R.id.List_view_users);
        listView.setEmptyView(findViewById(R.id.empty_subtitle_text));


        //TODO link with the rest
        //
        String[] username = {"Russell","Jon","Xuanhui"};
        String[] useremail = {"Russell@gmail.com","Jon@gmail.com","Xuanhui@gmail.com"};

        for(int i=0;i<username.length;i++){
            User.add(new User(useremail[i],username[i]));
        }
        //


        mAdminController = new AdminController(this,User);
        listView.setAdapter(mAdminController);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                showdeleteDialog(position);

            }
        });




    }




    //From this

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem menuItem = menu.findItem(R.id.searchView);

        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                Log.e("Main"," data search"+newText);

                mAdminController.getFilter().filter(newText);



                return true;
            }
        });


        return true;

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();


        if(id == R.id.searchView){

            return true;
        }
        return super.onOptionsItemSelected(item);
    }



//To this














    private void showdeleteDialog(final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Delete User?");

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //delete user and refresh page
                mAdminController.remove(User.get(position));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
