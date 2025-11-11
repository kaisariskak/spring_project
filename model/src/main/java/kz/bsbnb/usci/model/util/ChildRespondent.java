package kz.bsbnb.usci.model.util;

public class ChildRespondent {
    public String bin;
    public String name;

    public ChildRespondent() {
        super();
    }

    public ChildRespondent(String bin, String name) {
        this.bin = bin;
        this.name = name;
    }
    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
