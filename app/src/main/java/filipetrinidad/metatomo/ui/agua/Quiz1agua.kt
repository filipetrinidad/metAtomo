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
import filipetrinidad.metatomo.databinding.FragmentQuiz1Binding


class Quiz1 : Fragment() {

    private var _binding: FragmentQuiz1Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentQuiz1Binding.inflate(inflater, container, false)
        return _binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.resposta1?.setOnClickListener {
            quizErrado("Errado", "Íons são espécies químicas que possuem carga elétrica, perceba na imagem que não há o sinal positivo '+' para indicar carga positiva ou '-' para negativa.", R.drawable.bg_vermelho, R.drawable.error, R.drawable.background_botao_dialog_vermelho)
        }
        _binding?.resposta2?.setOnClickListener {
            quizErrado("Atenção", "Na imagem não há representação de prótons, mas sim de uma estrutura acima: Átomos.", R.drawable.bg_amarelo, R.drawable.atencao, R.drawable.background_botao_dialog_amarelo)
        }
        _binding?.resposta3?.setOnClickListener {

            quizErrado(
                "Errado",
                "A imagem representa apenas uma substância, não caracterizando uma mistura.",
                R.drawable.bg_vermelho,
                R.drawable.error,
                R.drawable.background_botao_dialog_vermelho
            )
        }
        _binding?.resposta4?.setOnClickListener {

                quizCerto(
                    "Certo",
                    "A imagem representa uma molécula, a molécula de água.",
                    R.drawable.bg_verde,
                    R.drawable.verifica,
                    R.drawable.background_botao_dialog_verde,
                    R.id.action_quiz1_to_quiz2
                )

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
            findNavController().navigate(destino)
            dialog.dismiss()
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
