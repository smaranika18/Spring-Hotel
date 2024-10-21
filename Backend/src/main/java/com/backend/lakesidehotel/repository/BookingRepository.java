package com.backend.lakesidehotel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.lakesidehotel.model.BookedRoom;

public interface BookingRepository extends JpaRepository<BookedRoom, Long> {

	List<BookedRoom> findByRoomId(Long roomId);

	Optional<BookedRoom> findByBookingConfirmationCode(String confirmationCode);

	List<BookedRoom> findByGuestEmail(String email);

}
