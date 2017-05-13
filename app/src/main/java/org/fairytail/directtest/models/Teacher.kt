package org.fairytail.directtest.models

import org.parceler.Parcel
import org.parceler.ParcelConstructor

/**
 * Created by Alex on 5/13/2017.
 * GitHub: https://github.com/s0nerik
 * LinkedIn: https://linkedin.com/in/sonerik
 */
@Parcel(Parcel.Serialization.BEAN)
data class Teacher @ParcelConstructor constructor(
        val name: String
)