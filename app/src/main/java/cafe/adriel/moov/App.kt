package cafe.adriel.moov

import android.app.Application
import com.joanzapata.iconify.Iconify
import com.joanzapata.iconify.fonts.MaterialModule

class App: Application() {

    companion object {
        lateinit var context: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        context = this

        Iconify.with(MaterialModule())
    }

}