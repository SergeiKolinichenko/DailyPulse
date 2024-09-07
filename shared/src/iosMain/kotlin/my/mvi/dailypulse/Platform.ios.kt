package my.mvi.dailypulse

import platform.UIKit.UIDevice

actual class Platform {
    actual val osName: String
        get() = UIDevice.currentDevice.systemName()
    actual val osVersion: String
        get() = UIDevice.currentDevice.systemVersion
    actual val deviceModel: String
        get() = UIDevice.currentDevice.model
    actual val density: Int
        get() = UIScreen.mainScreen.scale.toInt()

    actual fun logSystemInfo() {
        NSLog("OS Name: $osName OS Version: $osVersion Device Model: $deviceModel Density: $density")
    }

}