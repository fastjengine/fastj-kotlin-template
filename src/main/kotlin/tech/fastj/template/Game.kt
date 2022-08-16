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

        val helloFastJ: Text2D = Text2D.fromText("Hello, FastJ 1.7.0-SNAPSHOT-1!")

        /* Translate our hello text to the center of the screen */
        helloFastJ.translation = canvas.canvasCenter.subtract((helloFastJ.width() / 2f), (helloFastJ.height() / 2f))

        /* Render hello fastj text */
        drawableManager().addGameObject(helloFastJ)
    }
}

