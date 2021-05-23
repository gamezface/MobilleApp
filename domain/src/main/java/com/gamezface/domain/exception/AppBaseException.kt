package com.gamezface.domain.exception

abstract class AppBaseException : Exception {
    override val message: String?
        get() = super.message.orEmpty()
    private var code: Int? = null

    constructor(message: String?, code: Int) : super(message) {
        this.code = code
    }

    constructor(message: String?, cause: Throwable) : super(message, cause)
}