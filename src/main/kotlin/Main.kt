import kotlin.concurrent.thread

@Volatile
var dir = Snake.Direction.UP

fun main(args: Array<String>) {
    val world = World()
    val snake = Snake(world, world.width / 2, world.height / 2)
    world.snake = snake
    world.draw()
    thread(isDaemon = true) {
        while (true) {
            val d: Char = System.`in`.read().toChar()
            when (d) {
                'w' -> dir = Snake.Direction.UP
                's' -> dir = Snake.Direction.DOWN
                'd' -> dir = Snake.Direction.RIGHT
                'a' -> dir = Snake.Direction.LEFT
            }
        }
    }
    while (world.running) {
        snake.move(dir)
        world.draw()
        world.checkCollisions()
        Thread.sleep(1000 / 10)
    }
    println("You Failed")
}