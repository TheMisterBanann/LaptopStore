import java.util.List;

public class Main { // jeśli plik nazywa się App.java, zostaw: public class App
    public static void main(String[] args) {
        // 1. Tworzymy menedżera i oddziały
        StoreManager manager = new StoreManager();
        manager.addBranch("Kraków Centrum", 3);
        manager.addBranch("Warszawa Mokotów", 2);
        manager.addBranch("Gdańsk Wrzeszcz", 5); // ten zostawimy pusty

        // 2. Tworzymy kilka laptopów
        Laptop l1 = new Laptop("ThinkPad T480", "Lenovo", LaptopCondition.WORKING, 2018, 1200.0, 16, 85, 65);
        Laptop l2 = new Laptop("Latitude 7490", "Dell", LaptopCondition.IN_TESTING, 2019, 1450.0, 16, 90, 65);
        Laptop l3 = new Laptop("MacBook Air M1", "Apple", LaptopCondition.WORKING, 2020, 2900.0, 8, 95, 30);
        Laptop l4 = new Laptop("Pavilion 15", "HP", LaptopCondition.DAMAGED, 2017, 500.0, 8, 40, 45);
        // Duplikat l1 (ten sam model, marka, rok) do przetestowania zabezpieczenia:
        Laptop l1Duplicate = new Laptop("ThinkPad T480", "Lenovo", LaptopCondition.IN_REPAIR, 2018, 900.0, 8, 70, 65);

        // 3. Dodajemy laptopy do oddziału Kraków
        Branch krakow = manager.getBranch("Kraków Centrum");
        krakow.addLaptop(l1);
        krakow.addLaptop(l2);
        krakow.addLaptop(l3);
        krakow.addLaptop(l1Duplicate); // powinno wypisać komunikat, że już istnieje!
        krakow.addLaptop(l4);          // powinno wypisać błąd na System.err (pojemność 3/3)!

        // 4. Testujemy metody oddziału (Branch)
        krakow.summary();

        System.out.println("Liczba sprawnych w Krakowie: " + krakow.countByCondition(LaptopCondition.WORKING));
        krakow.changeCondition(l2, LaptopCondition.WORKING);
        krakow.changeYear(l2, 2020);
        System.out.println("Liczba sprawnych po zmianie stanu l2: " + krakow.countByCondition(LaptopCondition.WORKING));

        System.out.println("\n--- Sortowanie po cenie ---");
        for (Laptop l : krakow.sortByPrice()) {
            System.out.println(l.getModel() + " - " + l.getPrice() + " zł");
        }

        System.out.println("\n--- Sortowanie po nazwie ---");
        for (Laptop l : krakow.sortByName()) {
            System.out.println(l.getModel());
        }

        System.out.println("\n--- Wyszukiwanie dokładne (ThinkPad T480) ---");
        Laptop found = krakow.search("ThinkPad T480");
        if (found != null) found.print();

        System.out.println("\n--- Wyszukiwanie częściowe ('del') ---");
        List<Laptop> partial = krakow.searchPartial("del");
        for (Laptop l : partial) {
            System.out.println("Znaleziono: " + l.getBrand() + " " + l.getModel());
        }

        System.out.println("\n--- Najdroższy laptop w Krakowie ---");
        Laptop maxLaptop = krakow.max();
        if (maxLaptop != null) {
            System.out.println("Najdroższy: " + maxLaptop.getModel() + " (" + maxLaptop.getPrice() + " zł)");
        }

        // 5. Testujemy metody StoreManager (podsumowanie, puste oddziały, transfer, wartość)
        System.out.println();
        manager.summary();

        System.out.println("\nPuste oddziały:");
        for (Branch b : manager.findEmpty()) {
            System.out.println("- " + b.getBranchName());
        }

        System.out.println("\nŁączna wartość laptopów w Krakowie: " + manager.totalValue("Kraków Centrum") + " zł");

        // Transfer laptopa z Krakowa do Warszawy
        manager.transferLaptop(l1, "Kraków Centrum", "Warszawa Mokotów");
        
        // Sprzedaż i usunięcie
        krakow.sellLaptop(l2);
        krakow.removeLaptop(l3);

        System.out.println();
        manager.summary();
        manager.removeBranch("Gdańsk Wrzeszcz");
    }
}