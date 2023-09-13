package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.security.jarsigner.JarSigner;

public class TrelloAttachmentByTypeDto {
    @JsonProperty("trello")
    private TrelloTrelloDto trello;
}
