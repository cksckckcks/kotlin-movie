package movie

import movie.controller.MovieController
import movie.database.DatabaseInitializer

fun main() {
    DatabaseInitializer.initSchema()

    MovieController().run()
}
