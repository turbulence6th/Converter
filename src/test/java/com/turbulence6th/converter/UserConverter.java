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
            mapper.map(User::setAddresses, UserDto::getAddresses, addressConverter::convertToEntityList);
        };
    }

    @Override
    protected MapCaller<UserDto, User> fillDto() {
        return mapper -> {
            mapper.map(UserDto::setUsername, User::getUsername);
            mapper.map(UserDto::setCdate, User::getCdate);
            mapper.map(UserDto::setAddresses, User::getAddresses, addressConverter::convertToDtoList);
        };
    }
}
