package com.example.user.dpkb_jateng;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class User extends AppCompatActivity {
    //the listview
    ListView listView;
    //database reference to get uploads data
    DatabaseReference mDatabaseReference;
    //list to store uploads data
    List<Upload> uploadList;
    //Spinner
    Spinner sp_name;
    String bulan;
    Button but_fil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        uploadList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);
        sp_name=(Spinner) findViewById(R.id.sp_name);
        but_fil=(Button)findViewById(R.id.button_fil);
        //adding a clicklistener on listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //getting the upload
                Upload upload = uploadList.get(i);

                //Opening the upload file in browser using the upload url
                Intent intent = new Intent(Intent.ACTION_VIEW);

                intent.setData(Uri.parse(upload.getUrl()));
                startActivity(intent);
            }
        });
        //getting the database reference
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constant.DATABASE_PATH_UPLOADS);
        but_fil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bulan=sp_name.getSelectedItem().toString();
                uploadList.clear();
                //retrieving upload data from firebase database
                mDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            String bulan_cari=postSnapshot.child("bulan").getValue(String.class);
                            if(bulan_cari.contains(bulan)){

                                Upload upload = postSnapshot.getValue(Upload.class);
                                uploadList.add(upload);
                            }
                        }

                        String[] uploads = new String[uploadList.size()];

                        for (int i = 0; i < uploads.length; i++) {
                            uploads[i] = uploadList.get(i).getName()+"-"+uploadList.get(i).getBulan();
                        }


                        //displaying it to list
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                        listView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });





    }
}
