package multivac.app0;

public class Repository {
    private static final Repository ourInstance = new Repository();

    public static Repository getInstance() {
        return ourInstance;
    }

    private String userName;
    private int age;
    private int greeting_type;

    private Repository() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGreeting_type() {
        return greeting_type;
    }

    public void setGreeting_type(int greeting_type) {
        this.greeting_type = greeting_type;
    }
}
