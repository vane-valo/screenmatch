package com.aluracursos.screenmatch.main;

import com.aluracursos.screenmatch.model.Episode;
import com.aluracursos.screenmatch.model.EpisodeInfo;
import com.aluracursos.screenmatch.model.SeasonInfo;
import com.aluracursos.screenmatch.model.SeriesInfo;
import com.aluracursos.screenmatch.service.ConsumeAPI;
import com.aluracursos.screenmatch.service.DataConverter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private ConsumeAPI consumeApi = new ConsumeAPI();
    private DataConverter converter = new DataConverter();
    private final String BASE_URL = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=84ace5c3";
    public void userEntry(){
        System.out.println("Please write the wished Series: ");
        var userInputSeries = scanner.next();
        var json = consumeApi.obtainData(BASE_URL + userInputSeries.replace(" ","+") + API_KEY);

        SeriesInfo series = converter.obtainData(json, SeriesInfo.class);

        System.out.println(series);

        List<SeasonInfo> seasonsList = new ArrayList<>();

        for (int i = 1; i < series.seriesTotalOfSeasons(); i++) {
            json = consumeApi.obtainData(BASE_URL + userInputSeries.replace(" ","+")+ "&Season=" + i + API_KEY);
            SeasonInfo season = converter.obtainData(json, SeasonInfo.class);
            seasonsList.add(season);
        }
        //seasonsList.forEach(System.out::println);

        //seasonsList.forEach(s -> s.seasonEpisodes().forEach(e -> System.out.println(e.episodeTitle())));

        List<EpisodeInfo> episodeInfo = seasonsList.stream()
                .flatMap(s -> s.seasonEpisodes().stream())
                .collect(Collectors.toList());

        System.out.println("Top 5 episodes: ");

        episodeInfo.stream()
                .filter(e -> !e.episodeRating().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(EpisodeInfo::episodeRating).reversed())
                .limit(5)
                .forEach(System.out::println);

        //System.out.println("Every episode on each season: ");
        List<Episode> episodesList = seasonsList.stream()
                .flatMap(s-> s.seasonEpisodes().stream()
                        .map(d -> new Episode(s.seasonNumber(), d)))
                .collect(Collectors.toList());

        //episodesList.forEach(System.out::println);

        System.out.println("From what released year you want to see: ");
        var yearDate = scanner.nextInt();
        scanner.nextLine();

        LocalDate searchDate = LocalDate.of(yearDate, 1,1);

        DateTimeFormatter dft = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        episodesList.stream()
                .filter(e -> e.getEpisodeClassReleasedDate() != null && e.getEpisodeClassReleasedDate().isAfter(searchDate))
                .forEach(e -> System.out.println(
                        "Season= " + e.getEpisodeClassSeason() +
                                " Episode= " + e.getEpisodeClassNumber() +
                                " ReleasedDate= " + e.getEpisodeClassReleasedDate().format(dft)
                ));
    }
}
