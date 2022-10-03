package ru.geekbrains.notes;

import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

public class CardsSourceImpl implements CardsSource {

    private final List<CardData> dataSource;
    private final Resources resources;

    public CardsSourceImpl(Resources resources) {
        dataSource = new ArrayList<>(10);
        this.resources = resources;
    }

    public CardsSourceImpl init() {
        String[] titles = resources.getStringArray(R.array.titlesForCards);
        int[] pictures = getImageArray();

        for (int i = 0; i < titles.length; i++) {
            dataSource.add(new CardData(pictures[i], titles[i]));
        }
        return this;
    }

    private int[] getImageArray() {
        TypedArray pictures = resources.obtainTypedArray(R.array.picturesForCards);
        int length = pictures.length();
        int[] answer = new int[length];
        for (int i = 0; i < length; i++) {
            answer[i] = pictures.getResourceId(i, 0);
        }
        return answer;
    }

    public CardData getCardData(int position) {
        return dataSource.get(position);
    }

    public int size() {
        return dataSource.size();
    }

    @Override
    public void deleteCarData(int position) {
        dataSource.remove(position);
    }

    @Override
    public void updateCardData(int position, CardData cardData) {
        dataSource.set(position, cardData);
    }

    @Override
    public void addCardData(CardData cardData) {
        dataSource.add(cardData);
    }

    @Override
    public void clearCardData() {
        dataSource.clear();
    }


}























