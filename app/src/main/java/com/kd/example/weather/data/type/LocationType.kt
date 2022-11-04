package com.kd.example.weather.data.type

sealed class LocationType(val name:String, val lat:Double, val lon:Double) {
    object London:LocationType("London",0.0,0.0)
    object Seoul:LocationType("Seoul",0.0,0.0)
    object Chicago:LocationType("Chicago",0.0,0.0)



}