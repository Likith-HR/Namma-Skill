package com.example.nammaskill.ui.apply

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class SummaryFragmentArgs(
  public val name: String,
  public val phone: String,
  public val courseId: String
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("name", this.name)
    result.putString("phone", this.phone)
    result.putString("courseId", this.courseId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("name", this.name)
    result.set("phone", this.phone)
    result.set("courseId", this.courseId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): SummaryFragmentArgs {
      bundle.setClassLoader(SummaryFragmentArgs::class.java.classLoader)
      val __name : String?
      if (bundle.containsKey("name")) {
        __name = bundle.getString("name")
        if (__name == null) {
          throw IllegalArgumentException("Argument \"name\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"name\" is missing and does not have an android:defaultValue")
      }
      val __phone : String?
      if (bundle.containsKey("phone")) {
        __phone = bundle.getString("phone")
        if (__phone == null) {
          throw IllegalArgumentException("Argument \"phone\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"phone\" is missing and does not have an android:defaultValue")
      }
      val __courseId : String?
      if (bundle.containsKey("courseId")) {
        __courseId = bundle.getString("courseId")
        if (__courseId == null) {
          throw IllegalArgumentException("Argument \"courseId\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"courseId\" is missing and does not have an android:defaultValue")
      }
      return SummaryFragmentArgs(__name, __phone, __courseId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): SummaryFragmentArgs {
      val __name : String?
      if (savedStateHandle.contains("name")) {
        __name = savedStateHandle["name"]
        if (__name == null) {
          throw IllegalArgumentException("Argument \"name\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"name\" is missing and does not have an android:defaultValue")
      }
      val __phone : String?
      if (savedStateHandle.contains("phone")) {
        __phone = savedStateHandle["phone"]
        if (__phone == null) {
          throw IllegalArgumentException("Argument \"phone\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"phone\" is missing and does not have an android:defaultValue")
      }
      val __courseId : String?
      if (savedStateHandle.contains("courseId")) {
        __courseId = savedStateHandle["courseId"]
        if (__courseId == null) {
          throw IllegalArgumentException("Argument \"courseId\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"courseId\" is missing and does not have an android:defaultValue")
      }
      return SummaryFragmentArgs(__name, __phone, __courseId)
    }
  }
}
