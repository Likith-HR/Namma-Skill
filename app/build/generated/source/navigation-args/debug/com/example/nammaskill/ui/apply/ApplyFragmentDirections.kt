package com.example.nammaskill.ui.apply

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.nammaskill.R
import kotlin.Int
import kotlin.String

public class ApplyFragmentDirections private constructor() {
  private data class ActionApplyToSummary(
    public val name: String,
    public val phone: String,
    public val courseId: String
  ) : NavDirections {
    public override val actionId: Int = R.id.action_apply_to_summary

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("name", this.name)
        result.putString("phone", this.phone)
        result.putString("courseId", this.courseId)
        return result
      }
  }

  public companion object {
    public fun actionApplyToSummary(
      name: String,
      phone: String,
      courseId: String
    ): NavDirections = ActionApplyToSummary(name, phone, courseId)
  }
}
