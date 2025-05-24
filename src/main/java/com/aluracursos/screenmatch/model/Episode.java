package com.aluracursos.screenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episode {
    private Integer episodeClassSeason;
    private String episdeClassTitle;
    private Integer episodeClassNumber;
    private Double episodeClassRating;
    private LocalDate episodeClassReleasedDate;

    public Episode(Integer episodeClassSeason, EpisodeInfo episodeInfo) {
        this.episodeClassSeason = episodeClassSeason;
        this.episdeClassTitle = episodeInfo.episodeTitle();
        this.episodeClassNumber = episodeInfo.episodeNumber();
        try {
            this.episodeClassRating = Double.valueOf(episodeInfo.episodeRating());
        } catch (NumberFormatException e){
            this.episodeClassRating = 0.0;
        }
        try{
            this.episodeClassReleasedDate = LocalDate.parse(episodeInfo.episodeReleasedDate());
        } catch (DateTimeParseException e){
            this.episodeClassReleasedDate = null;
        }
    }

    @Override
    public String toString() {
        return "Season=" + episodeClassSeason +
                ", Title='" + episdeClassTitle + '\'' +
                ", Episode=" + episodeClassNumber +
                ", Rating=" + episodeClassRating +
                ", ReleasedDate=" + episodeClassReleasedDate;
    }

    public Integer getEpisodeClassSeason() {
        return episodeClassSeason;
    }

    public Integer getEpisodeClassNumber() {
        return episodeClassNumber;
    }

    public LocalDate getEpisodeClassReleasedDate() {
        return episodeClassReleasedDate;
    }

    public String getEpisdeClassTitle() {
        return episdeClassTitle;
    }

    public Double getEpisodeClassRating() {
        return episodeClassRating;
    }
}
