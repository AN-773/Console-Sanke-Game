class Food(x: Int, y: Int): Pixel(x, y) {
    var show = false
    override fun getChar(): String {
        show = !show
        return if (show) "*" else EMPTY_CHAR
    }
}