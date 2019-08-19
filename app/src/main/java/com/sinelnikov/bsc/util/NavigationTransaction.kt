package com.sinelnikov.bsc.util

import androidx.annotation.IdRes

data class NavigationTransaction<out T>(@IdRes val resId: Int, val data: T?)