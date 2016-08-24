import com.sk.dao.PersonDAO;
import com.sk.dao.UserDAO;
import com.sk.model.Person;
import com.sk.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

/**
 * Created by shisong on 16/7/16.
 */
public class RepostroyTest {
    private ApplicationContext ctx;


    @Before
    public void setUp() throws Exception {
        ctx = new ClassPathXmlApplicationContext("mvc-config-test.xml");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
        }
    }

    @Test
    public void testPersonDao() {
        PersonDAO personDAO = ctx.getBean(PersonDAO.class);
        Person person = new Person();
        person.setName("Pankaj1");
        person.setCountry("India2");
        personDAO.save(person);
        System.out.println("Person::" + person);
        List<Person> list = personDAO.list();
        for (Person p : list) {
            System.out.println("Person List::" + p);
        }

    }

    @Test
    public void testUserDao() {
        UserDAO userDAO = ctx.getBean(UserDAO.class);
        User user = new User();
        user.setNickName("开熏");
        user.setPasswd("fefe");
        user.setAvatar("http://fsefesfsefes");
        user.setSex("男");
        user.setBirthday(new Date(System.currentTimeMillis()));
        userDAO.save(user);
        System.out.println("User::" + user);
        List<User> list = userDAO.list();
        for (User p : list) {
            System.out.println("User List::" + p);
        }

    }

}
