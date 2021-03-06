CREATE TABLE ROOMS
(
    ID                  NUMBER    NOT NULL,
    CONSTRAINT PK_ROOMS PRIMARY KEY (ID),
    NUMBER_OF_GUESTS    NUMBER    NOT NULL,
    PRICE               NUMBER    NOT NULL,
    -- it is a boolean field
    BREAKFAST_INCLUDED  NUMBER(1, 0) DEFAULT 0,
    CHECK (PETS_ALLOWED BETWEEN 0 AND 1),
    -- it is a boolean field
    PETS_ALLOWED        NUMBER(1, 0) DEFAULT 0,
    CHECK (PETS_ALLOWED BETWEEN 0 AND 1),
    DATE_AVAILABLE_FROM TIMESTAMP NOT NULL,
    HOTEL_ID            NUMBER    NOT NULL,
    CONSTRAINT FK_HOTELS_ROOMS FOREIGN KEY (HOTEL_ID) REFERENCES HOTELS (ID)
);