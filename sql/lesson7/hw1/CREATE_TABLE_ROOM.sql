CREATE TABLE ROOM
(
    ID                    NUMBER,
    CONSTRAINT PK_ROOM PRIMARY KEY (ID),
    NUMBER_OF_GUESTS      NUMBER,
    PRICE                 NUMBER,
    BREAKFAST_INCLUDED    NUMBER,
    PETS_ALLOWED          NUMBER,
    DATE_AVAILABLE_FROM TIMESTAMP,
    HOTEL_ID              NUMBER,
    CONSTRAINT FK_HOTEL FOREIGN KEY (HOTEL_ID) REFERENCES HOTEL (ID)
);