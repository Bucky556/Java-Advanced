package project_lombok;

import lombok.Getter;

import java.util.UUID;

public class Main {

    @Getter(lazy = true)
    private final String id = getUniqueId();

    private String getUniqueId() {
        System.out.println("Generating unique id");
        return UUID.randomUUID().toString();
    }


    public static void main(String[] args) {
       /* Main main = new Main();
        System.out.println("Main object created!");
        System.out.println(main.getId());*/

       /* Manager manager = new Manager(1,"Nodir","okilov@gmail.com","Main_manager");
        System.out.println(manager);*/

        /*Card card1 = Card.getInsatnce("Nodir","5614","020927");
        Card card2 = Card.getInsatnce("Nodir", "5614", "020127");
        System.out.println(card1.equals(card2));*/

        /*Card card = Card.getInsatnce("asdasd","112123213","123321");
        System.out.println(card);
        card.setExpiry(null);
        System.out.println(card);*/

        var user = new UserRegisterRequest("Nodir","nodir556");



    }
}