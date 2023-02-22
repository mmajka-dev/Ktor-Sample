package kmm.sample.utils.wrapper

sealed class ServiceResponse<Data, Error> {
    data class Success<Data, Error>(val data: Data): ServiceResponse<Data, Error>()
    data class Failure<Data, Error>(val error: Error): ServiceResponse<Data, Error>()

    val dataOrNull: Data?
        get() = (this as? Success)?.data
    val errorOrNull: Error?
        get() = (this as? Failure)?.error

    inline fun onSuccess(action: (Data) -> Unit): ServiceResponse<Data, Error> {
        if (this is Success) action(data)
        return this
    }

    inline fun onFailure(action: (Error) -> Unit): ServiceResponse<Data, Error> {
        if (this is Failure) action(error)
        return this
    }
    inline fun <R> map(onSuccess: (Data) -> R, onError: (Error) -> R): R =
        when (this) {
            is Success -> onSuccess(data)
            is Failure -> onError(error)
        }
}
