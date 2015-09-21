package com.gudarin.sample.chatroom

import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import kotlin.properties.Delegates
import android.widget.EditText
import android.widget.Button
import android.text.TextUtils

/**
 * Created by VerachadW on 2/19/15.
 */
class RegisterFragment : Fragment() {

    val etUsername by lazy { vRoot.findViewById(R.id.etUsername) as EditText  }
    val btJoin by lazy { vRoot.findViewById(R.id.btJoin) as Button }

    lateinit var vRoot: View

    companion object {
        fun newInstance(): RegisterFragment {
            return RegisterFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        vRoot = inflater!!.inflate(R.layout.fg_register, container, false)
        return vRoot
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btJoin.setOnClickListener {

            val username = etUsername.text.toString()

            if (!TextUtils.isEmpty(username)) {
                (activity as SimpleChatActivity).openChatRoom(username)
            }
        }
    }
}
