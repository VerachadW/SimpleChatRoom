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

    val etUsername by Delegates.lazy { vRoot.findViewById(R.id.etUsername) as EditText  }
    val btJoin by Delegates.lazy { vRoot.findViewById(R.id.btJoin) as Button }

    var vRoot: View by Delegates.notNull()

    class object {
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

            val username = etUsername.getText().toString()

            if (!TextUtils.isEmpty(username)) {
                (getActivity() as SimpleChatActivity).openChatRoom(username)
            }
        }
    }
}
