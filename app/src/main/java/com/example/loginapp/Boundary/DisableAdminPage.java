package com.example.loginapp;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class AdminPage extends AppCompatActivity {



    ArrayList<User> User=new ArrayList<User>();

    AdminController mAdminController;
    ListView listView;
    FirebaseAuth fAuth=FirebaseAuth.getInstance();
    String newtext;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //initialize the views
        listView = (ListView) findViewById(R.id.List_view_users);
        listView.setEmptyView(findViewById(R.id.empty_subtitle_text));


        //TODO link with the rest

        //to fetch all the users of firebase Auth app
//        fAuth = FirebaseAuth.getInstance();
//        String email = fAuth.getCurrentUser().getEmail();
//        String name = fAuth.getCurrentUser().getDisplayName();
//        User.add(new User(email, name));

        // Start listing users from the beginning, 1000 at a time.


        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersdRef = rootRef.child("Users");
        //get list of users, making sure that they aren't already disabled, an admin, or a clinic admin
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String uid = ds.getKey();
                    Log.d("TAG", uid);
                    String email=ds.child("email").getValue(String.class);
                    String name=ds.child("fullName").getValue(String.class);
                    User.add(new User(email,name,0, "nil"));
                }
                mAdminController = new AdminController(AdminPage.this,User);
                listView.setAdapter(mAdminController);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                        showdeleteDialog(mAdminController.getItemId(position));

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        usersdRef.addListenerForSingleValueEvent(eventListener);











    }





    //From this

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem menuItem = menu.findItem(R.id.searchView);

        SearchView searchView = (SearchView) menuItem.getActionView();

        MenuItem alph = menu.findItem(R.id.arrangebyalphabetical);
        alph.setVisible(false);
        MenuItem dist = menu.findItem(R.id.arrangedist);
        dist.setVisible(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newtext=newText;

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

        else{
            Intent myIntent = new Intent(getApplicationContext(), mainactivityAdmin.class);
            startActivityForResult(myIntent, 0);
        }
        return super.onOptionsItemSelected(item);
    }

















//shows dialog to ask admin if he really wants to delete user

    private void showdeleteDialog(final long position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Delete User?");
//finds user in database and disables account
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //delete user and refresh page

                mAdminController.remove(User.get((int) position));
                mAdminController.getFilter().filter(newtext);
                //TODO fAuth.deleteUser(User.get((int) position).uid);
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

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(getApplicationContext(), mainactivityAdmin.class);
        startActivityForResult(myIntent, 0);
        super.onBackPressed();
    }


}
