import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Branch {
    private String branchName;
    private List<Laptop> laptopList;
    private int maxCapacity;

    public Branch(String branchName, int maxCapacity) {
        this.branchName = branchName;
        this.maxCapacity = maxCapacity;
        this.laptopList = new ArrayList<>();
    }

    public void addLaptop(Laptop laptop) {
        if (laptopList.size() >= maxCapacity) {
            System.err.println("blad: Przekroczono maksymalną pojemność oddziału " + branchName);
            return;
        }

        // Pętla range-based for (identyczna idea jak w C++: for (const auto& l : laptopList))
        for (Laptop l : laptopList) {
            if (l.compareTo(laptop) == 0) {
                System.out.println("Taki laptop juz istnieje w oddziale " + branchName);
                return;
            }
        }

        laptopList.add(laptop);
    }


    public void removeLaptop(Laptop laptop) {
        if (!laptopList.remove(laptop)) {
            System.out.println("nie ma takiego laptopa");
        }
    }

    public void sellLaptop(Laptop laptop) {
        if (laptopList.remove(laptop)) {
            System.out.println("Sprzedano laptop: " + laptop.getBrand() + " " + laptop.getModel());
        } else {
            System.out.println("Nie mozna sprzedać - brak laptopa na stanie.");
        }
    }


    public void changeCondition(Laptop laptop, LaptopCondition condition) {
        laptop.setCondition(condition);
    }

    public void changeYear(Laptop laptop, int year) {
        laptop.setYear(year);
    }

    public int countByCondition(LaptopCondition condition) {
        int count = 0;
        for (Laptop l : laptopList) {
            if (l.getCondition() == condition) { // dla enumów == jest w pełni bezpieczne
                count++;
            }
        }
        return count;
    }


    public void summary(){
        System.out.println("--- oddzial: " + branchName + " (" + laptopList.size() + "/" + maxCapacity + ") ---");
        if(laptopList.isEmpty()){
            System.out.println("nie ma laptopow ");
            return;
        }
        for(Laptop l: laptopList){
            l.print();
            System.out.println("\n ----- \n");
        }
    }

    public List<Laptop> sortByName() {
     List<Laptop> sorted = new ArrayList<>(laptopList);
     // Używamy lambdy porównującej modele dwóch laptopów (o1 i o2)
     sorted.sort((o1, o2) -> o1.getModel().compareToIgnoreCase(o2.getModel()));
     return sorted;
    }


    public List<Laptop> sortByPrice() {
     List<Laptop> sorted = new ArrayList<>(laptopList);
     sorted.sort(Comparator.comparingDouble(Laptop::getPrice));
     return sorted;
    }



    public Laptop search(String model) {
     Comparator<Laptop> modelComparator = Comparator.comparing(Laptop::getModel);

     List<Laptop> sorted = new ArrayList<>(laptopList);
     sorted.sort(modelComparator);

     Laptop key = new Laptop(model, "", LaptopCondition.IN_TESTING, 0, 0.0, 0, 0, 0);
     int index = Collections.binarySearch(sorted, key, modelComparator);

     if (index >= 0) {
         return sorted.get(index);
     }
     return null; 
    }


    public List<Laptop> searchPartial(String text) {
     List<Laptop> results = new ArrayList<>();
     String query = text.toLowerCase();

     for (Laptop l : laptopList) {
         if (l.getModel().toLowerCase().contains(query) || l.getBrand().toLowerCase().contains(query)) {
             results.add(l);
         }
     }
     return results;
    }


    public Laptop max() {
     if (laptopList.isEmpty()) {
         return null;
     }
     return Collections.max(laptopList, Comparator.comparingDouble(Laptop::getPrice));
    }



    public String getBranchName() {
        return branchName;
    }

    public List<Laptop> getLaptopList() {
        return laptopList;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }



}