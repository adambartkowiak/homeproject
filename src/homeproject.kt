import org.khronos.webgl.WebGLRenderingContext
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.svg.SVGPatternElement
import kotlin.browser.*

fun main(args: Array<String>) {
    val message = "Hello JavaScript!"
    println(message)

    setButtons()
    modifyText()
//    zoomUp()
//    zoomDown()


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

    val svgCanvas = document.getElementById("svg_canvas")
    svgCanvas?.setAttribute("transform", "scale(1.0)")
}

fun zoomUp() {
    zoom += 0.2

    val svgCanvas = document.getElementById("svg_canvas")
    svgCanvas?.setAttribute("transform", "scale(50)")

}

private fun modifyText() {
    val element = document.getElementById("text")
    element?.innerHTML = "nowe cos"
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