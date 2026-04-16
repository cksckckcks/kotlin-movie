package movie

import movie.controller.MovieController
import movie.database.DatabaseInitializer
import movie.repository.ScheduleRepository
import movie.service.ScheduleService

fun main() {
    DatabaseInitializer.initSchema()

    val scheduleRepository = ScheduleRepository()
    val scheduleService = ScheduleService(scheduleRepository)

    MovieController(scheduleService).run()
}
