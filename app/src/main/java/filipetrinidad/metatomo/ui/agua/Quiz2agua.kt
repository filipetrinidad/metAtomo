package filipetrinidad.metatomo.ui.agua

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import filipetrinidad.metatomo.R
import filipetrinidad.metatomo.databinding.FragmentQuiz2Binding

class Quiz2 : Fragment() {

    private var _binding: FragmentQuiz2Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuiz2Binding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.resposta1?.setOnClickListener{
            //
            quizErrado("Errado",
                "Apesar de haver vários átomos ligados, estes são do mesmo tipo e, portanto, não caracteriza uma substÂncia composta",
                R.drawable.bg_vermelho,
                R.drawable.error,
                R.drawable.background_botao_dialog_vermelho)
        }
        _binding?.resposta2?.setOnClickListener{
            quizErrado(
                "Atenção",
                "Embora haja ligações químicas, na imagem não há ións, únicas estruturas que realizam ligações iônicas.",
                R.drawable.bg_amarelo,
                R.drawable.atencao,
                R.drawable.background_botao_dialog_amarelo
            )
        }
        _binding?.resposta3?.setOnClickListener{
            quizCerto("Correto",
                    "O composto apresentado é composto de apenas um tipo de átomo, cinfigurando uma substância pura",
                        R.drawable.bg_verde,
                        R.drawable.verifica,
                        R.drawable.background_botao_dialog_verde,
                        R.id.action_quiz2_to_explicacaoAtomo)
        }
        _binding?.resposta4?.setOnClickListener{
            quizErrado(
                "Errado",
                "Apesar da substância representada possuir uma aprarência metálica, o composto não é um metal.",
                R.drawable.bg_vermelho,
                R.drawable.error,
            R.drawable.background_botao_dialog_vermelho)
        }
    }

    private fun quizCerto(titulo: String, corpo: String, bolaFundoBack: Int, bolaFundoImage: Int, btnCor: Int, destino: Int){

        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_view)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()

        val txt_titulo = dialog.findViewById<TextView>(R.id.txt_titulo)
        val txt_corpo = dialog.findViewById<TextView>(R.id.txt_corpo)
        val bola_fundo = dialog.findViewById<ImageView>(R.id.bola_fundo)
        val btn_confirmar = dialog.findViewById<Button>(R.id.btn_confirm)

        txt_titulo.text = titulo
        txt_corpo.text = corpo
        bola_fundo.setBackgroundResource(bolaFundoBack)
        bola_fundo.setImageResource(bolaFundoImage)
        btn_confirmar.setBackgroundResource(btnCor)

        btn_confirmar.setOnClickListener {
            dialog.dismiss()
            findNavController().navigate(destino)
        }
    }
    private fun quizErrado(titulo: String, corpo: String, bolaFundoBack: Int, bolaFundoImage: Int, btnCor: Int ){

        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_view)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()

        val txt_titulo = dialog.findViewById<TextView>(R.id.txt_titulo)
        val txt_corpo = dialog.findViewById<TextView>(R.id.txt_corpo)
        val bola_fundo = dialog.findViewById<ImageView>(R.id.bola_fundo)
        val btn_confirmar = dialog.findViewById<Button>(R.id.btn_confirm)

        txt_titulo.text = titulo
        txt_corpo.text = corpo
        bola_fundo.setBackgroundResource(bolaFundoBack)
        bola_fundo.setImageResource(bolaFundoImage)
        btn_confirmar.setBackgroundResource(btnCor)

        btn_confirmar.setOnClickListener {
            dialog.dismiss()
        }
    }

}