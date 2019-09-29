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
import java.util.Collections;

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
        assertThat("Paris", is(user.getAddresses().get(0).getCity()));
        assertThat("Rue de Rivoli", is(user.getAddresses().get(0).getStreet()));
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
        userDto.setAddresses(Collections.singletonList(createAddressDto()));
        return userDto;
    }
}
