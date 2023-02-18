package kmm.sample.db

import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    private val appConfig = HoconApplicationConfig(ConfigFactory.load())
    private lateinit var database: Database

    fun init(): Database {
        val driver = appConfig.property("db.driver").getString()
        val url = appConfig.property("db.url").getString()
        val user = appConfig.property("db.user").getString()
        val password = appConfig.property("db.password").getString()

        try {
            database = Database.connect(url, driver,user, password)
        }catch (e: Exception){
            println("OTRS_ERR: Database connection error ${e.message}")
        }
        return database
    }

    suspend fun <T> dbQuery(block: () -> T): T = withContext(Dispatchers.IO) {
        transaction {
            block()
        }
    }
}