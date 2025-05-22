package com.aluracursos.screenmatch.main;

import com.aluracursos.screenmatch.model.SeasonInfo;
import com.aluracursos.screenmatch.model.SeriesInfo;
import com.aluracursos.screenmatch.service.ConsumeAPI;
import com.aluracursos.screenmatch.service.DataConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

        seasonsList.forEach(s -> s.seasonEpisodes().forEach(e -> System.out.println(e.episodeTitle())));
    }
}
