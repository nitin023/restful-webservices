package com.spring.webservices.restfulwebservices.Service;

import com.spring.webservices.restfulwebservices.DTO.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {

    private  static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    static {

        users.add(new User(1,"Nitin",new Date()));
        users.add(new User(2,"Monika",new Date()));
        users.add(new User(3,"Akash",new Date()));
        users.add(new User(4,"Amit",new Date()));
    }

    public List<User>findAll()
    {
        return users;
    }

    public User save(User user)
    {
        if(user.getId()==null)
        {
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id)
    {
        User foundUser = null;
        for(User user : users)
        {
            if(user.getId()==id)
            {
                foundUser = user;
                break;
            }
        }
        return foundUser;
    }

    public User deleteById(int id)
    {
        User deletedUser = null;
        Iterator<User> userIterator = users.iterator();

        while (userIterator.hasNext())
        {
            User user = userIterator.next();

            if(user.getId() == id)
            {
                userIterator.remove();
                deletedUser = user;
                break;
            }
        }
        return deletedUser;
    }
}
