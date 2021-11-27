class Snake(val world: World, x: Int, y: Int) {

    val cells = arrayListOf<BodyCell>()

    init {
        cells.add(BodyCell(x, y, isHead = true))
    }

    fun addCell() {
        val lastCell = cells[cells.size - 1]
        cells.add(BodyCell(lastCell.x, lastCell.y, lastCell.dir, true))
        lastCell.isTail = false
    }

    fun move(d: Direction) {
        var direction = d
        val head = cells[0]
        if ((head.dir == Direction.LEFT && direction == Direction.RIGHT) ||  (direction == Direction.LEFT && head.dir == Direction.RIGHT) ||
            (head.dir == Direction.UP && direction == Direction.DOWN) ||  (direction == Direction.UP && head.dir == Direction.DOWN)
        )
            direction = head.dir
        var lastX = head.x
        var lastY = head.y
        var lastDir = direction
        when (direction) {
            Direction.UP -> {
                lastY = if (head.y - 1 == -1) world.height-1 else head.y - 1
            }
            Direction.DOWN -> {
                lastY = if (head.y + 1 == world.height) 0 else head.y + 1
            }
            Direction.RIGHT -> {
                lastX = if (head.x + 1 == world.width) 0 else head.x + 1
            }
            Direction.LEFT -> {
                lastX = if (head.x - 1 == -1) world.width-1 else head.x - 1
            }
        }
        for (cell in cells) {
            val lx = cell.x
            val ly = cell.y
            val ldir = cell.dir
            cell.x = lastX
            cell.y = lastY
            cell.dir = lastDir
            lastX = lx
            lastY = ly
            lastDir = ldir
        }
    }

    class BodyCell(
        x: Int,
        y: Int,
        var dir: Direction = Direction.UP,
        var isTail: Boolean = false,
        private val isHead: Boolean = false
    ) : Pixel(x, y) {


        override fun getChar(): String {
            return if (isHead) {
                when (dir) {
                    Direction.UP -> "^"
                    Direction.DOWN -> "∨"
                    Direction.LEFT -> "<"
                    Direction.RIGHT -> ">"
                }
            } else if (isTail) {
                "~"
            } else if (dir == Direction.UP || dir == Direction.DOWN) {
                "∥"
            } else {
                "="
            }
        }
    }

    enum class Direction {
        UP, DOWN, LEFT, RIGHT
    }
}