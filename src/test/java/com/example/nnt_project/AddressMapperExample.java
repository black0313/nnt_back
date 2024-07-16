package com.example.nnt_project;

import com.example.nnt_project.entity.Address;
import com.example.nnt_project.mapper.AddressMapper;
import com.example.nnt_project.payload.AddressDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AddressMapperExample {

    @Autowired
    private AddressMapper addressMapper;

    @Configuration
    static class TestConfig {
        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }

        @Bean
        public AddressMapper addressMapper() {
            return new AddressMapper();
        }
    }

    @Test
    public void testAddressToDto() {
        Address address = new Address("123 Main St", "Springfield", "IL", "62704", "USA");
        AddressDto addressDto = addressMapper.addressDto(address);

        assertThat(addressDto).isNotNull();
        assertThat(addressDto.getStreet()).isEqualTo(address.getStreet());
        assertThat(addressDto.getCity()).isEqualTo(address.getCity());
        assertThat(addressDto.getState()).isEqualTo(address.getState());
        assertThat(addressDto.getPostalCode()).isEqualTo(address.getPostalCode());
        assertThat(addressDto.getCountry()).isEqualTo(address.getCountry());
    }

    @Test
    public void testDtoToAddress() {
        AddressDto addressDto = new AddressDto("456 Elm St", "Shelbyville", "IL", "62705", "USA");
        Address address = addressMapper.toAddress(addressDto);

        assertThat(address).isNotNull();
        assertThat(address.getStreet()).isEqualTo(addressDto.getStreet());
        assertThat(address.getCity()).isEqualTo(addressDto.getCity());
        assertThat(address.getState()).isEqualTo(addressDto.getState());
        assertThat(address.getPostalCode()).isEqualTo(addressDto.getPostalCode());
        assertThat(address.getCountry()).isEqualTo(addressDto.getCountry());
    }

    @Test
    public void testToAddressDtoList() {
        List<Address> addresses = Arrays.asList(
                new Address("123 Main St", "Springfield", "IL", "62704", "USA"),
                new Address("456 Elm St", "Shelbyville", "IL", "62705", "USA")
        );

        List<AddressDto> addressDtos = addressMapper.toAddressDtoList(addresses);

        assertThat(addressDtos).isNotNull();
        assertThat(addressDtos).hasSize(2);

        assertThat(addressDtos.get(0).getStreet()).isEqualTo(addresses.get(0).getStreet());
        assertThat(addressDtos.get(0).getCity()).isEqualTo(addresses.get(0).getCity());
        assertThat(addressDtos.get(0).getState()).isEqualTo(addresses.get(0).getState());
        assertThat(addressDtos.get(0).getPostalCode()).isEqualTo(addresses.get(0).getPostalCode());
        assertThat(addressDtos.get(0).getCountry()).isEqualTo(addresses.get(0).getCountry());

        assertThat(addressDtos.get(1).getStreet()).isEqualTo(addresses.get(1).getStreet());
        assertThat(addressDtos.get(1).getCity()).isEqualTo(addresses.get(1).getCity());
        assertThat(addressDtos.get(1).getState()).isEqualTo(addresses.get(1).getState());
        assertThat(addressDtos.get(1).getPostalCode()).isEqualTo(addresses.get(1).getPostalCode());
        assertThat(addressDtos.get(1).getCountry()).isEqualTo(addresses.get(1).getCountry());
    }
}

