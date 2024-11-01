package my.mvi.dailypulse

import kotlinx.coroutines.CoroutineScope

expect open class BaseViewModel() {
    val scope: CoroutineScope
}