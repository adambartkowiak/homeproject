import model.Point
import model.Wall
import org.w3c.dom.events.MouseEvent
import org.w3c.dom.svg.SVGLineElement
import kotlin.browser.*
import kotlin.dom.appendElement

const val ZOOM_STEP = 1.015
const val ZOOM_ANIMATION_SPEED = 5
const val ZOOM_ANIMATION_STEPS = 20
const val GRID_SCALE = 20

const val GRID_PATH_PATTERN = "M 10 0 L 0 0 0 10"
const val TRIANGLE_PATTERN = "22,1 30,21 17,25 12,23"

var zoom: Double = 5.0
var wall: Wall? = null

fun main(args: Array<String>) {
    setActionOnButtons()
    updateZoomText()

    val svgRoot = document.getElementById("svg_root")
    svgRoot?.addEventListener("mousedown", {
        it as MouseEvent

        if (wall == null) {
            val p1 = Point(it.clientX.toDouble(), it.clientY.toDouble())
            wall = Wall(p1)
        } else {
            val p2 = Point(it.clientX.toDouble(), it.clientY.toDouble())
            wall?.p2 = p2


            val line = document.createElementNS("http://www.w3.org/2000/svg","line")

            line.setAttribute("x1", wall?.p1?.x?.toString()?:"0")
            line.setAttribute("y1", wall?.p1?.y?.toString()?:"0")
            line.setAttribute("x2", wall?.p2?.x?.toString()?:"0")
            line.setAttribute("y2", wall?.p2?.y?.toString()?:"0")
            line.setAttribute("style", "stroke:rgb(255,0,0);stroke-width:2")

            svgRoot.appendChild(line)

            wall = null
        }
    })
}


fun setActionOnButtons() {
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
    smallGridPath?.setAttribute("d", scalePath(zoom, GRID_PATH_PATTERN))

    val zoomExtra = zoom * GRID_SCALE
    val grid = document.getElementById("grid")
    grid?.setAttribute("width", zoomExtra.toString())
    grid?.setAttribute("height", zoomExtra.toString())

    val gridRect = document.getElementById("gridRect")
    gridRect?.setAttribute("width", zoomExtra.toString())
    gridRect?.setAttribute("height", zoomExtra.toString())

    val gridPath = document.getElementById("gridPath")
    gridPath?.setAttribute("d", scalePath(zoomExtra, GRID_PATH_PATTERN))

    val wall1 = document.getElementById("wall_1")
    wall1?.setAttribute("points", scalePath(zoom, TRIANGLE_PATTERN))
}

private fun scalePath(scale: Double, pattern: String?): String {
    var resizedPattern = ""
    val regex = "\\d+(e\\+)?\\.?(\\d+)?|.".toRegex()
    if (pattern != null) {
        regex.findAll(pattern).forEach { s ->
            var number: Double? = s.value.toDoubleOrNull()
            if (number != null) {
                number *= scale
                println(number)
                resizedPattern += number.toString()
            } else {
                println(s.value)
                resizedPattern += s.value
            }
        }
    }

    return resizedPattern
}

private fun updateZoomText() {
    val element = document.getElementById("text")
    element?.innerHTML = zoom.toString()
}