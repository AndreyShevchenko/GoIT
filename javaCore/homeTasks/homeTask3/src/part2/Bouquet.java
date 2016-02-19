package part2;

import java.util.HashSet;
import java.util.Set;

public class Bouquet {
    private Set<Flower> flowersBouquet;
    private int bouquetLength;
    private String bouquetColour;

    public Bouquet() {
        flowersBouquet = new HashSet<>();
    }

    public void setBouquet(int bouquetLength, String bouquetColour) {
        this.bouquetLength = bouquetLength;
        this.bouquetColour = bouquetColour;
    }

    public void addFlowers(String flowerType, int length, String colour) {
        Flower newFlower;
        try {
            switch (flowerType) {
                case "ASTER":
                    newFlower = new Aster(length, colour);
                    break;
                case "CHAMOMILE":
                    newFlower = new Chamomile(length, colour);
                    break;
                case "ROSE":
                    newFlower = new Rose(length, colour);
                    break;
                case "TULIP":
                    newFlower = new Tulip(length, colour);
                    break;
            }
            //flowersBouquet.add(newFlower);
            //newFlower.setFlower(length, colour);
            if (length != bouquetLength) throw new IncorrectLengthException(length);
            if (colour != bouquetColour) throw new IncorrectColourException(colour);

        }
        catch (IllegalArgumentException e) {
            System.out.println("[Error]: " + flowerType + " it's inappropriate type of flower");
        }
        catch (IncorrectLengthException e) {}
        catch (IncorrectColourException e) {}
        catch (RuntimeException e) {
            System.out.println("[Error]: verify the signature method");
        }
    }

}
