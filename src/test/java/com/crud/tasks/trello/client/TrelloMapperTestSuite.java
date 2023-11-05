package com.crud.tasks.trello.client;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TrelloMapperTestSuite {
    @Autowired
    private TrelloMapper trelloMapper;
    private TrelloList trelloList;
    private TrelloListDto trelloListDto;
    private TrelloBoard trelloBoard;
    private TrelloBoardDto trelloBoardDto;
    private TrelloCardDto trelloCardDto;
    private TrelloCard trelloCard;
    private List<TrelloList> listTest;
    private List<TrelloBoard> trelloBoardList;
    private List<TrelloListDto> listTestDto;
    private List<TrelloBoardDto> trelloBoardListDto;

    @BeforeEach
    public void setUp() {
        trelloList = new TrelloList("TrelloList Id", "TrelloList Name", false);
        listTest = new ArrayList<>();
        listTest.add(trelloList);
        trelloBoard = new TrelloBoard("TrelloBoard Id", "TrelloBoard Name", listTest);

        trelloBoardList = new ArrayList<>();
        trelloBoardList.add(trelloBoard);

        trelloListDto = new TrelloListDto("TrelloListDto Id", "TrelloListDto Name", false);
        listTestDto = new ArrayList<>();
        listTestDto.add(trelloListDto);
        trelloBoardDto = new TrelloBoardDto("TrelloBoardDto Id", "TrelloBoardDto Name", listTestDto);

        trelloBoardListDto = new ArrayList<>();
        trelloBoardListDto.add(trelloBoardDto);

        trelloCardDto = new TrelloCardDto("TestCardDto1", "TestCardDto1 description", "TestCardDto1 pos", "TestCardDto1 ListID");
        trelloCard = new TrelloCard("TestCard1", "TestCard1 description", "TestCard1 pos", "TestCard1 ListID");
    }
    @Test
    public void shouldMapTrelloBoardDtoToTrelloBoard() {
        // When
        List<TrelloBoard> trelloBoardsToTest = trelloMapper.mapToBoards(trelloBoardListDto);

        // Then
        assertEquals(trelloBoardListDto.get(0).getId(), trelloBoardsToTest.get(0).getId());
        assertEquals(trelloBoardListDto.get(0).getName(), trelloBoardsToTest.get(0).getName());
        assertEquals(trelloBoardListDto.get(0).getLists().size(), trelloBoardsToTest.get(0).getLists().size());
    }

    @Test
    public void shouldMapTrelloBoardToTrelloBoardDto() {
        // When
        List<TrelloBoardDto> trelloBoardDtoToTest = trelloMapper.mapToBoardsDto(trelloBoardList);

        // Then
        assertEquals(trelloBoardList.get(0).getId(), trelloBoardDtoToTest.get(0).getId());
        assertEquals(trelloBoardList.get(0).getName(), trelloBoardDtoToTest.get(0).getName());
        assertEquals(trelloBoardList.get(0).getLists().size(), trelloBoardDtoToTest.get(0).getLists().size());
    }

    @Test
    public void shouldMapTrelloListDtoToTrelloList() {
        // When
        List<TrelloList> trelloListToTest = trelloMapper.mapToList(listTestDto);

        // Then
        assertEquals(listTestDto.get(0).getId(), trelloListToTest.get(0).getId());
        assertEquals(listTestDto.get(0).getName(), trelloListToTest.get(0).getName());
        assertEquals(listTestDto.get(0).isClosed(), trelloListToTest.get(0).isClosed());
    }

    @Test
    public void shouldMapTrelloListToTrelloListDto() {
        // When
        List<TrelloListDto> trelloListDtoToTest = trelloMapper.mapToListDto(listTest);

        // Then
        assertEquals(listTest.get(0).getId(), trelloListDtoToTest.get(0).getId());
        assertEquals(listTest.get(0).getName(), trelloListDtoToTest.get(0).getName());
        assertEquals(listTest.get(0).isClosed(), trelloListDtoToTest.get(0).isClosed());
    }

    @Test
    public void shouldMapTrelloCardToTrelloCardDto() {
        // When
        TrelloCardDto trelloCardDtoToTest = trelloMapper.mapToCardDto(trelloCard);

        // Then
        assertEquals(trelloCard.getName(), trelloCardDtoToTest.getName());
        assertEquals(trelloCard.getDescription(), trelloCardDtoToTest.getDescription());
        assertEquals(trelloCard.getPos(), trelloCardDtoToTest.getPos());
        assertEquals(trelloCard.getListId(), trelloCardDtoToTest.getListId());
    }

    @Test
    public void shouldMapTrelloCardDtoToTrelloCard() {
        // When
        TrelloCard trelloCardToTest = trelloMapper.mapToCard(trelloCardDto);

        // Then
        assertEquals(trelloCardDto.getName(), trelloCardToTest.getName());
        assertEquals(trelloCardDto.getDescription(), trelloCardToTest.getDescription());
        assertEquals(trelloCardDto.getPos(), trelloCardToTest.getPos());
        assertEquals(trelloCardDto.getListId(), trelloCardToTest.getListId());
    }
}
