import kotlin.browser.*

const val ZOOM_STEP = 1.015
const val ZOOM_ANIMATION_SPEED = 5
const val ZOOM_ANIMATION_STEPS = 20
var zoom: Double = 20.0

fun main(args: Array<String>) {
    setButtons()
    updateZoomText()
}


fun setButtons() {
    val zoomUp = document.getElementById("zoomPlus")
    zoomUp?.addEventListener("click", {
        zoomUp()
    })


    val zoomDown = document.getElementById("zoomMinus")
    zoomDown?.addEventListener("click", {
        zoomDown()
    })
}


fun zoomDown() {

    val interval = window.setInterval({
        zoom /= ZOOM_STEP
        updateSvgGrid()
        updateZoomText()
    }, ZOOM_ANIMATION_SPEED)

    window.setTimeout({ window.clearInterval(interval) }, ZOOM_ANIMATION_SPEED * ZOOM_ANIMATION_STEPS)
}


fun zoomUp() {

    val interval = window.setInterval({
        zoom *= ZOOM_STEP
        updateSvgGrid()
        updateZoomText()
    }, ZOOM_ANIMATION_SPEED)

    window.setTimeout({ window.clearInterval(interval) }, ZOOM_ANIMATION_SPEED * ZOOM_ANIMATION_STEPS)
}

private fun updateSvgGrid() {
    val smallGrid = document.getElementById("smallGrid")
    smallGrid?.setAttribute("width", zoom.toString())
    smallGrid?.setAttribute("height", zoom.toString())

    val smallGridPath = document.getElementById("smallGridPath")
    smallGridPath?.setAttribute("d", "M $zoom 0 L 0 0 0 $zoom")

    val zoomExtra = zoom * 10
    val grid = document.getElementById("grid")
    grid?.setAttribute("width", zoomExtra.toString())
    grid?.setAttribute("height", zoomExtra.toString())

    val gridRect = document.getElementById("gridRect")
    gridRect?.setAttribute("width", zoomExtra.toString())
    gridRect?.setAttribute("height", zoomExtra.toString())


    val gridPath = document.getElementById("gridPath")
    gridPath?.setAttribute("d", "M $zoomExtra 0 L 0 0 0 $zoomExtra")
}

private fun updateZoomText() {
    val element = document.getElementById("text")
    element?.innerHTML = zoom.toString()
}