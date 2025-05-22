package com.aluracursos.screenmatch.model;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeasonInfo(
        @JsonAlias("Season") Integer seasonNumber,
        @JsonAlias("Episodes") List<EpisodeInfo> seasonEpisodes
) {
}
