package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChatbotActivity extends AppCompatActivity {

    private EditText userInput;
    private RecyclerView recyclerView;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        userInput = findViewById(R.id.userInput);
        userInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    String userInputStr = userInput.getText().toString();
                    Log.d("info", userInputStr);
                    userInput.getText().clear();
                    return true;



                }
                return false;
            }
        });


        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        List<Chatbot> chatbotList = new ArrayList<>();
        chatbotList.add(new Chatbot("chatbot message 1", "chatbot message 1"));
        chatbotList.add(new Chatbot("chatbot message 2", "chatbot message 2"));
        chatbotList.add(new Chatbot("chatbot message 3", "chatbot message 3"));
        chatbotList.add(new Chatbot("chatbot message 4", "chatbot message 4"));
        chatbotList.add(new Chatbot("chatbot message 5", "chatbot message 5"));
        chatbotList.add(new Chatbot("chatbot message 6", "chatbot message 6"));
        chatbotList.add(new Chatbot("chatbot message 7", "chatbot message 7"));

        Adapter adapter = new Adapter(chatbotList);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        }
    }
