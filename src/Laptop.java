public class Laptop implements Comparable<Laptop> {
    
    private String model, brand;
    private LaptopCondition condition;
    private int year = 0;
    private double price = 0.0;
    private int ramGB = 0;
    private int batteryHealth = 0;
    private int power = 0;

    public Laptop(String a, String h, LaptopCondition b, int c, double d, int e, int f, int g) {
        this.model = a;
        this.brand = h;
        this.condition = b;
        this.year = c;
        this.price = d;
        this.ramGB = e;
        this.batteryHealth = f;
        this.power = g;

    }

    public int getPower() {
        return power;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public LaptopCondition getCondition() {
        return condition;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public int getRamGB() {
        return ramGB;
    }

    public int getBatteryHealth() {
        return batteryHealth;
    }



    public void setModel(String model) {
        this.model = model;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setCondition(LaptopCondition condition) {
        this.condition = condition;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRamGB(int ramGB) {
        this.ramGB = ramGB;
    }

    public void setBatteryHealth(int batteryHealth) {
        this.batteryHealth = batteryHealth;
    }

    public void setPower(int power) {
        this.power = power;
    }


    public void print(){

        System.out.print("Ten laptop to: ");
        System.out.println(brand);
        System.out.print("model - ");
        System.out.println(model);

        System.out.print("data produkcji - ");
        System.out.println(year);


        System.out.println("Jego parametry to: ");
        System.out.print("Ram - ");
        System.out.println(ramGB);
        System.out.print("zdrowie batrii - ");
        System.out.println(batteryHealth);
        System.out.print("moc zasilacza - ");
        System.out.println(power);

        System.out.print("Jego stan - ");
        System.out.println(condition);

        System.out.print("Cena - ");
        System.out.println(price);
    }


    @Override
    public int compareTo(Laptop other) {

        int result = this.model.compareTo(other.model);
        if (result != 0) {
            return result;
        }

        result = this.brand.compareTo(other.brand);
        if (result != 0) {
            return result;
        }

        return Integer.compare(this.year, other.year);
    }


}
