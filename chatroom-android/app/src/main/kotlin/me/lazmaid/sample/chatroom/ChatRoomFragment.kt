package me.lazmaid.sample.chatroom

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI
import kotlin.properties.Delegates

/**
 * Created by VerachadW on 2/19/15.
 */
class ChatRoomFragment : Fragment() {

    private val SERVER_URL = "ws://x.x.x.x:8888"
    companion object {

        private val ARG_USERNAME = "arg_username"

        fun newInstance(username: String): ChatRoomFragment {
            val fragment = ChatRoomFragment()
            val args = Bundle();
            args.putString(ARG_USERNAME, username)
            fragment.arguments = args

            return fragment
        }
    }

    private val mClient: ChatClient by Delegates.lazy {
        ChatClient(URI(SERVER_URL))
    }

    private val btSend by lazy {
        activity.findViewById(R.id.btSend) as Button
    }

    private val etMessage by lazy {
        activity.findViewById(R.id.etMessage) as EditText
    }

    private val rvChatMessages by lazy {
        activity.findViewById(R.id.rvChatMessages) as RecyclerView
    }

    private val mAdapter: ChatMessageAdapter = ChatMessageAdapter()

    lateinit private var mUsername: String

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fg_chatroom, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            mUsername = arguments.getString(ARG_USERNAME)
        } else {
            IllegalAccessError("No username found")
        }

        mClient.connect()

        rvChatMessages.layoutManager = LinearLayoutManager(activity)
        rvChatMessages.adapter = mAdapter

        btSend.setOnClickListener {
            val msg = etMessage.text.toString()
            if (!TextUtils.isEmpty(msg)) {
                mClient.send("$mUsername: $msg")
            }
        }
    }

    inner class ChatClient(url: URI): WebSocketClient(url) {
        override fun onOpen(handshakedata: ServerHandshake?) {
            Log.d("ChatClient", "ChatClient Connected")
        }

        override fun onMessage(message: String) {
            this@ChatRoomFragment.activity.runOnUiThread({
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
