package com.example.loginapp;

public class Chatbot {

    String chatbotMessage;
    String userMessage;

    public Chatbot(String chatbotMessage, String userMessage) {
        this.chatbotMessage = chatbotMessage;
        this.userMessage = userMessage;
    }

    public String getChatbotMessage() {
        return chatbotMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }
}
