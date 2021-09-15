package com.example.projectpertamamad;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectpertamamad.R;
import com.example.projectpertamamad.databinding.ActivityAddUpdateTaskBinding;
import com.example.projectpertamamad.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AddUser extends AppCompatActivity  {

    private ActivityAddUserBinding binding;
    private User user;
    private int position = 0;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //memberikan tombol back pada action bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        //mendapatkan data task yang dikirim dari activity asal
        user = getIntent().getParcelableExtra(User);
        position = getIntent().getIntExtra(POSITION, 0);

        //jika task tidak null maka is edit dinilai true
        isEdit = user != null;

        //jika is edit true, maka mengisi form edit text dengan data pada objek task
        if (isEdit) {
            binding.editNama.setText(user.getNama());
            binding.editAlamat.setText(user.getAlamat());
            binding.editUmur.setText(user.getUmur());
        } else {
            user = new User();
        }

        //memberi aksi pada btn submit
        binding.Submit.setOnClickListener(v -> {
            //mendapatkan nilai dari tiap edit text
            String name = binding.editNama.getText().toString().trim();
            String description = binding.editDescription.getText().toString().trim();
            String deadline = binding.editDeadline.getText().toString().trim();

            //memastikan setiap field tidak kosong
            if (nama.isEmpty() || alamat.isEmpty() || umur.isEmpty()) {
                Toast.makeText(this, "Complete all forms.", Toast.LENGTH_SHORT).show();
                return;
            }

            //jika is edit bernilai true maka memanggil method update, jika false maka memanggil method add
            if (isEdit) {
                updateUser(user.getNama(), nama, alamat, umur);
            } else {
                addUser(nama, alamat, umur);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //jika user tidak null, maka memberikan menu pada action bar
        if (user != null) {
            getMenuInflater().inflate(R.user.AddUser);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (item.getItemId() == R.id.action_delete) {
            deleteUser(user.getId());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
