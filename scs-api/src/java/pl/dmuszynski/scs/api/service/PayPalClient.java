package pl.dmuszynski.scs.api.service;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmuszynski.scs.api.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PayPalClient implements PaymentService{

    // password
    private final String CLIENT_ID = "AXVqwb4YG3jXElgN4G182y67sE9BYoOsvHhMFAL6SpKfx7LOLAZzFbgzzMDpzSS8kcAqlHxblxs6OUmE";
    private final String CLIENT_SECRET = "EJZAlZs_bwTDek9dBj_w6odFmJTkaFSsMaPcdDuoJ-Yk1TUQYwxU2uGFL5_lFw_6IbQoWFtAQzjTCj6E";
    private final UserService userService;

    @Autowired
    public PayPalClient(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public Map<String, Object> createPayment(String sum){
        Map<String, Object> response = new HashMap<>();
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(sum);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:4200/cancel");
        redirectUrls.setReturnUrl("http://localhost:4200/user");
        payment.setRedirectUrls(redirectUrls);

        Payment createdPayment;
        try {
            String redirectUrl = "";
            APIContext context = new APIContext(CLIENT_ID, CLIENT_SECRET, "sandbox");
            createdPayment = payment.create(context);
            if(createdPayment!=null){
                List<Links> links = createdPayment.getLinks();
                for (Links link:links) {
                    if(link.getRel().equals("approval_url")){
                        redirectUrl = link.getHref();
                        break;
                    }
                }
                response.put("status", "success");
                response.put("redirect_url", redirectUrl);
            }
        } catch (PayPalRESTException e) {
            System.out.println("Error happened during transaction creation!");
        }
        return response;
    }

    public Map<String, Object> completePayment(HttpServletRequest req, User user){
        Map<String, Object> response = new HashMap<>();
        Payment payment = new Payment();
        payment.setId(req.getParameter("paymentId"));
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(req.getParameter("PayerID"));
        APIContext context = new APIContext(CLIENT_ID, CLIENT_SECRET, "sandbox");
        try {
            Payment createdPayment = payment.execute(context, paymentExecution);
            if(createdPayment != null){
                User existingUser = userService.findUserByNick(user.getNick());
                int transactionTotalAmount = (int) Float.parseFloat(createdPayment.getTransactions().get(0).getAmount().getTotal());
                int newPremiumCurrency = existingUser.getPremiumCurrency() + transactionTotalAmount;
                userService.updateUser(changeUserPremiumCurrency(existingUser, newPremiumCurrency));
                response.put("status", "success");
            }
        } catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
        }
        return response;
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
}
