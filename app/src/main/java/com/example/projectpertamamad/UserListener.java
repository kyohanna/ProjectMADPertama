package com.example.projectpertamamad;

import android.view.View;

import com.example.projectpertamamad.User;

public interface UserListener {
    void onTaskClicked(View view, User user, int position);
}

