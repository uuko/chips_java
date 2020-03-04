package com.example.chips_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecycleItemSelectedListener, View.OnClickListener {
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private EditText input;
    private ChipGroup chipGroup;
    private List<Contact> Contacts=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycleview);
        input=findViewById(R.id.txt_name);
        chipGroup=findViewById(R.id.chipGroup);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        getContacts();
        Log.d("123", "onCreate: "+Contacts);
        recyclerAdapter=new RecyclerAdapter(this,Contacts);
        recyclerView.setAdapter(recyclerAdapter);

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String sinput=s.toString();
                List<Contact> Newcontacts=new ArrayList<>();
                for (Contact contact:Contacts){
                    if (contact.getName().contains(sinput)){
                        Newcontacts.add(contact);
                    }
                }
                recyclerAdapter=new RecyclerAdapter(MainActivity.this,Newcontacts);
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public void onItemSelected(Contact contact) {
        Chip chip=new Chip(this);
        chip.setText(contact.getName());
        chip.setChipIcon(ContextCompat.getDrawable(this,contact.getPinId()));
        chip.setCloseIconVisible(true);
        chip.setCheckable(false);
        chip.setClickable(false);
        chip.setOnCloseIconClickListener(this);
        chipGroup.addView(chip);
        chipGroup.setVisibility(View.VISIBLE);
    }

    private void  getContacts(){
        List<String> name= Arrays.asList(getResources().getStringArray(R.array.name));
        int [] images={R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background};
        int count=0;
        for (String Name:name) {
            Contacts.add(new Contact(Name,images[count]));
            count++;
        }

        }

    @Override
    public void onClick(View v) {
        Chip chip=(Chip)v;
        chipGroup.removeView(chip);
    }
}

