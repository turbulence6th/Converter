Sample Converter:

```java
package com.turbulence6th.converter;

import com.turbulence6th.AbstractConverter;
import com.turbulence6th.Creator;
import com.turbulence6th.MapCaller;
import com.turbulence6th.dto.UserDto;
import com.turbulence6th.entity.User;

public class UserConverter extends AbstractConverter<User, UserDto> {

    private AddressConverter addressConverter;

    public UserConverter(AddressConverter addressConverter) {
        this.addressConverter = addressConverter;
    }

    @Override
    protected Creator<User> createEntity() {
        return User::new;
    }

    @Override
    protected Creator<UserDto> createDto() {
        return UserDto::new;
    }

    @Override
    protected MapCaller<User, UserDto> fillEntity() {
        return mapper -> {
            mapper.map(User::setUsername, UserDto::getUsername);
            mapper.map(User::setCdate, UserDto::getCdate);
            mapper.map(User::setAddress, UserDto::getAddress, addressConverter::convertToEntity);
        };
    }

    @Override
    protected MapCaller<UserDto, User> fillDto() {
        return mapper -> {
            mapper.map(UserDto::setUsername, User::getUsername);
            mapper.map(UserDto::setCdate, User::getCdate);
            mapper.map(UserDto::setAddress, User::getAddress, addressConverter::convertToDto);
        };
    }
}
```

Sample Test:
```java
package com.turbulence6th.test;

import com.turbulence6th.converter.AddressConverter;
import com.turbulence6th.converter.UserConverter;
import com.turbulence6th.dto.AddressDto;
import com.turbulence6th.dto.UserDto;
import com.turbulence6th.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserConverterTest {

    private AddressConverter addressConverter;
    private UserConverter userConverter;

    @Before
    public void setup() {
        addressConverter = new AddressConverter();
        userConverter = new UserConverter(addressConverter);
    }

    @Test
    public void testConvertToEntity() {
        UserDto userDto = createuserDto();
        User user = userConverter.convertToEntity(userDto);

        assertThat("turbulence6th", is(user.getUsername()));
        assertThat(LocalDate.of(2019, Month.AUGUST, 1), is(user.getCdate()));
        assertThat("Paris", is(user.getAddress().getCity()));
        assertThat("Rue de Rivoli", is(user.getAddress().getStreet()));
    }

    private AddressDto createAddressDto() {
        AddressDto addressDto = new AddressDto();
        addressDto.setCity("Paris");
        addressDto.setStreet("Rue de Rivoli");
        return addressDto;
    }

    private UserDto createuserDto() {
        UserDto userDto = new UserDto();
        userDto.setUsername("turbulence6th");
        userDto.setCdate(LocalDate.of(2019, Month.AUGUST, 1));
        userDto.setAddress(createAddressDto());
        return userDto;
    }
}

```
