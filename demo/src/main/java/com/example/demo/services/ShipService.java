package com.example.demo.services;

import com.example.demo.models.dtos.CreateShipDto;
import com.example.demo.models.dtos.ShipDto;
import com.example.demo.models.entities.Category;
import com.example.demo.models.entities.Ship;
import com.example.demo.models.entities.User;
import com.example.demo.models.enums.ShipType;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ShipRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShipService {
    private final ShipRepository shipRepository;
    private final CategoryRepository categoryRepository;
    private final LoggedUser loggedUser;
    private final UserRepository userRepository;

    public ShipService(ShipRepository shipRepository,
                       CategoryRepository categoryRepository,
                       LoggedUser loggedUser, UserRepository userRepository) {
        this.shipRepository = shipRepository;
        this.categoryRepository = categoryRepository;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
    }

    public boolean create(CreateShipDto createShipDto){

        Optional<Ship> byName =
                this.shipRepository.findByName(createShipDto.getName());
        if (byName.isPresent()){
            return false;
        }
//        ShipType type = switch (createShipDto.getCategory()) {
//            case 0 -> ShipType.BATTLE;
//            case 1 -> ShipType.CARGO;
//            case 2 -> ShipType.PATROL;
//            default -> ShipType.BATTLE;
//        };
        ShipType type = null;
        if (createShipDto.getCategory() == 1){
            type = ShipType.BATTLE;
        } else if(createShipDto.getCategory() == 2){
            type = ShipType.CARGO;
        } else if(createShipDto.getCategory() == 3){
            type = ShipType.PATROL;
        }

        Category category = this.categoryRepository.findByName(type);
        Optional<User> owner = this.userRepository.findById(this.loggedUser.getId());

        Ship ship = new Ship();
        ship.setName(createShipDto.getName());
        ship.setPower(createShipDto.getPower());
        ship.setHealth(createShipDto.getHealth());
        ship.setCreated(createShipDto.getCreated());
        ship.setCategory(category);
        ship.setUser(owner.get());

        this.shipRepository.save(ship);

        return true;
    }


    public List<ShipDto> getShipsOwnedById(long ownerId) {
        return this.shipRepository.findByUserId(ownerId)
                .stream()
                .map(ShipDto::new)
                .collect(Collectors.toList());
    }
    public List<ShipDto> getShipsNotOwnedById(long ownerId) {
        return this.shipRepository.findByUserIdNot(ownerId)
                .stream()
                .map(ShipDto::new)
                .collect(Collectors.toList());
    }

    public List<ShipDto> getAllShipsSorted() {
        return this.shipRepository.findByOrderByNameAscHealthAscPowerAsc()
                .stream()
                .map(ShipDto::new)
                .collect(Collectors.toList());
    }
}
