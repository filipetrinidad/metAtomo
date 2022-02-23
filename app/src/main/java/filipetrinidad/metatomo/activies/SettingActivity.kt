package filipetrinidad.metatomo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import filipetrinidad.metatomo.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {

    private var _binding: ActivitySettingBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(_binding?.root)
    }
}