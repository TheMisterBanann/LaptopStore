import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreManager {
    private Map<String, Branch> branches = new HashMap<>();

    public void addBranch(String name, int capacity) {
        if (branches.containsKey(name)) {
            System.out.println("Oddział o nazwie " + name + " już istnieje!");
            return;
        }
        branches.put(name, new Branch(name, capacity));
    }

    public void removeBranch(String name) {
        if (branches.remove(name) == null) {
            System.out.println("Nie znaleziono oddziału: " + name);
        } else {
            System.out.println("Usunięto oddział: " + name);
        }
    }

    public Branch getBranch(String name) {
        return branches.get(name);
    }

    public List<Branch> findEmpty() {
        List<Branch> emptyBranches = new ArrayList<>();
        for (Branch b : branches.values()) {
            if (b.getLaptopList().isEmpty()) {
                emptyBranches.add(b);
            }
        }
        return emptyBranches;
    }

    public void summary() {
        System.out.println("=== PODSUMOWANIE SIECI SKLEPÓW ===");
        if (branches.isEmpty()) {
            System.out.println("Brak oddziałów w sieci.");
            return;
        }
        for (Branch b : branches.values()) {
            double fillPercentage = ((double) b.getLaptopList().size() / b.getMaxCapacity()) * 100.0;
            System.out.printf("Oddział: %s | Zapełnienie: %.2f%% (%d/%d)\n",
                    b.getBranchName(), fillPercentage, b.getLaptopList().size(), b.getMaxCapacity());
        }
    }

    public void transferLaptop(Laptop laptop, String fromBranchName, String toBranchName) {
        Branch from = branches.get(fromBranchName);
        Branch to = branches.get(toBranchName);

        if (from == null || to == null) {
            System.err.println("Błąd transferu: Nieprawidłowa nazwa jednego z oddziałów.");
            return;
        }
        if (!from.getLaptopList().contains(laptop)) {
            System.out.println("Oddział " + fromBranchName + " nie posiada tego laptopa.");
            return;
        }
        if (to.getLaptopList().size() >= to.getMaxCapacity()) {
            System.err.println("Błąd transferu: Oddział docelowy " + toBranchName + " jest pełny.");
            return;
        }

        from.sellLaptop(laptop); 
        to.addLaptop(laptop); 
        System.out.println("Przeniesiono " + laptop.getModel() + " z " + fromBranchName + " do " + toBranchName);
    }

    public double totalValue(String branchName) {
        Branch b = branches.get(branchName);
        if (b == null) {
            System.out.println("Nie znaleziono oddziału: " + branchName);
            return 0.0;
        }
        double sum = 0.0;
        for (Laptop l : b.getLaptopList()) {
            sum += l.getPrice();
        }
        return sum;
    }
}