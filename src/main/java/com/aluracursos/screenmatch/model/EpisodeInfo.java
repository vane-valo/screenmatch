package com.aluracursos.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeInfo(
        @JsonAlias("Title") String episodeTitle,
        @JsonAlias("Episode") Integer episodeNumber,
        @JsonAlias("imdbRating") String episodeRating,
        @JsonAlias("Released") String episodeReleasedDate
        ) {
}
