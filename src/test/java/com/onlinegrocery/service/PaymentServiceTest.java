package com.onlinegrocery.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.onlinegrocery.dto.PaymentDto;
import com.onlinegrocery.entity.AppUser;
import com.onlinegrocery.entity.Payment;
import com.onlinegrocery.enums.PaymentType;
import com.onlinegrocery.repo.AppUserRepo;
import com.onlinegrocery.repo.PaymentRepo;
import com.onlinegrocery.serviceImpl.PaymentServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PaymentServiceTest {

	@Mock
	private PaymentRepo paymentRepo;

	@Mock
	private AppUserRepo appUserRepo;

	@InjectMocks
	private PaymentServiceImpl paymentService;

	@Test
	public void testPayBill() {

		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setAmount(100.0);
		paymentDto.setType(PaymentType.CARD);
		paymentDto.setDate(LocalDate.now());
		paymentDto.setUserId(2);

		AppUser user = new AppUser();
		user.setUserid(2);
		user.setUserName("testUser");

		when(appUserRepo.findById(2)).thenReturn(Optional.of(user));

		Payment payment = new Payment();
		payment.setAmount(paymentDto.getAmount());
		payment.setType(paymentDto.getType());
		payment.setDate(paymentDto.getDate());
		payment.setUserId(user);

		when(paymentRepo.save(payment)).thenReturn(payment);

		PaymentDto actualResult = paymentService.payBill(paymentDto);

		assertEquals(payment.getPaymentId(), actualResult.getPaymentId());
	}

	@Test
	public void testGetPaymentById() {

		long paymentId = 1L;

		Payment payment = new Payment();
		payment.setPaymentId(paymentId);
		payment.setAmount(100.0);

		when(paymentRepo.findById(paymentId)).thenReturn(Optional.of(payment));

		Payment actualResult = paymentService.getPaymentById(paymentId);

		assertEquals(payment.getPaymentId(), actualResult.getPaymentId());
		assertEquals(payment.getAmount(), actualResult.getAmount());
	}

	@Test
	public void testGetPaymentByIdThrowsException() {

		long paymentId = 2L;

		when(paymentRepo.findById(paymentId)).thenReturn(Optional.empty());

		assertThrows(RuntimeException.class, () -> paymentService.getPaymentById(paymentId));
	}

	@Test
	public void testViewBillById() {

		AppUser user = new AppUser();
		user.setUserid(1);

		Payment payment1 = new Payment();
		payment1.setPaymentId(1);
		payment1.setAmount(100.0);
		payment1.setUserId(user);

		Payment payment2 = new Payment();
		payment2.setPaymentId(2);
		payment2.setAmount(200.0);
		payment2.setUserId(user);

		List<Payment> expectedPayments = new ArrayList<>();
		expectedPayments.add(payment1);
		expectedPayments.add(payment2);

		when(paymentRepo.findByUserId(user)).thenReturn(expectedPayments);

		List<Payment> actualPayments = paymentService.viewBillById(user);

		assertEquals(expectedPayments.size(), actualPayments.size());
		for (int i = 0; i < expectedPayments.size(); i++) {
			assertEquals(expectedPayments.get(i).getPaymentId(), actualPayments.get(i).getPaymentId());
			assertEquals(expectedPayments.get(i).getAmount(), actualPayments.get(i).getAmount());
			assertEquals(expectedPayments.get(i).getUserId(), actualPayments.get(i).getUserId());
		}
	}
}
