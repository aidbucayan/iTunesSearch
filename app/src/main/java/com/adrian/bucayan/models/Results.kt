package com.adrian.bucayan.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @author Adrian Bucayan
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Results (

    @field:SerializedName("wrapperType")
    val wrapperType: String? = null,

    @field:SerializedName("kind")
    val kind: String? = null,

    @field:SerializedName("artistId")
    val artistId: Int? = null,

    @field:SerializedName("collectionId")
    val collectionId: Int? = null,

    @field:SerializedName("trackId")
    val trackId: Int? = null,

    @field:SerializedName("artistName")
    val artistName: String? = null,

    @field:SerializedName("collectionName")
    val collectionName: String? = null,

    @field:SerializedName("trackName")
    val trackName: String? = null,

    @field:SerializedName("collectionCensoredName")
    val collectionCensoredName: String? = null,

    @field:SerializedName("trackCensoredName")
    val trackCensoredName: String? = null,

    @field:SerializedName("artistViewUrl")
    val artistViewUrl: String? = null,

    @field:SerializedName("collectionViewUrl")
    val collectionViewUrl: String? = null,

    @field:SerializedName("trackViewUrl")
    val trackViewUrl: String? = null,

    @field:SerializedName("previewUrl")
    val previewUrl: String? = null,

    @field:SerializedName("artworkUrl30")
    val artworkUrl30: String? = null,

    @field:SerializedName("artworkUrl60")
    val artworkUrl60: String? = null,

    @field:SerializedName("artworkUrl100")
    val artworkUrl100: String? = null,

    @field:SerializedName("collectionPrice")
    val collectionPrice: Float? = null,

    @field:SerializedName("trackPrice")
    val trackPrice: Float? = null,

    @field:SerializedName("collectionExplicitness")
    val collectionExplicitness: String? = null,

    @field:SerializedName("trackExplicitness")
    val trackExplicitness: String? = null,

    @field:SerializedName("discCount")
    val discCount: Int? = null,

    @field:SerializedName("discNumber")
    val discNumber: Int? = null,

    @field:SerializedName("trackCount")
    val trackCount: Int? = null,

    @field:SerializedName("trackNumber")
    val trackNumber: Int? = null,

    @field:SerializedName("trackTimeMillis")
    val trackTimeMillis: Int? = null,

    @field:SerializedName("country")
    val country: String? = null,

    @field:SerializedName("currency")
    val currency: String? = null,

    @field:SerializedName("primaryGenreName")
    val primaryGenreName: String? = null,

    @field:SerializedName("isStreamable")
    val isStreamable: Boolean? = null

) : Serializable