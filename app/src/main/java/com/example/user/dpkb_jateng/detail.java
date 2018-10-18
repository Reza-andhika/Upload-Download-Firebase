package com.example.user.dpkb_jateng;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class detail extends AppCompatActivity {
    private TextView nm_fl,bln_fl;
    private Button down,hps;
    private String nm,bln,ur;
    private Context ctx;
    //database reference to get uploads data
    private DatabaseReference mDatabaseReference;
    //list to store uploads data
    private List<Upload> uploadList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            nm = bundle.getString("nama");
            bln = bundle.getString("bulan");
            ur = bundle.getString("ur");
        } else {
            nm = getIntent().getStringExtra("nama");
            bln=getIntent().getStringExtra("bulan");
            ur=getIntent().getStringExtra("ur");
        }
        uploadList = new ArrayList<>();
        nm_fl=(TextView)findViewById(R.id.nm_file);
        bln_fl=(TextView)findViewById(R.id.bln_file);
        down=(Button)findViewById(R.id.dwnd_file);
        hps=(Button)findViewById(R.id.hps_file);

        nm_fl.setText(nm);
        bln_fl.setText(bln);
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(ur));
                startActivity(intent);
            }
        });
        hps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference rev= FirebaseDatabase.getInstance().getReference();
                DatabaseReference menu=rev.child("uploads");
                Upload upload=new Upload();
                menu.child(nm.toLowerCase()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(detail.this,"Hapus dari Database Sukses..",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(detail.this,"Hapus dari Database Gagal..",Toast.LENGTH_SHORT).show();
                    }
                });

                StorageReference mStorageRef;

                ///Hapus File
                mStorageRef= FirebaseStorage.getInstance().getReferenceFromUrl(ur);

                mStorageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(detail.this,"Hapus File Dari Storage Sukses..",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(detail.this,"Hapus File Dari Storage Gagal..",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
