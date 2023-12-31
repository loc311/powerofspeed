package vn.aptech.powerofspeed;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import vn.aptech.powerofspeed.util.RandomStringUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import vn.aptech.powerofspeed.model.user.Address;
import vn.aptech.powerofspeed.model.user.Role;
import vn.aptech.powerofspeed.model.user.User;
import vn.aptech.powerofspeed.repository.category.CategoryRepository;
import vn.aptech.powerofspeed.repository.images.ImagesRepository;
import vn.aptech.powerofspeed.repository.product.ProductRepository;
import vn.aptech.powerofspeed.repository.subcategory.SubcategoryRepository;
import vn.aptech.powerofspeed.repository.user.AddressRepository;
import vn.aptech.powerofspeed.repository.user.RoleRepository;
import vn.aptech.powerofspeed.repository.user.UserRepository;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PowerOfSpeedApplication {

    public static void main(String[] args) {
        SpringApplication.run(PowerOfSpeedApplication.class, args);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner init(UserRepository userRepository,
                           RoleRepository roleRepository,
                           AddressRepository addressRepository,
                           CategoryRepository categoryRepository,
                           SubcategoryRepository subcategoryRepository,
                           ProductRepository productRepository,
                           ImagesRepository imagesRepository) {


        return args -> {

            Role roleAdmin = roleRepository.findByName("ADMIN");
            if (roleAdmin == null) {
                roleAdmin = new Role();
                roleAdmin.setName("ADMIN");
                roleRepository.save(roleAdmin);
            }

            Role roleStaff = roleRepository.findByName("STAFF");
            if (roleStaff == null) {
                roleStaff = new Role();
                roleStaff.setName("STAFF");
                roleRepository.save(roleStaff);
            }

            Role roleCustomer = roleRepository.findByName("CUSTOMER");
            if (roleCustomer == null) {
                roleCustomer = new Role();
                roleCustomer.setName("CUSTOMER");
                roleRepository.save(roleCustomer);
            }

            User admin = userRepository.findByEmail("admin@powerofspeed.com");
            if (admin == null) {
                admin = new User();
                admin.setEmail("admin@powerofspeed.com");
                admin.setEmailConfirmed(true);
                admin.setPassword(passwordEncoder.encode("123456"));
                admin.setPhoneNumber("0123456789");
                admin.setPhoneConfirmed(true);
                admin.setFirstName("Nguyen Van");
                admin.setLastName("A");
                admin.setGender(User.GenderType.Male);
                admin.setDateOfBirth(Date.valueOf("1999-07-26"));
                admin.setProfilePicture("avatar.png");
                admin.setStatus(true);

                Address adminAddress = new Address();
                adminAddress.setCountry("Vietnam");
                adminAddress.setAddress("Block A EHomse S Nam Sai Gon, Binh Hung");
                adminAddress.setCity("Ho Chi Minh");
                adminAddress.setStateOrRegion("Binh Chanh");
                adminAddress.setPostalCode("80000");
                admin.setAddress(addressRepository.save(adminAddress));

                Set<Role> adminRoles = new HashSet<>();
                adminRoles.add(roleAdmin);
                adminRoles.add(roleStaff);
                admin.setRoles(adminRoles);

                userRepository.save(admin);

            }

            User staff = userRepository.findByEmail("staff@powerofspeed.com");
            if (staff == null) {
                staff = new User();
                staff.setEmail("staff@powerofspeed.com");
                staff.setEmailConfirmed(true);
                staff.setPassword(passwordEncoder.encode("123456"));
                staff.setPhoneNumber("0123456789");
                staff.setPhoneConfirmed(true);
                staff.setFirstName("Nguyen Van");
                staff.setLastName("B");
                staff.setGender(User.GenderType.Male);
                staff.setDateOfBirth(Date.valueOf("1999-07-26"));
                staff.setProfilePicture("avatar2.png");
                staff.setStatus(true);

                Address staffAddress = new Address();
                staffAddress.setCountry("Vietnam");
                staffAddress.setAddress("343 Pham Ngu Lao");
                staffAddress.setCity("Ho Chi Minh");
                staffAddress.setStateOrRegion("Quan 1");
                staffAddress.setPostalCode("80000");
                staff.setAddress(addressRepository.save(staffAddress));

                Set<Role> staffRoles = new HashSet<>();
                staffRoles.add(roleStaff);
                staff.setRoles(staffRoles);

                userRepository.save(staff);

            }

            User customer = userRepository.findByEmail("customer@powerofspeed.com");
            if (customer == null) {
                customer = new User();
                customer.setEmail("customer@powerofspeed.com");
                customer.setEmailConfirmed(true);
                customer.setPassword(passwordEncoder.encode("123456"));
                customer.setPhoneNumber("0123456789");
                customer.setPhoneConfirmed(true);
                customer.setFirstName("Nguyen Van");
                customer.setLastName("C");
                customer.setGender(User.GenderType.Male);
                customer.setDateOfBirth(Date.valueOf("1999-07-26"));
                customer.setProfilePicture("avatar3.png");
                customer.setStatus(true);

                Address customerAddress = new Address();
                customerAddress.setCountry("Vietnam");
                customerAddress.setAddress("343 Pham Ngu Lao");
                customerAddress.setCity("Ho Chi Minh");
                customerAddress.setStateOrRegion("Quan 1");
                customerAddress.setPostalCode("80000");
                customer.setAddress(addressRepository.save(customerAddress));

                Set<Role> customerRoles = new HashSet<>();
                customerRoles.add(roleCustomer);
                customer.setRoles(customerRoles);

                userRepository.save(customer);

            }
        };
    }

}
