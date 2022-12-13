package com.dalhousie.foodnculture.activities;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dalhousie.foodnculture.R;
import com.dalhousie.foodnculture.adapters.ChatAdapter;
import com.dalhousie.foodnculture.apifacade.ApiFacade;
import com.dalhousie.foodnculture.models.Messages;
import com.dalhousie.foodnculture.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChatActivity extends AppCompatActivity {


    RecyclerView recyclerViewChat;
    TextView name;
    EditText msg;
    ImageButton send;

    Integer userId, friendUserId;
    List<Messages> chatList;
    ChatAdapter chatAdapter;
    boolean notify = false;

    final Handler readMessageHandler = new Handler();
    final int delay = 2000; // every 2 seconds
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            readMessages();
            readMessageHandler.postDelayed(this, delay);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        friendUserId = getIntent().getIntExtra("friendId", 0);
        userId = getIntent().getIntExtra("userId", 0);

        Optional<User> user = ApiFacade.getInstance().getUserApi().getById(friendUserId);
        readMessageHandler.postDelayed(runnable, delay);

        // initialise the text views and layouts
        name = findViewById(R.id.txtuserchatname);
        msg = findViewById(R.id.messaget);
        send = findViewById(R.id.sendmsg);
        name.setText(String.format("%s %s", user.get().getFirstName(), user.get().getLastName()));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);

        recyclerViewChat = findViewById(R.id.chatrecycle);
        recyclerViewChat.setHasFixedSize(true);
        recyclerViewChat.setLayoutManager(linearLayoutManager);

        ImageButton backButton = findViewById(R.id.btnArrowleft);
        backButton.setOnClickListener(view -> finish());

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notify = true;
                String message = msg.getText().toString().trim();
                if (TextUtils.isEmpty(message)) {
                    Toast.makeText(ChatActivity.this, "Please write a message", Toast.LENGTH_LONG).show();
                } else {
                    sendmessage(message);
                }
                msg.setText("");
            }
        });

        readMessages();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }


    private void readMessages() {
        // show message after retrieving data
        chatList = new ArrayList<>();
        chatList = ApiFacade.getInstance().getMessagesApi().getAllMessagesBetweenUsers(userId, friendUserId);


        chatAdapter = new ChatAdapter(ChatActivity.this, chatList, userId);
        chatAdapter.notifyDataSetChanged();
        recyclerViewChat.setAdapter(chatAdapter);
    }


    private void sendmessage(final String message) {
        Messages messages = new Messages();
        messages.setUserId(userId);
        messages.setTargetUserId(friendUserId);
        messages.setContent(message);
        messages.setRead(false);

        try {
            ApiFacade.getInstance().getMessagesApi().save(messages);
            readMessages(); // refresh messages
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(ChatActivity.this, "Failed to send the message", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroy () {
        readMessageHandler.removeCallbacks(runnable);
        super.onDestroy ();
    }
}


