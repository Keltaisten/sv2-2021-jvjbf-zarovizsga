package shipping;

public class NationalPackage implements Transportable {
    private int weight;
    private boolean breakable;
    private static final int SHIPPING_PRICE = 1000;

    public NationalPackage(int weight, boolean breakable) {
        this.weight = weight;
        this.breakable = breakable;
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
        if (breakable) {
            return SHIPPING_PRICE * 2;
        } else {
            return SHIPPING_PRICE;
        }
    }
}
