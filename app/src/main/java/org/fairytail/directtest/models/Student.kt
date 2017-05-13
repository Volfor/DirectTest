package org.fairytail.directtest.models

import org.parceler.Parcel
import org.parceler.ParcelConstructor

/**
 * Created by Valentine on 5/13/2017.
 */
@Parcel(Parcel.Serialization.BEAN)
data class Student @ParcelConstructor constructor(
        val name: String
)
