import org.khronos.webgl.WebGLRenderingContext
import org.w3c.dom.HTMLCanvasElement
import kotlin.browser.*

fun main(args: Array<String>) {
    val message = "Hello JavaScript!"
    println(message)

    setBodyBackground()
    setButtons()
    modifyText()

    zoomUp()
    zoomDown()

//    start()
}



fun setButtons() {
    val zoomUp = document.getElementById("zoomPlus")
    zoomUp?.addEventListener("click", {
        zoomUp()
        println("zoomUp: $zoom")
    })


    val zoomDown = document.getElementById("zoomMinus")
    zoomDown?.addEventListener("click", {
        zoomDown()
        println("zoomMinus: $zoom")
    })

}

var zoom: Double = 0.0

fun zoomDown() {
    zoom -= 0.2
}

fun zoomUp() {
    zoom += 0.2
}

fun setBodyBackground() {
    document.body?.style?.background = "#f3f3f3 url('res/bg.png')"
}

private fun modifyText() {
    val element = document.getElementById("text")
    element?.innerHTML = "cos"
}

//var gl: WebGLRenderingContext? = null
//fun start() {
//    val canvas = document.getElementById("glcanvas") as HTMLCanvasElement
//
//    gl = initWebGL(canvas)
//
//    if (gl != null) {
//        gl!!.clearColor(0.0F, 0.0F, 0.0F, 1.0F)
//        gl!!.enable(WebGLRenderingContext.DEPTH_TEST)
//        gl!!.depthFunc(WebGLRenderingContext.LEQUAL)
//        gl!!.clear(WebGLRenderingContext.COLOR_BUFFER_BIT or WebGLRenderingContext.DEPTH_BUFFER_BIT)
//    }
//
//    val vertices: FloatArray = floatArrayOf(
//            -0.5F, 0.5F, 0.0F,
//            -0.5F, -0.5F, 0.0F,
//            0.5F, -0.5F, 0.0F
//    )
//
//    val indices: IntArray = intArrayOf(0, 1, 2)
//}
//
//fun initWebGL(canvas: HTMLCanvasElement?): WebGLRenderingContext? {
//    gl = null
//
//    gl = canvas?.getContext("webgl") as WebGLRenderingContext?
//    //canvas.getContext("experimental-webgl")
//
//    // If we don't have a GL context, give up now
//    if (gl == null) {
//        gl = null
//        println("WebGL == null")
//    }
//
//    return gl
//}