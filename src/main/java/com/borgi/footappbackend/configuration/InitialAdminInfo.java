package com.borgi.footappbackend.configuration;

import com.borgi.footappbackend.entities.shared.Nationality;
import com.borgi.footappbackend.entities.user.UserRefrence;
import com.borgi.footappbackend.entities.user.UserRole;
import com.borgi.footappbackend.entities.user.UserRolePermission;
import com.borgi.footappbackend.repositories.sharedrepository.NationalityRepository;
import com.borgi.footappbackend.repositories.userrepositories.UserRepository;
import com.borgi.footappbackend.repositories.userrepositories.UserRolePermissionRepository;
import com.borgi.footappbackend.repositories.userrepositories.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Component
@Slf4j
public class InitialAdminInfo implements CommandLineRunner {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRolePermissionRepository rolePermissionRepository;
    private final NationalityRepository nationalityRepository;
    @Override
    public void run(String... args) throws Exception {
        UserRole userRole = new UserRole();
        if(userRoleRepository.findById(1).isPresent())
        {
            userRole=userRoleRepository.findById(1).orElse(null);
        }
        else
        {

            userRole.setUserRoleName("Admin");
            userRole=  userRoleRepository.save(userRole);


        UserRolePermission userRolePermission = new UserRolePermission();
        userRolePermission.setUserRolePermissionName("Gestion des utilisateurs");
        userRolePermission.setActiveUserRolePermission(true);
        userRolePermission.setUserRole(userRole);
        userRolePermission= rolePermissionRepository.save(userRolePermission);

        assert userRole != null;
        userRole.setUserRolePermissionList(new ArrayList<UserRolePermission>());
        userRole.getUserRolePermissionList().add(userRolePermission);



        UserRolePermission userRolePermission1 = new UserRolePermission();
        userRolePermission1.setUserRolePermissionName("Gestion des joueurs");
        userRolePermission1.setActiveUserRolePermission(true);
        userRolePermission1.setUserRole(userRole);
        userRolePermission1= rolePermissionRepository.save(userRolePermission1);

        userRole.getUserRolePermissionList().add(userRolePermission1);


        UserRolePermission userRolePermission2 = new UserRolePermission();
        userRolePermission2.setUserRolePermissionName("Gestion des leagues");
        userRolePermission2.setActiveUserRolePermission(true);
        userRolePermission2.setUserRole(userRole);
        userRolePermission2= rolePermissionRepository.save(userRolePermission2);

        userRole.getUserRolePermissionList().add(userRolePermission2);



        }

        if(userRepository.JPQL_GetUserByUserEmail("admin@gmail.com")==null)
        {
            UserRefrence userRefrence=new UserRefrence();
            userRefrence.setUserEmail("admin@gmail.com");
            userRefrence.setUserFirstName("Admin");
            userRefrence.setUserLastName("Admin");
            userRefrence.setUserPassword(passwordEncoder.encode("admin"));
            userRefrence.setUserPhone("+21695418515");
            userRefrence.setActiveAccount(true);
            userRefrence.setUserRole(userRole);
            userRefrence=userRepository.save(userRefrence);
            assert userRole != null;
            userRole.setUserRefrenceList(new ArrayList<>());
            userRole.getUserRefrenceList().add(userRefrence);
            userRoleRepository.save(userRole);

        }

        // Vérifiez si la table des nationalités est vide
        if(nationalityRepository.findAll().size() == 0) {
            // Insertion des nationalités
            nationalityRepository.saveAll(List.of(
                    new Nationality(1, "Afghanistan"),
                    new Nationality(2, "Albanie"),
                    new Nationality(3, "Algérie"),
                    new Nationality(4, "Andorre"),
                    new Nationality(5, "Angola"),
                    new Nationality(6, "Antigua-et-Barbuda"),
                    new Nationality(7, "Argentine"),
                    new Nationality(8, "Arménie"),
                    new Nationality(9, "Australie"),
                    new Nationality(10, "Autriche"),
                    new Nationality(11, "Azerbaïdjan"),
                    new Nationality(12, "Bahamas"),
                    new Nationality(13, "Bahrain"),
                    new Nationality(14, "Bangladesh"),
                    new Nationality(15, "Barbade"),
                    new Nationality(16, "Bélarus"),
                    new Nationality(17, "Belgique"),
                    new Nationality(18, "Belize"),
                    new Nationality(19, "Bénin"),
                    new Nationality(20, "Bhoutan"),
                    new Nationality(21, "Bolivie"),
                    new Nationality(22, "Bosnie-Herzégovine"),
                    new Nationality(23, "Botswana"),
                    new Nationality(24, "Brésil"),
                    new Nationality(25, "Brunei"),
                    new Nationality(26, "Bulgarie"),
                    new Nationality(27, "Burkina Faso"),
                    new Nationality(28, "Burundi"),
                    new Nationality(29, "Cap-Vert"),
                    new Nationality(30, "Cambodge"),
                    new Nationality(31, "Cameroun"),
                    new Nationality(32, "Canada"),
                    new Nationality(33, "République Centrafricaine"),
                    new Nationality(34, "Tchad"),
                    new Nationality(35, "Chili"),
                    new Nationality(36, "Chine"),
                    new Nationality(37, "Colombie"),
                    new Nationality(38, "Comores"),
                    new Nationality(39, "Congo (Congo-Brazzaville)"),
                    new Nationality(40, "Congo (République Démocratique du)"),
                    new Nationality(41, "Costa Rica"),
                    new Nationality(42, "Croatie"),
                    new Nationality(43, "Cuba"),
                    new Nationality(44, "Chypre"),
                    new Nationality(45, "République tchèque"),
                    new Nationality(46, "Danemark"),
                    new Nationality(47, "Djibouti"),
                    new Nationality(48, "Dominique"),
                    new Nationality(49, "République Dominicaine"),
                    new Nationality(50, "Équateur"),
                    new Nationality(51, "Égypte"),
                    new Nationality(52, "El Salvador"),
                    new Nationality(53, "Guinée Équatoriale"),
                    new Nationality(54, "Érythrée"),
                    new Nationality(55, "Estonie"),
                    new Nationality(56, "Eswatini"),
                    new Nationality(57, "Éthiopie"),
                    new Nationality(58, "Fidji"),
                    new Nationality(59, "Finlande"),
                    new Nationality(60, "France"),
                    new Nationality(61, "Gabon"),
                    new Nationality(62, "Gambie"),
                    new Nationality(63, "Géorgie"),
                    new Nationality(64, "Allemagne"),
                    new Nationality(65, "Ghana"),
                    new Nationality(66, "Grèce"),
                    new Nationality(67, "Grenade"),
                    new Nationality(68, "Guatemala"),
                    new Nationality(69, "Guinée"),
                    new Nationality(70, "Guinée-Bissau"),
                    new Nationality(71, "Guyana"),
                    new Nationality(72, "Haïti"),
                    new Nationality(73, "Honduras"),
                    new Nationality(74, "Hongrie"),
                    new Nationality(75, "Islande"),
                    new Nationality(76, "Inde"),
                    new Nationality(77, "Indonésie"),
                    new Nationality(78, "Iran"),
                    new Nationality(79, "Irak"),
                    new Nationality(80, "Irlande"),
                    new Nationality(81, "Italie"),
                    new Nationality(82, "Jamaïque"),
                    new Nationality(83, "Japon"),
                    new Nationality(84, "Jordanie"),
                    new Nationality(85, "Kazakhstan"),
                    new Nationality(86, "Kenya"),
                    new Nationality(87, "Kiribati"),
                    new Nationality(88, "Kuwait"),
                    new Nationality(89, "Kirghizistan"),
                    new Nationality(90, "Laos"),
                    new Nationality(91, "Lettonie"),
                    new Nationality(92, "Liban"),
                    new Nationality(93, "Lesotho"),
                    new Nationality(94, "Liberia"),
                    new Nationality(95, "Libye"),
                    new Nationality(96, "Liechtenstein"),
                    new Nationality(97, "Lituanie"),
                    new Nationality(98, "Luxembourg"),
                    new Nationality(99, "Madagascar"),
                    new Nationality(100, "Malawi"),
                    new Nationality(101, "Malaisie"),
                    new Nationality(102, "Maldives"),
                    new Nationality(103, "Mali"),
                    new Nationality(104, "Malte"),
                    new Nationality(105, "Îles Marshall"),
                    new Nationality(106, "Mauritanie"),
                    new Nationality(107, "Maurice"),
                    new Nationality(108, "Mexique"),
                    new Nationality(109, "Micronésie"),
                    new Nationality(110, "Moldavie"),
                    new Nationality(111, "Monaco"),
                    new Nationality(112, "Mongolie"),
                    new Nationality(113, "Monténégro"),
                    new Nationality(114, "Maroc"),
                    new Nationality(115, "Mozambique"),
                    new Nationality(116, "Namibie"),
                    new Nationality(117, "Nauru"),
                    new Nationality(118, "Népal"),
                    new Nationality(119, "Niger"),
                    new Nationality(120, "Nigeria"),
                    new Nationality(121, "Macédoine du Nord"),
                    new Nationality(122, "Norvège"),
                    new Nationality(123, "Nouvelle-Zélande"),
                    new Nationality(124, "Oman"),
                    new Nationality(125, "Pakistan"),
                    new Nationality(126, "Palaos"),
                    new Nationality(127, "Palestine"),
                    new Nationality(128, "Panama"),
                    new Nationality(129, "Papouasie-Nouvelle-Guinée"),
                    new Nationality(130, "Paraguay"),
                    new Nationality(131, "Pays-Bas"),
                    new Nationality(132, "Philippines"),
                    new Nationality(133, "Pologne"),
                    new Nationality(134, "Portugal"),
                    new Nationality(135, "Qatar"),
                    new Nationality(136, "République de Corée"),
                    new Nationality(137, "République Démocratique du Congo"),
                    new Nationality(138, "République tchèque"),
                    new Nationality(139, "Roumanie"),
                    new Nationality(140, "Royaume-Uni"),
                    new Nationality(141, "Russie"),
                    new Nationality(142, "Rwanda"),
                    new Nationality(143, "Saint-Kitts-et-Nevis"),
                    new Nationality(144, "Saint-Vincent-et-les-Grenadines"),
                    new Nationality(145, "Sao Tomé-et-Principe"),
                    new Nationality(146, "Arabie Saoudite"),
                    new Nationality(147, "Sénégal"),
                    new Nationality(148, "Serbie"),
                    new Nationality(149, "Seychelles"),
                    new Nationality(150, "Sierra Leone"),
                    new Nationality(151, "Singapour"),
                    new Nationality(152, "Slovaquie"),
                    new Nationality(153, "Slovénie"),
                    new Nationality(154, "Somalie"),
                    new Nationality(155, "Afrique du Sud"),
                    new Nationality(156, "Soudan"),
                    new Nationality(157, "Soudan du Sud"),
                    new Nationality(158, "Espagne"),
                    new Nationality(159, "Sri Lanka"),
                    new Nationality(160, "Syrie"),
                    new Nationality(161, "Tadjikistan"),
                    new Nationality(162, "Tanzanie"),
                    new Nationality(163, "Thaïlande"),
                    new Nationality(164, "Togo"),
                    new Nationality(165, "Tonga"),
                    new Nationality(166, "Trinité-et-Tobago"),
                    new Nationality(167, "Tunisie"),
                    new Nationality(168, "Turkménistan"),
                    new Nationality(169, "Turquie"),
                    new Nationality(170, "Tuvalu"),
                    new Nationality(171, "Ukraine"),
                    new Nationality(172, "Émirats Arabes Unis"),
                    new Nationality(173, "Royaume-Uni"),
                    new Nationality(174, "États-Unis"),
                    new Nationality(175, "Uruguay"),
                    new Nationality(176, "Uzbekistan"),
                    new Nationality(177, "Vanuatu"),
                    new Nationality(178, "Vatican"),
                    new Nationality(179, "Venezuela"),
                    new Nationality(180, "Viêt Nam"),
                    new Nationality(181, "Yémen"),
                    new Nationality(182, "Zambie"),
                    new Nationality(183, "Zimbabwe")
            ));
        }

    }
}
