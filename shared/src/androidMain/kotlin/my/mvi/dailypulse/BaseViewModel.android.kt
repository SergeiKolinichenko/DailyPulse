package my.mvi.dailypulse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

/** Created by Sergei Kolinichenko on 10.09.2024 at 11:41 (GMT+3) **/

actual open class BaseViewModel: ViewModel() {

  actual val scope = viewModelScope

}