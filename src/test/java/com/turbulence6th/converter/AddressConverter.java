package com.turbulence6th.converter;

import com.turbulence6th.AbstractConverter;
import com.turbulence6th.Creator;
import com.turbulence6th.MapCaller;
import com.turbulence6th.dto.AddressDto;
import com.turbulence6th.entity.Address;

public class AddressConverter extends AbstractConverter<Address, AddressDto> {

    @Override
    protected Creator<Address> createEntity() {
        return Address::new;
    }

    @Override
    protected Creator<AddressDto> createDto() {
        return AddressDto::new;
    }

    @Override
    protected MapCaller<Address, AddressDto> fillEntity() {
        return mapper -> {
            mapper.map(Address::setCity, AddressDto::getCity);
            mapper.map(Address::setStreet, AddressDto::getStreet);
        };
    }

    @Override
    protected MapCaller<AddressDto, Address> fillDto() {
        return mapper -> {
            mapper.map(AddressDto::setCity, Address::getCity);
            mapper.map(AddressDto::setStreet, Address::getStreet);
        };
    }
}
