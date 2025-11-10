package com.sam.insuranceservice.infraestructure.persistence.repository;

import com.sam.insuranceservice.domain.model.enums.CustomerStatus;
import com.sam.insuranceservice.domain.model.enums.Status;
import com.sam.insuranceservice.infraestructure.persistence.entity.customer.CustomerEntity;
import com.sam.insuranceservice.infraestructure.util.constant.QueryConstants;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICustomerJpaRepository extends JpaRepository<CustomerEntity, UUID>,
        JpaSpecificationExecutor<CustomerEntity> {
    Optional<CustomerEntity> findByIdAndDeletedIsFalse(UUID id);
    Optional<CustomerEntity> findByPeopleDocumentNumberAndDeletedIsFalse(String documentNumber);
    Optional<CustomerEntity> findByEmailAndDeletedIsFalse(String email);
    List<CustomerEntity> findAllByDeletedIsFalse();
    List<CustomerEntity> findAllByAssignedAgentIdAndDeletedIsFalse(UUID assignedAgentId);

    @Transactional
    @Modifying
    @Query(QueryConstants.UPDATE_CUSTOMER_STATUS)
    int updateCustomerStatus(@Param("id") UUID id, @Param("status") Status status);

    @Transactional
    @Modifying
    @Query(QueryConstants.UPDATE_CUSTOMER_CUSTOMER_STATUS)
    int updateCustomerCustomerStatus(@Param("id") UUID id, @Param("customerStatus") CustomerStatus customerStatus);


}
