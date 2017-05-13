package org.fairytail.directtest

import android.os.Build
import com.chibatching.kotpref.KotprefModel
import com.chibatching.kotpref.gsonpref.gsonPref
import org.fairytail.directtest.models.Student
import org.fairytail.directtest.models.Teacher

/**
 * Created by Alex on 5/13/2017.
 * GitHub: https://github.com/s0nerik
 * LinkedIn: https://linkedin.com/in/sonerik
 */
object Prefs : KotprefModel() {
    var studentInfo: Student by gsonPref(Student(Build.MODEL))
    var teacherInfo: Teacher by gsonPref(Teacher(Build.MODEL))
}
