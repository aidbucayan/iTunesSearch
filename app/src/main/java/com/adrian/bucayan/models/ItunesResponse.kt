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
data class ItunesResponse (

        @field:SerializedName("resultCount")
        val resultCount: Integer? = null,

        @field:SerializedName("results")
        var results: List<Results>? = null

) : Serializable