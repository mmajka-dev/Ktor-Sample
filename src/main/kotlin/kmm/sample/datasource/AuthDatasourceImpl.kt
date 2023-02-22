package kmm.sample.datasource

import kmm.sample.config.db.DatabaseFactory.dbQuery
import kmm.sample.model.db.entities.UserEntity
import kmm.sample.model.db.entities.toUserResponse
import kmm.sample.model.request.CreateUserRequest
import kmm.sample.model.request.LoginRequest
import kmm.sample.model.response.LoginResponse
import kmm.sample.model.response.UserResponse
import kmm.sample.config.auth.JWTConfig
import kmm.sample.utils.Encryptor
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import java.util.UUID

class AuthDatasourceImpl(
    private val encryptor: Encryptor
) : AuthDatasource {

    override suspend fun getUserByCredentials(request: LoginRequest): UserResponse? {
        return dbQuery {
            UserEntity.select {
                (UserEntity.username eq request.username) and (UserEntity.password eq encryptor.encrypt(request.password))
            }
                .firstOrNull()
                ?.toUserResponse()
        }
    }

    override suspend fun createUser(request: CreateUserRequest) {
        dbQuery {
            UserEntity.insert {
                it[uuid] = UUID.randomUUID().toString()
                it[username] = request.username
                it[email] = request.email
                it[password] = encryptor.encrypt(request.password)
                it[avatar] = request.imagePath
            }
        }
    }

    override suspend fun getUserByEmail(email: String): UserResponse? {
        return dbQuery {
            UserEntity.select {
                UserEntity.email eq email
            }
                .firstOrNull()
                ?.toUserResponse()
        }
    }
}