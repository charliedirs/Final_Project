import java.io.Serializable;

public class Tree implements Serializable {
    private TreeSpecies species;
    private int yearOfPlanting;
    private double height;
    private double growthRate;

    public Tree(TreeSpecies species, int yearOfPlanting, double height, double growthRate) {
        this.species = species;
        this.yearOfPlanting = yearOfPlanting;
        this.height = height;
        this.growthRate = growthRate;
    }

    public TreeSpecies getSpecies() {
        return species;
    }

    public int getYearOfPlanting() {
        return yearOfPlanting;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight( double height) {
        this.height = height;
    }

    public double getGrowthRate() {
        return growthRate;
    }

    public void simulateYear() {
        height *= (1 + growthRate);
    }

    @Override
    public String toString() {
        return String.format("%-6s %-6d %6.2f' %6.1f%%", species, yearOfPlanting, height, growthRate);
    }


    public String toCSV() {
        String str = species.toString();
        str = str.charAt(0) + str.substring(1).toLowerCase();
        return String.format("%s,%d,%.2f,%.1f\n", str, yearOfPlanting, height, growthRate);

    }

}
