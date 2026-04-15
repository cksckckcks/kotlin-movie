package movie

import movie.controller.MovieController
import movie.domain.MovieManager
import movie.mock.SchedulesMock

fun main() {
    MovieController(MovieManager(SchedulesMock.schedules)).run()
}
