package ru.geekbrains.notes;

public interface CardsSource {
    CardData getCardData(int position);
    int size();
    void deleteCarData(int position);
    void updateCardData(int position, CardData cardData);
    void addCardData(CardData cardData);
    void clearCardData();


}
