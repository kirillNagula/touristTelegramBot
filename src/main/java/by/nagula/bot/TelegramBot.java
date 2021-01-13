package by.nagula.bot;

import by.nagula.entity.City;
import by.nagula.service.CitiesService;
import by.nagula.service.exception.CityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@PropertySource("classpath:telegram.properties")
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    private final CitiesService citiesService;

    public TelegramBot(CitiesService citiesService) {
        this.citiesService = citiesService;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onRegister() {
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.getMessage().hasText() || !update.hasMessage())
            return;
        String text = update.getMessage().getText();
        String chatId = update.getMessage().getChatId().toString();
        String answer;
        try {
            City city = citiesService.showCityByName(text);
            answer = city.getDescription();
        } catch (CityNotFoundException ex) {
            answer = ex.getMessage();
        }

        switch (text){
            case "/start": answer = "Привет. Я туристический бот!";
            break;
            case "/help": answer = "Для получения информации вы должны написать название города";
            break;
        }

        try {
            execute(new SendMessage(chatId, answer));
            execute(new SendMessage(chatId, "О каком городе мне рассказать?"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
