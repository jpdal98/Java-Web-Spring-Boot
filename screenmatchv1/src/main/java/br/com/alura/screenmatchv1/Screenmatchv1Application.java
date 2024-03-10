package br.com.alura.screenmatchv1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Screenmatchv1Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Screenmatchv1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Primeiro projeto spring sem web!");
	}
}
