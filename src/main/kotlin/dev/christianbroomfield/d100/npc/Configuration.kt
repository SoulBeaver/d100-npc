package dev.christianbroomfield.d100.npc

data class D100Configuration(
    val debug: Boolean = false,
    val port: Int = 9000,
    val startup: StartupConfiguration = StartupConfiguration()
)

data class StartupConfiguration(
    val enabled: Boolean = false,
    val clearCollection: Boolean = true,
    val dataDirectory: String = ""
)
