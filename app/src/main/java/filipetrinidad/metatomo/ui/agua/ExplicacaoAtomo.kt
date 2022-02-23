package filipetrinidad.metatomo.ui.quizz

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
import filipetrinidad.metatomo.databinding.FragmentExplicacaoAtomoBinding

class ExplicacaoAtomo : Fragment() {

    private var _binding: FragmentExplicacaoAtomoBinding? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExplicacaoAtomoBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            _binding?.atomoDalton?.setOnClickListener {
            quizCerto("Informação",
            "Dalton foi o primeiro a idealizar o que seria um átomo: uma esfera (assim como a imagem), indivisível e que se ligaria com outros átomos para a formação de moléculas.",
            R.drawable.bg_azul,
            R.drawable.informacao,
            R.drawable.background_botao_dialog_azul,
            R.id.action_explicacaoAtomo_to_formulaAgua)
        }
        _binding?.txtCorpo?.text = "Átomo é a estrutura formadora de toda a matéria, isso significa que todas os objetos, substâncias, seres vivos, entre outros são formados por átomos." +
                "O primeiro modelo atômico foi formulado por Dalton, que consistia em uma esfera maciça. Hoje são conhecidos 118 elementos químicos (118 átomos), toda a matéria é formada pela ligação entre eles."
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

}