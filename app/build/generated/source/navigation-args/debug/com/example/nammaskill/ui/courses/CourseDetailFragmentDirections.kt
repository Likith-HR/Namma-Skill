package com.example.nammaskill.ui.courses

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.nammaskill.R
import kotlin.Int
import kotlin.String

public class CourseDetailFragmentDirections private constructor() {
  private data class ActionDetailToApply(
    public val courseId: String
  ) : NavDirections {
    public override val actionId: Int = R.id.action_detail_to_apply

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("courseId", this.courseId)
        return result
      }
  }

  public companion object {
    public fun actionDetailToApply(courseId: String): NavDirections = ActionDetailToApply(courseId)
  }
}
