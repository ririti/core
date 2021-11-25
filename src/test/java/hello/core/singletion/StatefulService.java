package hello.core.singletion;

public class StatefulService {

    private  int price; //상태를 유지하는 필드  10000이였던것이 20000으로 바뀜

    public void order(String name, int price){
        System.out.println("name = " + name + "price = " + price);
        this.price = price; //여기서 문제가생김
    }

    public  int getPrice(){
        return  price;
    }
}
