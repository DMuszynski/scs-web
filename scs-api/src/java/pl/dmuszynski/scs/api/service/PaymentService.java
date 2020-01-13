package pl.dmuszynski.scs.api.service;

import pl.dmuszynski.scs.api.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface PaymentService {
    Map<String, Object> createPayment(String sum);
    Map<String, Object> completePayment(HttpServletRequest req, User user);
}
