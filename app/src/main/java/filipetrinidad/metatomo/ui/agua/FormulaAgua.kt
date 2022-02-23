package filipetrinidad.metatomo.ui.quizz

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import filipetrinidad.metatomo.R
import filipetrinidad.metatomo.databinding.FragmentFormulaAguaBinding


class FormulaAgua : Fragment() {

    private var _binding: FragmentFormulaAguaBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFormulaAguaBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.confirmar?.setOnClickListener {

            val resposta: String =  _binding?.editResposta?.text.toString()

            if(resposta == "H20" || resposta == "h2o" || resposta == "H2o" || resposta == "h2O"){
                quizCerto("Correto",
                "A molécula de água é composta por três átomos, um oxigênio e dois hidrogênios",
                R.drawable.bg_verde,
                R.drawable.verifica,
                R.drawable.background_botao_dialog_verde,
                    R.id.action_formulaAgua_to_classificacao)
             } else{
                 quizErrado("Errado",
                 "Lembre-se, a água é formada por três átomos e por dois elementos químicos: Oxigênio e Hidrogênio",
                 R.drawable.bg_vermelho,
                 R.drawable.error,
                 R.drawable.background_botao_dialog_vermelho)
            }

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