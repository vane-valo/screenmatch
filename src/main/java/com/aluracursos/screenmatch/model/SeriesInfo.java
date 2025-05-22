package com.aluracursos.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeriesInfo(
        @JsonAlias("Title") String seriesTitle,
        @JsonAlias("totalSeasons") Integer seriesTotalOfSeasons,
        @JsonAlias("imdbRating") String seriesRating
) {
}
