package Auth;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class FakerDataGen {

    @Test
    public void genFakeTestData(){
        Faker faker=new Faker();
        String fullName=faker.name().fullName();
        System.out.println(fullName);

    }

}
