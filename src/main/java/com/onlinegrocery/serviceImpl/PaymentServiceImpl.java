package com.onlinegrocery.serviceImpl;


 
import java.util.Date;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.onlinegrocery.dto.DeliveryDto;
import com.onlinegrocery.dto.PaymentDto;
import com.onlinegrocery.entity.AppUser;
import com.onlinegrocery.entity.Payment;
import com.onlinegrocery.repo.AppUserRepo;
import com.onlinegrocery.repo.PaymentRepo;
import com.onlinegrocery.service.PaymentService;
 
import javax.transaction.Transactional;
import javax.validation.Valid;
 
 
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentRepo paymentRepo;
    @Autowired
    AppUserRepo appUserRepo;
    
    @Override
    public PaymentDto payBill(PaymentDto paymentDTO) {
       
        Payment payment = new Payment();
        payment.setAmount(paymentDTO.getAmount());
        payment.setType(paymentDTO.getType());
        AppUser user = appUserRepo.findById(paymentDTO.getUserId()).get();
        payment.setDate(paymentDTO.getDate());

	    payment.setUserId(user);
        paymentRepo.save(payment);
       
      
        paymentDTO.setPaymentId(payment.getPaymentId());
        paymentDTO.setAmount(payment.getAmount());
        return paymentDTO;
    }
 
	@Override
	public Payment getPaymentById(long paymentId) {
		return paymentRepo.findById(paymentId).orElseThrow();
	}

	@Override
	public List<Payment> viewBillById(AppUser userId) {
		List<Payment> payment = paymentRepo.findByUserId(userId);
		return payment;
	}
 

}
 

