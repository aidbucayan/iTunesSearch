package com.adrian.bucayan.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Adrian Bucayan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum Media {
    movie, podcast, music, musicVideo, audiobook, shortFilm, tvShow, software, ebook, all
}
