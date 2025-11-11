package kz.bsbnb.usci.model.util;

public class ParentRespondent {
    public String bin;
    public String name;

    public ParentRespondent() {
        super();
    }

    public ParentRespondent(String bin, String name) {
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
