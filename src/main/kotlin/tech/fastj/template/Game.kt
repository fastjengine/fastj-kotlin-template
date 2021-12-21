package tech.fastj.template

import tech.fastj.engine.FastJEngine
import tech.fastj.graphics.display.FastJCanvas
import tech.fastj.graphics.display.RenderSettings
import tech.fastj.graphics.game.Text2D
import tech.fastj.systems.control.SimpleManager

fun main() {
    FastJEngine.init("FastJ Kotlin Template", Game())
    FastJEngine.run()
}

class Game : SimpleManager() {
    override fun init(canvas: FastJCanvas) {
        /* Some crispy anti-aliasing for the road. */
        canvas.modifyRenderSettings(RenderSettings.Antialiasing.Enable)

        /* A very simple Text2D object, welcoming you to FastJ! */
        val helloFastJText = Text2D.fromText("Hello, FastJ 1.6.0!")
        helloFastJText.translate(canvas.canvasCenter)
        drawableManager.addGameObject(helloFastJText)
    }

    override fun update(canvas: FastJCanvas?) {
    }
}

