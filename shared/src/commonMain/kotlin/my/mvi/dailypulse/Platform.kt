package my.mvi.dailypulse

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform