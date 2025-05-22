package com.aluracursos.screenmatch;

import com.aluracursos.screenmatch.model.SerieInfo;
import com.aluracursos.screenmatch.service.ConsumeAPI;
import com.aluracursos.screenmatch.service.DataConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println("First not Web Spring project");
		var consumeApi = new ConsumeAPI();
		var json = consumeApi.obtainData("https://www.omdbapi.com/?t=Skins&apikey=84ace5c3");

		System.out.println(json);

		DataConverter converter = new DataConverter();
		SerieInfo info = converter.obtainData(json, SerieInfo.class);

		System.out.println(info);
	}
}
