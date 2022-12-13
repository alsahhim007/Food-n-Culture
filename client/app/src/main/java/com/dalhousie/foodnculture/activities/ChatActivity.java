package com.dalhousie.foodnculture.activities;

import android.os.Bundle;
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
import com.dalhousie.foodnculture.models.ChatModel;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatActivity extends AppCompatActivity {


    RecyclerView recyclerViewChat;
    TextView name;
    EditText msg;
    ImageButton send;
//    FirebaseAuth firebaseAuth;

    String uid, myuid;
    ArrayList<ChatModel> chatList;
    ChatAdapter chatAdapter;
    boolean notify = false;
    private String[][] message_dummy;
    private String[] sender;


    //    FirebaseDatabase firebaseDatabase;
//    DatabaseReference users;
    private String[] receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        //firebaseAuth = FirebaseAuth.getInstance();

        // initialise the text views and layouts
        name = findViewById(R.id.txtuserchatname);
        msg = findViewById(R.id.messaget);
        send = findViewById(R.id.sendmsg);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);

        recyclerViewChat = findViewById(R.id.chatrecycle);
        recyclerViewChat.setHasFixedSize(true);
        recyclerViewChat.setLayoutManager(linearLayoutManager);

        initialize_data();
//        uid = getIntent().getStringExtra("uid");

        // getting uid of another user using intent
//        firebaseDatabase = FirebaseDatabase.getInstance();
//
//        users = firebaseDatabase.getReference("Users");


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notify = true;
                String message = msg.getText().toString().trim();
                if (TextUtils.isEmpty(message)) {//if empty
                    Toast.makeText(ChatActivity.this, "Please write a message", Toast.LENGTH_LONG).show();
                } else {
                    sendmessage(message);
                }
                msg.setText("");
            }
        });


//        Query userquery = users.orderByChild("uid").equalTo(uid);
//        userquery.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                // retrieve user data
//                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                    String nameh = "" + dataSnapshot1.child("name").getValue();
//
//                    name.setText(nameh);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
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


//        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Chats");
//        dbref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                chatList.clear();
//                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                    ChatModel modelChat = dataSnapshot1.getValue(ChatModel.class);
//                    if (modelChat.getSender().equals(myuid) &&
//                            modelChat.getReceiver().equals(uid) ||
//                            modelChat.getReceiver().equals(myuid)
//                                    && modelChat.getSender().equals(uid)) {
//                        chatList.add(modelChat); // add the chat in chatlist
//                    }
//                    chatAdapter = new ChatAdapter(ChatActivity.this, chatList);
//                    chatAdapter.notifyDataSetChanged();
//                    recyclerViewChat.setAdapter(chatAdapter);
//                }
//            }

//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }


    private void sendmessage(final String message) {
        // creating a reference to store data in firebase
        // We will be storing data using current time in "Chatlist"
        // and we are pushing data using unique id in "Chats"
        // DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();


        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", myuid);
        hashMap.put("receiver", uid);
        hashMap.put("message", message);


//        databaseReference.child("Chats").push().setValue(hashMap);
//        final DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("ChatList").child(uid).child(myuid);
//        ref1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (!dataSnapshot.exists()) {
//                    ref1.child("id").setValue(myuid);
//                }
//            }

//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


//        final DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("ChatList").child(myuid).child(uid);
//        ref2.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                if (!dataSnapshot.exists()) {
//                    ref2.child("id").setValue(uid);
//                }
//            }
//
////            @Override
////            public void onCancelled(@NonNull DatabaseError databaseError) {
////
////            }
//        });
    }

    private void initialize_data() {

        chatList = new ArrayList<>();

        sender = new String[]{
                getString(R.string.friend1),
                getString(R.string.friend1),
                getString(R.string.friend1),
        };

        receiver = new String[]{
                getString(R.string.frienduser5),
                getString(R.string.frienduser3),
                getString(R.string.frienduser4),
        };

        message_dummy = new String[][]{
                {"Hi", "Hello How are you?"},
                {"Hello", "I am doing fine how about you?"},
                {"Yoo the event looks sick", "Do join in"}
        };

        for (int iter = 0; iter < receiver.length; iter++) {
            for (int iter_msg = 0; iter_msg < message_dummy.length - 1; iter_msg++) {
                ChatModel chat = new ChatModel(receiver[iter], message_dummy[iter][iter_msg], sender[iter]);
                chatList.add(chat);
            }
        }
    }
}


