package com.gudarin.sample.chatroom

import android.support.v7.widget.RecyclerView
import kotlin.properties.Delegates
import android.view.View
import android.widget.TextView
import android.view.ViewGroup
import java.util.ArrayList
import android.view.LayoutInflater

/**
 * Created by VerachadW on 2/19/15.
 */
class ChatMessageAdapter : RecyclerView.Adapter<MessageViewHolder>() {

    private val messages: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_message, parent, false)
        return MessageViewHolder(view)

    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.tvChatMessage.setText(messages.get(position))
    }

    override fun getItemCount(): Int {
        return messages.size()
    }

    fun add(message: String) {
        messages.add(message)
        notifyDataSetChanged()
    }
}

class MessageViewHolder(val rootView: View) : RecyclerView.ViewHolder(rootView) {
   val tvChatMessage by Delegates.lazy { rootView.findViewById(R.id.tvChatMessage) as TextView}
}
