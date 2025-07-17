package de.twisssow.adventofcode.mmxv.day2;

public class Box {
    int length;
    int width;
    int height;

    int bottomAndTopArea;
    int leftAndRightArea;
    int frontAndBackArea;

    int perimeterBottomAndTop;
    int perimeterLeftAndRight;
    int perimeterFrontAndBack;

    public Box(String  dimensions) {

        String[] parts = dimensions.split("x");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid box dimensions: " + dimensions);
        }
        this.length = Integer.parseInt(parts[0]);
        this.width = Integer.parseInt(parts[1]);
        this.height = Integer.parseInt(parts[2]);

        this.bottomAndTopArea = length * width;
        this.leftAndRightArea = length * height;
        this.frontAndBackArea = width * height;

        this.perimeterBottomAndTop = 2 * (length + width);
        this.perimeterLeftAndRight = 2 * (length + height);
        this.perimeterFrontAndBack = 2 * (width + height);

    }

    public int surfaceArea() {
        return 2 * (bottomAndTopArea + leftAndRightArea + frontAndBackArea);
    }

    public int wrappedArea() {
        int smallestArea = Math.min(Math.min(bottomAndTopArea, leftAndRightArea), frontAndBackArea);
        return surfaceArea() + smallestArea;
    }

    public int ribbonLength() {
        int smallestPerimeter = Math.min(Math.min(perimeterBottomAndTop, perimeterLeftAndRight), perimeterFrontAndBack);
        return smallestPerimeter + (length * width * height);
    }

}
