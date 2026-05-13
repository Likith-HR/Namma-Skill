package com.example.nammaskill.models

data class CourseApplication(
    val name: String = "",
    val phone: String = "",
    val course_id: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
