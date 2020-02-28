package pl.dmuszynski.scs.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.dmuszynski.scs.api.model.Item;
import pl.dmuszynski.scs.api.model.PremiumEquipment;
import pl.dmuszynski.scs.api.model.User;
import pl.dmuszynski.scs.api.service.ItemService;
import pl.dmuszynski.scs.api.service.PremiumEquipmentService;
import pl.dmuszynski.scs.api.service.UserService;

import java.util.List;

@RestController
public class PremiumEquipmentController {

    private final UserService userService;
    private final ItemService itemService;
    private final PremiumEquipmentService premiumEquipmentService;

    public PremiumEquipmentController(final PremiumEquipmentService premiumEquipmentService, final UserService userService,
                                      final ItemService itemService)
    {
        this.userService = userService;
        this.itemService = itemService;
        this.premiumEquipmentService = premiumEquipmentService;
    }

    @GetMapping(value = "/user/{userId}/premiumEquipment")
    public List<PremiumEquipment> findAllByUserId(@PathVariable(value = "userId") Long id) {
        System.out.println("JEST" + id);
        return this.premiumEquipmentService.findAllByUserId(id);
    }
//
//    @PostMapping(value = "/user/{userId}/premiumEquipment/item/{itemId}/create")
//    @CrossOrigin
//    public void createPremiumEquipmentItem(@PathVariable(value = "userId") Long userId, @PathVariable(value = "itemId") Long itemId) {
//        try {
//            User user = userService.findUserById(userId);
//            Item item = itemService.findItemById(itemId);
//            PremiumEquipment premiumEquipmentItem = new PremiumEquipment(user, item);
//
//            this.premiumEquipmentService.save(premiumEquipmentItem);
//        } catch (pl.dmuszynski.scs.sb.app.exception.ResourceNotFoundException exception) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
//        }
//    }
//
//    @DeleteMapping(value = "/premiumEquipment/delete/{id}")
//    public void deletePremiumEquipment(@PathVariable(value = "id") Long id) {
//        this.premiumEquipmentService.deletePremiumEquipmentById(id);
//    }
}
