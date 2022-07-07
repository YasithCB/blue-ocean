package view.tm;

public class PackageTM {
    private String functions;
    private double price;
    private String packageCode;

    public PackageTM() {
    }

    public PackageTM(String functions, double price, String packageCode) {
        this.functions = functions;
        this.price = price;
        this.packageCode = packageCode;
    }

    public String getFunctions() {
        return functions;
    }

    public void setFunctions(String functions) {
        this.functions = functions;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }
}
