package filipetrinidad.metatomo.activies

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.ThumbnailUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import filipetrinidad.metatomo.R
import filipetrinidad.metatomo.databinding.ActivityClassificacaoAguaSimbolicoBinding
import filipetrinidad.metatomo.ml.ModelUnquant
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.ByteOrder

class ClassificacaoAguaSimbolico : AppCompatActivity() {

    private var _binding: ActivityClassificacaoAguaSimbolicoBinding? = null

    var result: TextView? = null
    var imageView: ImageView? = null
    lateinit var picture: Button
    var imageSize = 224

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityClassificacaoAguaSimbolicoBinding.inflate(layoutInflater)
        setContentView(_binding?.root)

        result = findViewById(R.id.result)
        imageView = findViewById(R.id.imageView)
        picture = findViewById(R.id.button)

        picture.setOnClickListener {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, 1)
            } else {
                //Request camera permission if we don't have it.
                requestPermissions(arrayOf(Manifest.permission.CAMERA), 100)
            }
        }
    }

    fun classifyImage(image: Bitmap) {
        try {

            var resultado: String = ""

            val model = ModelUnquant.newInstance(this)

            // Creates inputs for reference.
            val inputFeature0 =
                TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)
            val byteBuffer: ByteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3)
            byteBuffer.order(ByteOrder.nativeOrder())

            // get 1D array of 224 * 224 pixels in image
            val intValues = IntArray(imageSize * imageSize)
            image.getPixels(intValues, 0, image.width, 0, 0, image.width, image.height)

            // iterate over pixels and extract R, G, and B values. Add to bytebuffer.
            var pixel = 0
            for (i in 0 until imageSize) {
                for (j in 0 until imageSize) {
                    val `val` = intValues[pixel++] // RGB
                    byteBuffer.putFloat((`val` shr 16 and 0xFF) * (1f / 255f))
                    byteBuffer.putFloat((`val` shr 8 and 0xFF) * (1f / 255f))
                    byteBuffer.putFloat((`val` and 0xFF) * (1f / 255f))
                }
            }
            inputFeature0.loadBuffer(byteBuffer)

            // Runs model inference and gets result.
            val outputs = model.process(inputFeature0)

            val outputFeature0 = outputs.outputFeature0AsTensorBuffer

            val confidences = outputFeature0.floatArray
            // find the index of the class with the biggest confidence.
            var maxPos = 0
            var maxConfidence = 0f
            for (i in confidences.indices) {
                if (confidences[i] > maxConfidence) {
                    maxConfidence = confidences[i]
                    maxPos = i
                }
            }
            val classes = arrayOf("Copo de água - Macroscópico",
                "Copo de água - Simbólico",
                "Água - Submicroscópico",
                "Água - Simbólico",
                "Copo de água - Macroscópico",
                "SiH4")
            result!!.text = classes[maxPos]
            resultado = classes[maxPos]
            var s = ""
            for (i in classes.indices) {
                s += String.format("%s: %.1f%%\n", classes[i], confidences[i] * 100)
            }

            if(resultado == "Água - Simbólico"){
                quizCerto("Correto",
                    "$resultado",
                    R.drawable.bg_verde,
                    R.drawable.verifica,
                    R.drawable.background_botao_dialog_verde
                )
            } else{
                quizErrado("Errado",
                    "O desenho não representa o nível simbólico de uma molécula de água. O nível simbólico retrata os átômos em formatos de letras. \n" +
                            "Classificação: $resultado",
                    R.drawable.bg_vermelho,
                    R.drawable.error,
                    R.drawable.background_botao_dialog_vermelho)
            }

            //confidence!!.text = s


            // Releases model resources if no longer used.
            model.close()
        } catch (e: IOException) {
            // TODO Handle the exception
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            var image = data!!.extras!!["data"] as Bitmap?
            val dimension = Math.min(image!!.width, image.height)
            image = ThumbnailUtils.extractThumbnail(image, dimension, dimension)
            imageView!!.setImageBitmap(image)
            image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false)
            classifyImage(image)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun quizCerto(titulo: String, corpo: String, bolaFundoBack: Int, bolaFundoImage: Int, btnCor: Int){

        val dialog = Dialog(this)
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
            val i = Intent(this, ClassificacaoCopoMacroscopico::class.java )
            startActivity(i)
        }
    }
    private fun quizErrado(titulo: String, corpo: String, bolaFundoBack: Int, bolaFundoImage: Int, btnCor: Int ){

        val dialog = Dialog(this)
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