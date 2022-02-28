package shipping;

public class InternationalPackage implements Transportable, Comparable<InternationalPackage>{
    private int weight;
    private boolean breakable;
    private String destinationCountry;
    private int distance;
    private static final int SHIPPING_PRICE = 1200;
    private static final int SHIPPING_PRICE_PER_KM = 10;

    public InternationalPackage(int weight, boolean breakable, String destinationCountry, int distance) {
        this.weight = weight;
        this.breakable = breakable;
        this.destinationCountry = destinationCountry;
        this.distance = distance;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public boolean isBreakable() {
        return breakable;
    }

    @Override
    public int calculateShippingPrice() {
        int distancePrice = SHIPPING_PRICE_PER_KM * distance;
        if (breakable) {
            return SHIPPING_PRICE * 2 + distancePrice;
        } else {
            return SHIPPING_PRICE + distancePrice;
        }
    }

    @Override
    public String getDestinationCountry() {
        return destinationCountry;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public int compareTo(InternationalPackage o) {
        return distance - o.distance;
    }
}
