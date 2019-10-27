package winds.by.admire.social.windsserver.storage

open class StorageException : RuntimeException {

    constructor(message: String) : super(message) {}

    constructor(message: String, cause: Throwable) : super(message, cause) {}
}