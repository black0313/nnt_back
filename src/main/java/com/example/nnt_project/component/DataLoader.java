package com.example.nnt_project.component;

import com.example.nnt_project.entity.Facility;
import com.example.nnt_project.entity.PickupAddress;
import com.example.nnt_project.entity.Role;
import com.example.nnt_project.enums.Permissions;
import com.example.nnt_project.repository.*;
import com.example.nnt_project.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    @Value("${spring.sql.init.mode}")
    private String initMode;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FacilityRepository facilityRepository;
    private final PickupAddressRepository pickupAddressRepository;
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        if ("always".equals(initMode)) {

            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = User.builder()
                        .firstname("Adminbek")
                        .lastname("Adminbekov")
                        .username("admin")
                        .password(passwordEncoder.encode("123"))
                        .role(roleRepository.save(new Role("Admin", Arrays.stream(Permissions.values()).toList())))
                        .build();
                userRepository.save(admin);
            }

            if (userRepository.findByUsername("manager").isEmpty()) {
                User manager = User.builder()
                        .firstname("Manager")
                        .lastname("Manager")
                        .username("manager")
                        .password(passwordEncoder.encode("123"))
                        .role(roleRepository.save(new Role("Manager", Arrays.stream(Permissions.values()).toList())))
                        .build();
                userRepository.save(manager);
            }

            if (userRepository.findByUsername("dispatcher").isEmpty()) {
                User dispatcher = User.builder()
                        .firstname("Dispatch")
                        .lastname("Dispatchov")
                        .username("dispatcher")
                        .password(passwordEncoder.encode("123"))
                        .role(roleRepository.save(new Role("Dispatcher", Arrays.stream(Permissions.values()).toList())))
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

//            if (brokerRepository.findByName("Amazon").isEmpty()) {
//                Broker amazon = Broker.builder()
//                        .name("Amazon")
//                        .build();
//                brokerRepository.save(amazon);
            }


            List<String> facilityNames = Arrays.asList(
                    "SCK4", "SLC2", "SLC2", "LAS1", "BDU5", "AZA5", "OAK5", "OAK5", "PHX6", "PHX3",
                    // (add all other facility names here)
                    "TUS2", "AZA4", "AZA9", "GYR2", "OAK3", "SCK8", "TCY5", "SMF3", "GYR1", "TUS2",
                    "SCK3", "SCK8", "GYR2", "GYR2", "OAK5", "DSR4", "SCK1", "SCK8", "GYR1", "XCA2",
                    "SJC7", "SCK4", "GYR3", "PHX7", "GYR1", "ONT8", "GYR1", "SAN5", "SMF6", "SCK1",
                    "LGB3", "ONT6", "MDW8", "GYR1", "KRB9", "TUS1", "OAK4", "PSP1", "SMF3", "AZA5",
                    "AZA5", "PHX7", "PHX7", "SAN3", "OAK4", "SAN5", "VGT2", "GYR1", "AZA9", "GYR2",
                    "OAK3", "MCE1", "SMF1", "LGB7", "GYR1", "SAN3", "GYR1", "SCK6", "PHX5", "SCK8",
                    "SCK3", "MCE1", "SCK8", "SCK4", "PHX5", "GYR1", "TUS2", "ONT5", "SAN3", "SNA4",
                    "SCK6", "TUS2", "GYR2", "LGB3", "LGB7", "SMF6", "BFL1", "MCE1", "TUS2", "ONT5",
                    "FAT1", "SNA4", "SBD1", "MCI3", "DPX6", "GYR1", "GYR1", "FAT2", "SDM4", "HOU7",
                    "ONT6", "DFW8", "OAK4", "PHX5", "FTW6", "TUS2", "LAX5", "ONT5", "DFO3", "LAS7",
                    "LGB4", "OAK5", "SMF6", "TUS2", "LGB3", "SAZ1", "SMF1", "FTW6", "LAS1", "SAN3",
                    "BFL1", "OAK5", "HOU7", "MCE1", "GYR2", "TUS5", "SMF1", "VGT1", "SCK4", "SCK1",
                    "ONT1", "SCK4", "FAT2", "LGB8", "OAK5", "GYR1", "KOHL_S_P_95363_741", "TUS5", "TUS5",
                    "LAX9", "TUS2", "GYR2", "LAS1", "GYR2", "PHX3", "KRB9", "SMF1", "KRB9", "SCK4",
                    "MIA1", "PHX7", "JAX2", "HSM1", "SCK4", "ONT8", "DTU2", "TUS2", "LAS1", "XLX7",
                    "SCK4", "SCK4", "IND4", "SCK6", "TUS2", "STL3", "TUS2", "PHX7", "STL3", "MCE1",
                    "ORD5", "TCY9", "OAK5", "TUS2", "SCK1", "LAS1", "TUS1", "GYR1", "KOHL_S_P_95363_741",
                    "PHX7", "KOHL_S_P_95363_741", "KOHL_S_P_95363_741", "LAS2", "LAX9", "XLX7", "LAX5",
                    "SCK1", "SMF5", "SMF7", "AZA5", "PHX3", "XCA2", "SMF9", "MCE1", "TCY9", "OAK5",
                    "DFO9", "MGE5", "LAS2", "1146BKCCG_ITR", "GYR1", "ONT2", "TUS2", "KRB3", "SMF3",
                    "OAK5", "KSCK", "SMF1", "MAZ2", "TUS5", "TUS2", "TCY5", "LGB5", "DFO3", "VGT1",
                    "SCK6", "SMF1", "PHX7", "AZA5", "MAZ2", "LAS7", "SCK4", "MCE1", "XCA2", "VGT1",
                    "LGB3", "DUR3", "LAS6", "SCK6", "SCK6", "LAS8", "AZA9", "AZA4", "LAS2", "LAS7",
                    "OXR1", "SCK1", "MCE1", "LAS1", "GYR2", "SCK6", "TUS1", "OAK5", "TCY5", "HSF9",
                    "SCK1", "HLA9", "SMF5", "OAK5", "HLA9", "LAS7", "TUS2", "KOHL_S_P_95363_741", "LAS1",
                    "GYR1", "SMF5", "VGT1", "DFO3", "SNA4", "SMF5", "DTU6", "MCE1", "SMF6", "ONT1",
                    "LAS1", "SCK1", "AZA4", "DTU7", "ICT2", "MEM2", "DPX6", "PHX5", "HOU7", "XCA2",
                    "LAX5", "TYS1", "DSC3", "ATL7", "DSF8", "MOB5", "SMF6", "DFO3", "DPS1", "VGT2",
                    "AZA5", "AGS5", "XLX7", "AFW5", "SAV4", "SMF1", "FWA6", "DXC5", "SDF4", "HSM1",
                    "TYS1", "HKX1", "FWA4", "TUS2", "GYR3", "MEM1", "HOU8", "LIT1", "MQY1", "SDF4",
                    "MGE9", "SDF8", "IAH1", "GSO1", "MDW2", "MEM1", "CLT6", "HMK9", "DSC3", "IND5",
                    "STL3", "SAT1", "MQJ5", "MGE5", "MCI9", "HLA1", "MKC6", "FTW8", "MCI5", "SAT2",
                    "CMH4", "OAK5", "ATL2", "HGA6", "BTR9", "CAE1", "AZA5", "PHX3", "CMH2", "DLX9",
                    "MEM5", "GYR2", "SCK1", "SAN3", "MAZ2", "DSD1", "DSD1", "DSD1", "ONT6", "SBD1",
                    "FAT1", "SCK1", "SCK6", "SJC7", "TUS1", "SMF5", "SMF5", "PHX6", "SMF6", "SMF6",
                    "ORD9", "ICT2", "HOU5", "MCI3", "ICT2", "New Horizons RV Corporation", "DJX1",
                    "LAX9", "DMC4", "TPA4", "TPA1", "BNA7", "TUS2", "MGE9", "JAN1", "MEM5", "MQY1",
                    "MEM5", "SMF1", "CSG1", "IND2", "CHA2", "HSV1", "DAZ2", "MLI1", "HSV1", "LOWES_34_35020_811",
                    "DFW7", "SMF3", "ORD5", "IND9", "GYR3", "AFW5", "MSP1", "CMH7", "LGB5", "DPX7",
                    "RFD2", "MEM8", "MEM2", "MEM6", "MEM4", "DGT8", "XME1", "FTW1", "SCK6", "MGE9",
                    "RDU9", "RDU1", "MCE1", "TYS1", "CLE3", "MGE8", "LFT1", "MQY1", "MQJ1", "DLB2",
                    "JAN1", "DIA4", "SMF3", "ATL2", "JAN1", "JAN1", "MEM8", "BTR9", "STL3", "SJC7",
                    "HDA8", "DFW7", "HPX3", "JAN1", "CVG2", "SCK1", "SMF6", "DSM4", "MDW9", "HGA6",
                    "AZA5", "JAN1", "VGT1", "BTR9", "MLI1", "SAN5", "FTW2", "DAX7", "RBD5", "BTR9",
                    "STL9", "BTR9", "CLE2", "OAK4", "MQY1", "LGB3", "LEX2", "SMF6", "GYR3", "LFT1",
                    "AZA5", "CHA2", "OMA2", "OAK4", "DSF8", "LFT1", "SAZ2", "LFT1", "SAN5", "SCK1",
                    "ATL2", "SAN3", "DPX7", "AZA5", "GYR2", "MGE5", "XME1", "SCK1", "MCI9", "AZA5",
                    "SCK1", "IAH3", "DFO3", "HOU5", "SCK1", "SCK8", "SLC1", "HOU1", "WPR1", "GYR1",
                    "LIT2", "IND9", "DSM5", "JAN1", "DSF7", "ATL2", "MSP1", "CVG2", "DTU3", "CVG9",
                    "JAN1", "VGT2", "OAK4", "TUL2", "MCI9", "LIT1", "TUL2", "SMF6", "DTU7", "MQY1",
                    "ONT5", "LGB6", "SLC1", "DSF7", "AZA5", "JAN1", "DPX6", "MCI5", "SAV4", "MDW7",
                    "MCE1", "DLT6", "SMF3", "MCI9", "DPX6", "DPX6", "OKC1", "PCA1", "SMF1", "SCK8",
                    "SCA3", "AZA5", "DEN3", "GYR1", "OKC1", "DGE9", "SCA5", "SJC7", "DAT6", "TUS2",
                    "LIT1", "VGT1", "HSF9", "DFA5", "GYR1", "SCK3", "SMF6", "DUT7", "OAK5", "AZA9",
                    "GYR1", "SCK1", "DSJ9", "AZA5"
            );

            for (String facilityName : facilityNames) {
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

