package libs;

import java.util.Random;

public class Generate {

    private static final String[] name = { "Macaulay", "Domonic", "Duncan", "Rayonder", "Breadin", "Monika", "Emmeline", "Zorking", "Rosebalt", "Marcokov", "Julian", "Rosemara", "Finley", "Werner", "Palacios", "Christos",};
    private static final String[] surName = { "Sanford", "Bakerin", "Alston", "Finley", "Yoderin", "Werner", "Duncan", "Lambert", "Palacios", "Zavala", "Handley" };

    private static final Random random = new Random();
    public static String userName() { return name[random.nextInt(name.length)] + random.nextInt(9999); }
    public static String name() { return name[random.nextInt(name.length)]; }
    public static String surName() { return surName[random.nextInt(surName.length)];}
    public static String email() { return "test" + random.nextInt(9999) + "@test.com"; }
}
