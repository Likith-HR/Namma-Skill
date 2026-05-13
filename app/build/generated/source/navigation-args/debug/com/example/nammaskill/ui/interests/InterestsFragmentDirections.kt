package com.example.nammaskill.ui.interests

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.nammaskill.R

public class InterestsFragmentDirections private constructor() {
  public companion object {
    public fun actionInterestsToCourses(): NavDirections =
        ActionOnlyNavDirections(R.id.action_interests_to_courses)
  }
}
