
package MUSD;

/**
 *
 * @author tharaka
 */
public class User {

    private String id;
    private String name;
    private String district;

    public User(String id, String name, String district) {
        this.id = id;
        this.name = name;
        this.district = district;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDistrict() {
        return district;
    }
    
    
}
