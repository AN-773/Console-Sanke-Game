import java.util.Random

const val EMPTY_CHAR = "_"

class World(val width: Int = 32, val height: Int = 27) {
    val rand = Random(System.currentTimeMillis())
    lateinit var snake: Snake
    var food = Food(rand.nextInt(width), rand.nextInt(height))
    var running = true

    fun draw() {
        System.out.write(byteArrayOf(0x1b, '['.toByte(), 'H'.toByte()))
        System.out.flush()
        for (y in 0 until height) {
            for (x in 0 until width) {
                if (food.x == x && food.y == y) {
                    print(food.getChar())
                    continue
                } else {
                    print(EMPTY_CHAR)
                }
                var found = false
                for (pixel in snake.cells) {
                    if (pixel.x == x && pixel.y == y) {
                        print(pixel.getChar())
                        found = true
                        break
                    }
                }
                if (!found)
                    print(EMPTY_CHAR)
            }
            println()
        }
    }

        fun checkCollisions() {
            val snakeHead = snake.cells[0]
            if (snakeHead.x == food.x && snakeHead.y == food.y) {
                snake.addCell()
                food = Food(rand.nextInt(width), rand.nextInt(height))
                return
            }
            for (i in 1 until snake.cells.size) {
                val cell = snake.cells[i]
                if (snakeHead.x == cell.x && snakeHead.y == cell.y) {
                    running = false
                    return
                }
            }
        }


    }