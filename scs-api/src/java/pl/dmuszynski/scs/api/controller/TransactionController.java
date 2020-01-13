package pl.dmuszynski.scs.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.dmuszynski.scs.api.model.*;
import pl.dmuszynski.scs.api.service.ItemService;
import pl.dmuszynski.scs.api.service.PremiumEquipmentService;
import pl.dmuszynski.scs.api.service.TransactionService;
import pl.dmuszynski.scs.api.service.UserService;

import java.time.LocalDateTime;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionController {

    private final UserService userService;
    private final ItemService itemService;
    private final TransactionService transactionService;
    private final PremiumEquipmentService premiumEquipmentService;

    @Autowired
    public TransactionController(final UserService userService,
                                 final ItemService itemService,
                                 final TransactionService transactionService,
                                 final PremiumEquipmentService premiumEquipmentService) {

        this.userService = userService;
        this.itemService = itemService;
        this.transactionService = transactionService;
        this.premiumEquipmentService = premiumEquipmentService;
    }

    @PostMapping(value = "/transaction")
    public void realizeTransaction(@RequestBody Transaction transaction) {
        User existingUser = userService.findUserByNick(transaction.getUser().getNick());
        int newPremiumCurrency = existingUser.getPremiumCurrency() - transaction.getProduct().getPrize();

        User updatedUser = changeUserPremiumCurrency(existingUser, newPremiumCurrency);
        Transaction newTransaction = createNewTransaction(updatedUser, transaction.getProduct());
        Item existingItem = this.itemService.findItemByName(transaction.getProduct().getName());
        PremiumEquipment premiumEquipment = createPremiumEquipment(updatedUser, existingItem);

        this.userService.updateUser(updatedUser);
        this.transactionService.realizeTransaction(newTransaction);
        this.premiumEquipmentService.save(premiumEquipment);
    }

    private User changeUserPremiumCurrency(User user, int premiumCurrency){
        return new User.UserBuilder()
            .id(user.getId())
            .nick(user.getNick())
            .email(user.getEmail())
            .password(user.getPassword())
            .roles(user.getRoles())
            .premiumCurrency(premiumCurrency)
            .activationCode(user.getActivationCode())
            .active(user.isActive())
            .logged(user.isLogged())
            .created(user.getCreated())
            .build();
    }

    private Transaction createNewTransaction(User user, Product product) {
        Transaction newTransaction = new Transaction();
        newTransaction.setUser(user);
        newTransaction.setProduct(product);
        newTransaction.setDate(LocalDateTime.now());

        return newTransaction;
    }

    private PremiumEquipment createPremiumEquipment(User user, Item item) {
        PremiumEquipment premiumEquipment = new PremiumEquipment();
        premiumEquipment.setUser(user);
        premiumEquipment.setItem(item);

        return premiumEquipment;
    }
}
