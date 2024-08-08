package com.example.nnt_project.mapper;

import com.example.nnt_project.entity.Address;
import com.example.nnt_project.payload.AddressDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressMapper {
    @Autowired
    private ModelMapper modelMapper;

    public AddressDto addressDto(Address address) {
        return modelMapper.map(address, AddressDto.class);
    }

    public Address toAddress(AddressDto addressDto) {
        return modelMapper.map(addressDto, Address.class);
    }

    public List<AddressDto> toAddressDtoList(List<Address> addressList) {
        return addressList.stream()
                .map(this::addressDto)
                .collect(Collectors.toList());
    }

    public void update(AddressDto addressDto, Address address) {
        modelMapper.map(addressDto, address);
    }
}
