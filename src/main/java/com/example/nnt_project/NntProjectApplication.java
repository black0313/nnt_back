package com.example.nnt_project;

import com.example.nnt_project.bot.MyTelegramBot;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


@SpringBootApplication
public class NntProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(NntProjectApplication.class, args);

    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            try {
                MyTelegramBot myTelegramBot = ctx.getBean(MyTelegramBot.class);
                botsApi.registerBot(myTelegramBot);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        };
    }
}
