package com.backend.lakesidehotel.service;

import java.util.List;

import com.backend.lakesidehotel.model.BookedRoom;

public interface BookingService {

	List<BookedRoom> getAllBookingsByRoomId(Long roomId);

	List<BookedRoom> getAllBookings();

	BookedRoom findByBookingConfirmationCode(String confirmationCode);

	String saveBooking(Long roomId, BookedRoom bookingRequest);

	void cancelBooking(Long bookingId);

	List<BookedRoom> getBookingsByUserEmail(String email);

}
