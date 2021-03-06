package com.example.loginapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {

    private List<Chatbot> chatbotList;

    public Adapter(List<Chatbot> chatbotList) {
        this.chatbotList = chatbotList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_conversation, viewGroup, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int position) {
        String userMessage = chatbotList.get(position).getUserMessage();
        String chatbotMessage = chatbotList.get(position).getChatbotMessage();
        viewholder.setData(userMessage, chatbotMessage);

    }

    @Override
    public int getItemCount() {
        return chatbotList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{

        private TextView userMessage;
        private TextView chatbotMessage;

        public Viewholder(@NonNull View itemView){
            super(itemView);

            userMessage = itemView.findViewById(R.id.userMessage);
            chatbotMessage = itemView.findViewById(R.id.chatbotMessage);
        }
        private void setData(String userText, String chatbotText) {
            userMessage.setText(userText);
            chatbotMessage.setText(chatbotText);

        }
    }
}
