package com.gamezface.domain.exception


sealed class RemoteException(message: String, code: Int) : AppBaseException(message, code)

class HttpClientErrorException(message: String, code: Int) : RemoteException(message, code)

class HttpServerErrorException(message: String, code: Int) : RemoteException(message, code)
