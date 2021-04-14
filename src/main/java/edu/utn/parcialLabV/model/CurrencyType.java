package edu.utn.parcialLabV.model;

public enum CurrencyType {
    EUROS(1,"EURO",110),
    DOLARES(2,"DOLARES",140),
    PESOS(3,"PESOS",1);

    private Integer id;
    private String description;
    private Integer toPesosConverter;

    CurrencyType(Integer id,String description,Integer toPesosConverter){
        this.id = id;
        this.description = description;
        this.toPesosConverter = toPesosConverter;
    }

    public static CurrencyType find(String description){
        for(CurrencyType n : values()){
            if(description.equalsIgnoreCase(n.toString())){
                return n;
            }
        }
        throw new IllegalArgumentException(String.format("Invalid type of currency: %s",description));
    }

    public String getDescription(){
        return this.description;
    }

    public Integer getToPesosConverter() {
        return this.toPesosConverter;
    }
}
