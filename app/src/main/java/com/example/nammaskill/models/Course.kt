package com.example.nammaskill.models

data class Course(
    val course_id: String = "",
    val title: String = "",
    val trade: String = "",
    val duration: String = "",
    val location: String = "",
    val eligibility: String = "",
    val job_guarantee: Boolean = false,
    val description: String = ""
)
