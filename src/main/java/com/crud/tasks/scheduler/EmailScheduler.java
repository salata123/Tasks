package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import com.crud.tasks.service.TrelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.crud.tasks.service.TrelloService.SUBJECT;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private final SimpleEmailService simpleEmailService;
    private final TaskRepository taskRepository;
    private final AdminConfig adminConfig;
    private final TrelloService trelloService;

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        long size = taskRepository.count();
        String taskString = "task";
        if(trelloService.fetchTrelloBoards().size() > 1){
            taskString += "s";
        }
        simpleEmailService.send(
                new Mail(
                        adminConfig.getAdminMail(),
                        Optional.of("mailToCc"),
                        SUBJECT,
                        "Currently in database you got: " + size + " " + taskString
                )
        );
    }


}
