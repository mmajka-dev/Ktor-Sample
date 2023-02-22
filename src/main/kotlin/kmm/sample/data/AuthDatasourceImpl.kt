package kmm.sample.data

import kmm.sample.db.DatabaseFactory.dbQuery
import kmm.sample.db.entities.UserEntity
import kmm.sample.db.entities.toUserResponse
import kmm.sample.model.request.CreateUserRequest
import kmm.sample.model.request.LoginRequest
import kmm.sample.model.response.LoginResponse
import kmm.sample.model.response.UserResponse
import kmm.sample.plugins.security.JWTConfig
import kmm.sample.utils.Encryptor
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import java.util.UUID

class AuthDatasourceImpl(
    private val encryptor: Encryptor
) : AuthDatasource {

    override suspend fun signIn(request: LoginRequest): LoginResponse? {
        val user = dbQuery {
            UserEntity.select {
                (UserEntity.username eq request.username) and (UserEntity.password eq encryptor.encrypt(request.password))
            }
                .firstOrNull()
                ?.toUserResponse()
        }

        return if (user == null) {
            null
        } else {
            LoginResponse(
                JWTConfig.instance.createAccessToken(user.id)
            )
        }
    }

    override suspend fun signUp(request: CreateUserRequest) {
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