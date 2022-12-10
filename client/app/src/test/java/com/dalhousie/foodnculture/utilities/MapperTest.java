package com.dalhousie.foodnculture.utilities;

import static org.junit.Assert.assertEquals;

import com.dalhousie.foodnculture.models.User;

import org.junit.Test;

public class MapperTest {

    private User createUser()
    {
        User user = new User();
        user.setId(1);
        user.setEmail("test@dal.ca");
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setUserName("test.dal");
        user.setPassword("DQJGDHGEWHDJWHEDWBVEJD");
        user.setVerified(false);
        return user;
    }

    @Test
    public void mapToJsonTest() throws Exception {
        String jsonData = "{\"id\":1,\"userName\":\"test.dal\",\"email\":\"test@dal.ca\",\"password\":\"DQJGDHGEWHDJWHEDWBVEJD\",\"firstName\":\"Test\",\"lastName\":\"Test\",\"status\":null,\"updatedAt\":null,\"createdAt\":null,\"verified\":false}";
        assertEquals(Mapper.mapToJson(createUser()), jsonData);
    }

    @Test
    public void mapFromJsonTest() throws Exception
    {
        String jsonData = "{\"id\":1,\"userName\":\"test.dal\",\"email\":\"test@dal.ca\",\"password\":\"DQJGDHGEWHDJWHEDWBVEJD\",\"firstName\":\"Test\",\"lastName\":\"Test\",\"status\":null,\"updatedAt\":null,\"createdAt\":null,\"verified\":false}";
        assertEquals(Mapper.mapFromJson(jsonData, User.class).getId(), createUser().getId());
    }
}
