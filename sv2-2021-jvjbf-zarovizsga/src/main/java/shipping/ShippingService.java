package shipping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ShippingService {
    private List<Transportable> packages = new ArrayList<>();

    public void addPackage(Transportable pack) {
        packages.add(pack);
    }

    public List<Transportable> getPackages() {
        return packages;
    }

    public List<Transportable> collectItemsByBreakableAndWeight(Boolean breakable, int weight) {
        return packages.stream().filter(p -> p.isBreakable() == breakable).filter(k -> k.getWeight() >= weight).toList();
    }

    public Map<String, Integer> collectTransportableByCountry() {
        Map<String, Integer> result = new TreeMap<>();
        for (Transportable t : packages) {
            String actualCountry = t.getDestinationCountry();
            if (!result.containsKey(actualCountry)) {
                result.put(actualCountry, 1);
            } else {
                result.put(actualCountry, result.get(actualCountry) + 1);
            }
        }
        return result;
//        packages.stream().forEach(p->p.);
    }

    public List<Transportable> sortInternationalPackagesByDistance() {
        return packages.stream().filter(InternationalPackage.class::isInstance).sorted().toList();
    }

//    private Map<String, InternationalPackage> collect(){
//
//    }
}
