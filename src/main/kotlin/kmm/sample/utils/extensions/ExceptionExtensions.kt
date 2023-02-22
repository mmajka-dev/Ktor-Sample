package kmm.sample.utils.extensions

fun Exception.getMessage() = message ?: "Something went wrong"