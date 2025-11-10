package com.sam.insuranceservice.infraestructure.util.constant;

public class QueryConstants {
    public final static String UPDATE_USER_STATUS = "UPDATE UserEntity u SET u.status = :status WHERE u.id = :id AND u.deleted = false";

    public final static String UPDATE_CUSTOMER_STATUS = "UPDATE CustomerEntity c SET c.status = :status WHERE c.id = :id AND c.deleted = false";
    public final static String UPDATE_CUSTOMER_CUSTOMER_STATUS = "UPDATE CustomerEntity c SET c.customerStatus = :customerStatus WHERE c.id = :id AND c.deleted = false";
}
