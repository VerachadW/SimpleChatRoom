package com.gudarin.sample.chatroom

import android.os.Bundle
import kotlin.properties.Delegates
import android.widget.EditText
import android.support.v7.app.ActionBarActivity

/**
 * Created by VerachadW on 2/19/15.
 */
class SimpleChatActivity : ActionBarActivity() {

    private val FG_REGISTRATION_TAG = "Registration"
    private val FG_CHAT_ROOM_TAG = "ChatRoom"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_chat)

        getSupportFragmentManager().beginTransaction()
                .add(R.id.flContainer, RegisterFragment.newInstance(), FG_REGISTRATION_TAG).commit()

    }

    fun openChatRoom(username: String){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.flContainer, ChatRoomFragment.newInstance(username), FG_CHAT_ROOM_TAG).commit()
    }

    override fun onBackPressed() {
        if (getSupportFragmentManager().findFragmentByTag(FG_CHAT_ROOM_TAG) != null) {
            getSupportFragmentManager().popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}
