package com.gudarin.sample.chatroom

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import org.java_websocket.WebSocket
import kotlin.properties.Delegates
import java.net.URI
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.text.TextUtils
import android.support.v7.widget.RecyclerView
import org.json.JSONObject
import com.google.gson.Gson
import android.support.v7.widget.LinearLayoutManager

/**
 * Created by VerachadW on 2/19/15.
 */
class ChatRoomFragment : Fragment() {

    class object {

        private val ARG_USERNAME = "arg_username"

        fun newInstance(username: String): ChatRoomFragment {
            val fragment = ChatRoomFragment()
            val args = Bundle();
            args.putString(ARG_USERNAME, username)
            fragment.setArguments(args)

            return fragment
        }
    }

    private val mClient: ChatClient by Delegates.lazy {
        ChatClient(URI("ws://172.18.5.68:8888"))
    }

    private val btSend by Delegates.lazy {
        getActivity().findViewById(R.id.btSend) as Button
    }

    private val etMessage by Delegates.lazy {
        getActivity().findViewById(R.id.etMessage) as EditText
    }

    private val rvChatMessages by Delegates.lazy {
        getActivity().findViewById(R.id.rvChatMessages) as RecyclerView
    }

    private val mAdapter: ChatMessageAdapter = ChatMessageAdapter()

    private var mUsername: String by Delegates.notNull()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fg_chatroom, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (getArguments() != null) {
            mUsername = getArguments().getString(ARG_USERNAME)
        } else {
            IllegalAccessError("No username found")
        }

        mClient.connect()

        rvChatMessages.setLayoutManager(LinearLayoutManager(getActivity()))
        rvChatMessages.setAdapter(mAdapter)

        btSend.setOnClickListener {
            val msg = etMessage.getText().toString()
            if (!TextUtils.isEmpty(msg)) {
                mClient.send("${mUsername}: ${msg}")
            }
        }
    }

    inner class ChatClient(url: URI): WebSocketClient(url) {
        override fun onOpen(handshakedata: ServerHandshake?) {
            Log.d("ChatClient", "ChatClient Connected")
        }

        override fun onMessage(message: String) {
            this@ChatRoomFragment.getActivity().runOnUiThread({
                mAdapter.add(message)
            })
        }

        override fun onClose(code: Int, reason: String?, remote: Boolean) {
            Log.d("ChatClient", "ChatClient Disconnected")
        }

        override fun onError(ex: Exception) {
            ex.printStackTrace()
        }

    }
}
