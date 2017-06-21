package cafe.adriel.moov.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tinsuke.icekick.extension.freezeInstanceState
import com.tinsuke.icekick.extension.unfreezeInstanceState

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        unfreezeInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        freezeInstanceState(outState)
    }

}