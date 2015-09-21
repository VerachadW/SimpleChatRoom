package me.lazmaid.sample.chatroom

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.ArrayList

/**
 * Created by VerachadW on 2/19/15.
 */
class ChatMessageAdapter : RecyclerView.Adapter<MessageViewHolder>() {

    private val messages: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.listitem_message, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.tvChatMessage.text = messages.get(position)
    }

    override fun getItemCount(): Int {
        return messages.size()
    }

    fun add(message: String) {
        messages.add(0, message)
        notifyDataSetChanged()
    }
}

class MessageViewHolder(val rootView: View) : RecyclerView.ViewHolder(rootView) {
   val tvChatMessage by lazy { rootView.findViewById(R.id.tvChatMessage) as TextView }
}
