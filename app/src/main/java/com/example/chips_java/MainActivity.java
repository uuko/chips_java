package com.example.chips_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    private Button button;
    private List<Contact> Contacts=new ArrayList<>();
    private List<Contact> NewContacts=new ArrayList<>();
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycleview);
        input=findViewById(R.id.txt_name);
        chipGroup=findViewById(R.id.chipGroup);
        button=findViewById(R.id.adddd);
//        final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
//                .findViewById(android.R.id.content)).getChildAt(0);
        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event!= null&& (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(input.getApplicationWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                }
                return false;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewContacts.clear();
                List<String> name= Arrays.asList(getResources().getStringArray(R.array.name));
                int [] images={R.drawable.ic_launcher_background,R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_background};
                int count=0;
                for (String Name:name) {
                    NewContacts.add(new Contact(Name,images[count]));
                    count++;
                }
           recyclerAdapter.updateList(NewContacts);
                //recyclerAdapter.oldupdateList(NewContacts);
                recyclerView.setAdapter(recyclerAdapter);

//                recyclerView.smoothScrollToPosition(recyclerAdapter.getItemCount()-1);
            }
        });
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
//                getWindow().getDecorView().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                        if (imm != null) {
//                            viewGroup.requestFocus();
//                            imm.showSoftInput(viewGroup, 0);
//                        }
//                    }
//                }, 100);
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
        int [] images={R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background};
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

