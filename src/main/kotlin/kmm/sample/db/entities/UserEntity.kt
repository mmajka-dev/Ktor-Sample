package kmm.sample.db.entities

import kmm.sample.db.Tables
import kmm.sample.model.response.UserResponse
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime

object UserEntity : Table(Tables.users) {
    val id = integer("id").uniqueIndex().autoIncrement()
    val uuid = varchar("uuid", 255)
    val username = varchar("username", 255)
    val email = varchar("email", 255)
    val password = varchar("password", 255)
    val avatar = varchar("avatar", 255).nullable()
    val createdAt = datetime("createdAt").clientDefault { DateTime.now() }
}

fun ResultRow.toUserResponse() = UserResponse(
    id = this[UserEntity.id],
    uuid = this[UserEntity.uuid],
    username = this[UserEntity.username],
    email = this[UserEntity.email],
    imagePath = this[UserEntity.avatar]
)