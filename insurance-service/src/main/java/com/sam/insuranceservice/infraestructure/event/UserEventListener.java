package com.sam.insuranceservice.infraestructure.event;

import com.sam.insuranceservice.application.service.IUserApplicationService;
import com.sam.insuranceservice.infraestructure.event.dto.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEventListener {

    /*private final IUserApplicationService _userApplicationService;

    @RabbitListener(queues = "${rabbitmq.user.queue}")
    public void handleUserCreatedEvent(UserCreatedEvent event) {
        try {
            UserCreationRequest request = new UserCreationRequest(
                    event.autUserId(),
                    event.firstName(),
                    event.lastName(),
                    event.documentType(),
                    event.documentNumber(),
                    event.birthDate(),
                    event.phoneNumber(),
                    event.jobTitle(),
                    null
            );
            _userApplicationService.createUser(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/

}
