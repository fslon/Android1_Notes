package ru.geekbrains.notes;

public interface CardsSource {
    CardData getCardData(int position);
    int size();

}
