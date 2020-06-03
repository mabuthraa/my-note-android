package com.apipas.mynote.event

import androidx.navigation.NavDirections
import com.apipas.mynote.event.common.LiveEvent

class NavToDirection(val navDirections: NavDirections) : LiveEvent()