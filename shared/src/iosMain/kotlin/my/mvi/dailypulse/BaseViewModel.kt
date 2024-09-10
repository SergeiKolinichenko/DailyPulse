package my.mvi.dailypulse

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.cancel

/** Created by Sergei Kolinichenko on 10.09.2024 at 18:07 (GMT+3) **/

class BaseViewModel {

  val scope = CoroutineScope(Dispatchers.IO)

  fun clear() {
    scope.cancel()
  }

}