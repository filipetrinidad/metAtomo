package filipetrinidad.metatomo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.chaquo.python.Python
import com.samsao.messageui.views.MessagesWindow
import filipetrinidad.metatomo.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {

    private var _binding: ActivityChatBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(_binding?.root)

        val messageWindow = _binding?.customizedMessagesWindow as MessagesWindow

        val edit =
            messageWindow.writingMessageView.findViewById<EditText>(R.id.message_box_text_field)
        val button = messageWindow.writingMessageView.findViewById<Button>(R.id.message_box_button)

        messageWindow.setBackgroundResource(R.color.cinzaClaro)
        edit.hint = "Escreva aqui"
        button.text = "->"

        val introducao = "Olá, sou atom, assistente virtual que te ajudará a tirar dúvidas sobre química. Qual conceito pretende pesquisar?"
        messageWindow.receiveMessage(introducao)

        button.setOnClickListener {

            messageWindow.sendMessage(edit.text.toString())

            val py = Python.getInstance()
            val pyobj = py.getModule("index")
            val obj = pyobj.callAttr("main", edit.text.toString())

            messageWindow.receiveMessage(obj.toString())
        }
    }
}