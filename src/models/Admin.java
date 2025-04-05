package models;

public class Admin extends User {

    public Admin(int id, String name, String mail) {
        super(id, name, mail);
    }

    public void manageStock(Warehouse warehouse) {
        System.out.println(this.getName() + " is managing warehouse " + warehouse.getId());
    }
}
