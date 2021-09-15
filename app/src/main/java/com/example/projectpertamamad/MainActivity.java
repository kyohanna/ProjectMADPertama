package com.example.projectpertamamad;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<User> user;
    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static class user {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //inisiasi recyclerview
        user = new ArrayList<>();
        adapter = new TaskAdapter(this, this);
        binding.recyclerTask.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerTask.setAdapter(adapter);


        //memberi aksi saat menekan floating button dengan mengarahkan ke activity create user
        binding.fabAddTask.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddUser.class);
            startActivityForResult(intent, AddUser.REQUEST_CREATE);
        });
    }