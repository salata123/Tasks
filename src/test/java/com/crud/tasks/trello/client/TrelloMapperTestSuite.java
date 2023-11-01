package com.crud.tasks.trello.client;


import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@TestComponent
public class TrelloMapperTestSuite{
    @Autowired
    private TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void TrelloMapperTest(){
        TrelloList trelloList = new TrelloList("TrelloList Id", "TrelloList Name", false);
        List<TrelloList> listTest = new ArrayList<>();
        listTest.add(trelloList);
        TrelloBoard trelloBoard = new TrelloBoard("TrelloBoard Id", "TrelloBoard Name", listTest);

        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(trelloBoard);

        TrelloListDto trelloListDto = new TrelloListDto("TrelloListDto Id", "TrelloListDto Name", false);
        List<TrelloListDto> listTestDto = new ArrayList<>();
        listTestDto.add(trelloListDto);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("TrelloBoardDto Id", "TrelloBoardDto Name", listTestDto);

        List<TrelloBoardDto> trelloBoardListDto = new ArrayList<>();
        trelloBoardListDto.add(trelloBoardDto);

        TrelloCardDto trelloCardDto = new TrelloCardDto("TestCardDto1", "TestCardDto1 description", "TestCardDto1 pos", "TestCardDto1 ListID");
        TrelloCard trelloCard = new TrelloCard("TestCard1", "TestCard1 description", "TestCard1 pos", "TestCard1 ListID");

        //When
        List<TrelloBoard> trelloBoardsToTest = trelloMapper.mapToBoards(trelloBoardListDto);
        List<TrelloBoardDto> trelloBoardDtoToTest = trelloMapper.mapToBoardsDto(trelloBoardList);

        List<TrelloList> trelloListToTest = trelloMapper.mapToList(listTestDto);
        List<TrelloListDto> trelloListDtoToTest = trelloMapper.mapToListDto(listTest);

        TrelloCardDto trelloCardDtoToTest = trelloMapper.mapToCardDto(trelloCard);
        TrelloCard trelloCardToTest = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals(trelloBoardsToTest.get(0).getName(), trelloBoardListDto.get(0).getName());
        assertEquals(trelloBoardsToTest.get(0).getId(), trelloBoardListDto.get(0).getId());
        assertEquals(trelloBoardsToTest.get(0).getLists().size(), trelloBoardListDto.get(0).getLists().size());

        assertEquals(trelloBoardDtoToTest.get(0).getName(), trelloBoardList.get(0).getName());
        assertEquals(trelloBoardDtoToTest.get(0).getId(), trelloBoardList.get(0).getId());
        assertEquals(trelloBoardDtoToTest.get(0).getLists().size(), trelloBoardList.get(0).getLists().size());

        assertEquals(trelloListToTest.get(0).getId(), listTestDto.get(0).getId());
        assertEquals(trelloListToTest.get(0).getName(), listTestDto.get(0).getName());

        assertEquals(trelloListDtoToTest.get(0).getId(), listTest.get(0).getId());
        assertEquals(trelloListDtoToTest.get(0).getName(), listTest.get(0).getName());

        assertEquals(trelloCardDtoToTest.getDescription(), trelloCard.getDescription());
        assertEquals(trelloCardDtoToTest.getName(), trelloCard.getName());
        assertEquals(trelloCardDtoToTest.getPos(), trelloCard.getPos());
        assertEquals(trelloCardDtoToTest.getListId(), trelloCard.getListId());

        assertEquals(trelloCardToTest.getDescription(), trelloCardDto.getDescription());
        assertEquals(trelloCardToTest.getName(), trelloCardDto.getName());
        assertEquals(trelloCardToTest.getPos(), trelloCardDto.getPos());
        assertEquals(trelloCardToTest.getListId(), trelloCardDto.getListId());
    }
}
