package com.aluracursos.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SerieInfo(
        @JsonAlias("Title") String title,
        @JsonAlias("totalSeasons") Integer totalOfSeasons,
        @JsonAlias("imdbRating") String rating
) {
}
