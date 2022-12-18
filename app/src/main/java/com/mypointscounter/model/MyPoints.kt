package com.mypointscounter.model

data class MyPoints(var id: Int?, var points: Int, var date: String, var hour: String) {

    init {
        this.id = id;
        this.points = points;
        this.date = date;
        this.hour = hour;
    }

    constructor(points: Int, date: String, hour: String) : this(null, points,date, hour);
}