package ca.thenetworknerds.APCS.lab13.vectorables;

import ca.thenetworknerds.APCS.lab13.operations.Addable;

import java.util.Random;

public class StringAdapter extends ExampleVector implements Addable<StringAdapter> {
    private String string;

    public StringAdapter() {
        string = "";
    }
    public StringAdapter(String string) {
        this.string = string;
    }

    @Override
    public StringAdapter add(StringAdapter other) {
        return new StringAdapter(string + other.string);
    }

    @Override
    public String getNature() {
        return "Concatenatable";
    }

    public StringAdapter createRandomValue() {
        final String alphabet = "`1234567890-=qwertyuiop[]\\asdfghjkl;'zxcvbnm,./~!@#$%^&*()_+QWERTYUIOP{}|ASDFGHJKL:\"ZXCVBNM<>?";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(5);
        for (int i = 0; i < 5; i++) {
            stringBuilder.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return new StringAdapter(stringBuilder.toString());
    }

    @Override
    public String toString() {
        return string;
    }

    @Override
    public int getPrintHeight() {
        return 1;
    }

    @Override
    public int getPrintWidth() {
        return string.length();
    }
}
