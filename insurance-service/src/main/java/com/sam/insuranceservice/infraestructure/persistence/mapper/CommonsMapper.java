package com.sam.insuranceservice.infraestructure.persistence.mapper;

import com.sam.insuranceservice.domain.model.BasePeople;
import com.sam.insuranceservice.infraestructure.persistence.entity.BasePeopleEmbeddable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommonsMapper {

    public BasePeopleEmbeddable toEmbeddable(BasePeople people) {
        BasePeopleEmbeddable embeddable = new BasePeopleEmbeddable();
        embeddable.setFirstName(people.firstName());
        embeddable.setLastName(people.lastName());
        embeddable.setDocumentType(people.documentType());
        embeddable.setDocumentNumber(people.documentNumber());
        embeddable.setBirthDate(people.birthDate());
        return embeddable;
    }

    public BasePeople toDomainRecord(BasePeopleEmbeddable embeddable) {
        return new BasePeople(
                embeddable.getFirstName(),
                embeddable.getLastName(),
                embeddable.getDocumentType(),
                embeddable.getDocumentNumber(),
                embeddable.getBirthDate()
        );
    }
}
