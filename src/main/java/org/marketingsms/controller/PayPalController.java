package org.marketingsms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import org.marketingsms.paypal.*;

@RestController
@RequestMapping(value = "/paypal")
public class PayPalController {

    private final PayPalClient payPalClient;
    @Autowired
    PayPalController(PayPalClient payPalClient){
        this.payPalClient = payPalClient;
    }

    @CrossOrigin(origins = "http://localhost:8090")
   /* @PostMapping*/
    @RequestMapping(value = "/make/payment")

    public Map<String, Object> makePayment(@RequestParam("sum") String sum){
        return payPalClient.createPayment("90");
    }

    @CrossOrigin(origins = "http://localhost:8090")
    @PostMapping(value = "/complete/payment")
    public Map<String, Object> completePayment(HttpServletRequest request, @RequestParam("paymentId") String paymentId, @RequestParam("payerId") String payerId){
        return payPalClient.completePayment(request);
    }


}
