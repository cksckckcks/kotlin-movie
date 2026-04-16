package movie.database

object DatabaseInitializer {
    fun initSchema() {
        val connection = DatabaseFactory.getConnection()
        val statement = connection.createStatement()

        val checkTableResultSet = connection.metaData.getTables(null, null, "MOVIE", null)
        val isFirstRun = !checkTableResultSet.next()

        if (isFirstRun) {
            val schemaSql = this::class.java.classLoader.getResource("movie.sql")?.readText()
                ?: throw IllegalStateException("movie.sql 파일을 찾을 수 없습니다.")
            statement.execute(schemaSql)

            val dataSql = this::class.java.classLoader.getResource("movie_data.sql")?.readText()
                ?: throw IllegalStateException("movie_data.sql 파일을 찾을 수 없습니다.")
            statement.execute(dataSql)
        }

        statement.close()
        connection.close()
    }
}
