package com.example.nnt_project.component;

import com.example.nnt_project.entity.Broker;
import com.example.nnt_project.entity.Facility;
import com.example.nnt_project.entity.PickupAddress;
import com.example.nnt_project.repository.BrokerRepository;
import com.example.nnt_project.repository.FacilityRepository;
import com.example.nnt_project.repository.PickupAddressRepository;
import com.example.nnt_project.repository.UserRepository;
import com.example.nnt_project.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.example.nnt_project.role.Role.*;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    @Value("${spring.sql.init.mode}")
    private String initMode;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BrokerRepository brokerRepository;
    private final FacilityRepository facilityRepository;
    private final PickupAddressRepository pickupAddressRepository;

    @Override
    public void run(String... args) {
        if ("always".equals(initMode)) {

            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = User.builder()
                        .firstname("Adminbek")
                        .lastname("Adminbekov")
                        .username("admin")
                        .password(passwordEncoder.encode("123"))
                        .role(ADMIN)
                        .build();
                userRepository.save(admin);
            }

            if (userRepository.findByUsername("manager").isEmpty()) {
                User manager = User.builder()
                        .firstname("Manager")
                        .lastname("Manager")
                        .username("manager")
                        .password(passwordEncoder.encode("123"))
                        .role(MANAGER)
                        .build();
                userRepository.save(manager);
            }

            if (userRepository.findByUsername("dispatcher").isEmpty()) {
                User dispatcher = User.builder()
                        .firstname("Dispatch")
                        .lastname("Dispatchov")
                        .username("dispatcher")
                        .password(passwordEncoder.encode("123"))
                        .role(DISPATCHER)
                        .build();
                userRepository.save(dispatcher);
            }

            // Uncomment and use if the accountant role is needed in the future
            // if (userRepository.findByUsername("accountant").isEmpty()) {
            //     User accountant = User.builder()
            //             .firstname("Account")
            //             .lastname("Accountov")
            //             .username("accountant")
            //             .password(passwordEncoder.encode("123"))
            //             .role(ACCOUNTANT)
            //             .build();
            //     userRepository.save(accountant);
            // }

            if (brokerRepository.findByName("Amazon").isEmpty()) {
                Broker amazon = Broker.builder()
                        .name("Amazon")
                        .build();
                brokerRepository.save(amazon);
            }

            // Add initial facilities
            String[] facilities = {"SCK4", "SLC2", "LAS1", "BDU5", "AZA5", "OAK5", "PHX6", "PHX3", "TUS2", "SNA4"};

            for (String facilityName : facilities) {
                if (facilityRepository.findByName(facilityName).isEmpty()) {
                    Facility facility = Facility.builder()
                            .name(facilityName)
                            .build();
                    facilityRepository.save(facility);
                }
            }


            String[] pickupAddresses = {
                    "6001 S AUSTIN RD Stockton, California 95215",
                    "6395 N. Sarival Avenue Glendale, Arizona 85340",
                    "7148 W Old Bingham Hwy West Jordan, UT 84081",
                    "12300 Bermuda Road Henderson, Nevada 89044",
                    "8350 Quintero St Commerce City, Colorado 80022",
                    "6000 West Van Buren Street Phoenix, Arizona 85043",
                    "38811 Cherry St Newark, CALIFORNIA 94560",
                    "4750 W MOHAVE ST Phoenix, AZ 85043",
                    "6835 W Buckeye Rd Phoenix, AZ 85043",
                    "6701 S KOLB RD Tucson, Arizona 85756",
                    "2496 w walnut ave Rialto, CA 92376",
                    "2751 Skypark Dr Torrance, California 90505"
            };

            for (String address : pickupAddresses) {
                if (pickupAddressRepository.findByAddress(address).isEmpty()) {
                    PickupAddress pickupAddress = PickupAddress.builder()
                            .address(address)
                            .build();
                    pickupAddressRepository.save(pickupAddress);
                }
            }
        }
    }
}
