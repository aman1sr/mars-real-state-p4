

package com.example.android.marsrealestate.network

import com.squareup.moshi.Json

/**
 * This data class defines a Mars property which includes an ID, the image URL, the type (sale
 * or rental) and the price (monthly if it's a rental).
 * The property names of this data class are used by Moshi to match the names of values in JSON.
 *
 *
 * When Moshi parses the JSON, it matches the keys by name and fills the data objects with appropriate values.
 */
data class MarsProperty(
        val id: String,
        // used to map img_src from the JSON to imgSrcUrl in our class
        @Json(name = "img_src") val imgSrcUrl: String,
        val type: String,
        val price: Double){

        val isRental
        get() = type == "rent"
}