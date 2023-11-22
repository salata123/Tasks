package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class TrelloService {
    private final TrelloClient trelloClient;
    private final SimpleEmailService emailService;
    private final AdminConfig adminConfig;
    public static final String SUBJECT = "Tasks: New Trello card";

    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }
    public CreatedTrelloCardDto createTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);
        ofNullable(newCard).ifPresent(card -> emailService.send(
                new Mail(
                        adminConfig.getAdminMail(),
                        Optional.of("mailToCc"),
                        SUBJECT,
                        "New card: " + trelloCardDto.getName() + " has been created on your Trello account"
                )));
        ofNullable(newCard).ifPresent(card -> emailService.sendDaily(
                new Mail(
                        adminConfig.getAdminMail(),
                        Optional.of("mailToCc"),
                        "Daily task count subscription",
                        "Today's task count"
                )));
        System.out.println(trelloCardDto.getName());
        return newCard;
    }



}