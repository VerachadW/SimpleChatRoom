package com.gudarin.sample.chatroom

import android.os.Bundle
import kotlin.properties.Delegates
import android.widget.EditText
import android.support.v7.app.ActionBarActivity
import android.support.v7.app.AppCompatActivity

/**
 * Created by VerachadW on 2/19/15.
 */
class SimpleChatActivity : AppCompatActivity() {

    private val FG_REGISTRATION_TAG = "Registration"
    private val FG_CHAT_ROOM_TAG = "ChatRoom"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_chat)

        supportFragmentManager.beginTransaction()
                .add(R.id.flContainer, RegisterFragment.newInstance(), FG_REGISTRATION_TAG).commit()

    }

    fun openChatRoom(username: String){
        supportFragmentManager.beginTransaction()
                .add(R.id.flContainer, ChatRoomFragment.newInstance(username), FG_CHAT_ROOM_TAG).commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.findFragmentByTag(FG_CHAT_ROOM_TAG) != null) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}
