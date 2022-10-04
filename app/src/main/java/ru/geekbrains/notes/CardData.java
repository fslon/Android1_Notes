package ru.geekbrains.notes;

public class CardData {
    private final int picture;
    private final String title;


    public CardData(int picture, String title) {
        this.picture = picture;
        this.title = title;
    }

    public int getPicture() {
        return picture;
    }

    public String getTitle() {
        return title;
    }

}
