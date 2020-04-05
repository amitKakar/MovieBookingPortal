package com.example.springexample.seat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springexample.global.Globals;

@Service
public class SeatDAO {

	@Autowired
	SeatRepository seatRepository;

	public void populateMapFromDB() {

		if (seatRepository.tableSize() > 0) {
			List<Seat> seatList = getAllSeats();

			for (Seat seat : seatList) {
				ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
				map.put(seat.getShowId(), seat.getSeatNumber());

				Globals.seatMap.put(map, seat.getSeatId());
			}

			for (ConcurrentHashMap<Integer, Integer> name : Globals.seatMap.keySet())
				System.out.println("key: " + name);
		}
	}

	public void addSeat(Seat seat, int numberOfSeats) {
		if (Globals.seatMap.isEmpty()) {
			populateMapFromDB();
		}

		int currentseatId = Seat.getCurrentSeatId();

		if (currentseatId == 0 && seatRepository.tableSize() > 0) {
			currentseatId = seatRepository.currentSeatId();
		}

		Seat.setCurrentSeatId(currentseatId);

		Seat seatEntity = new Seat();

		for (int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++) {
			seatEntity.setScreenId(seat.getScreenId());
			seatEntity.setSeatClass(seat.getSeatClass());
			seatEntity.setStatus(seat.getStatus());
			seatEntity.setSeatId(Seat.getCurrentSeatId() + 1);
			seatEntity.setSeatNumber(seatNumber);
			seatEntity.setShowId(seat.getShowId());
			seatRepository.save(seatEntity);

			ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
			map.put(seatEntity.getShowId(), seatEntity.getSeatNumber());
			Globals.seatMap.put(map, seatEntity.getSeatId());

			currentseatId++;
			Seat.setCurrentSeatId(currentseatId);
		}
	}

	public List<Seat> getAllSeats() {
		List<Seat> seatList = new ArrayList<>();
		Iterable<Seat> iterableElements = seatRepository.findAll();
		Iterator<Seat> it = iterableElements.iterator();
		while (it.hasNext()) {
			seatList.add(it.next());
		}

		return seatList;
	}

	public Seat getSeatById(int seatId) {
		Optional<Seat> seatContainer = seatRepository.findById(seatId);
		return seatContainer.get();
	}

	// TODO add functionality to retrieve seat by
	/*public Seat getSeat(int screenId, int seatNumber) {
		if (Globals.seatMap.isEmpty()) {
			populateMapFromDB();
		}

		HashMap<Integer, Integer> map = new HashMap();
		map.put(screenId, seatNumber);

		Integer seatId = Globals.seatMap.get(map);

		if (null != seatId) {
			return getSeatById(seatId);
		}

		return null;
	}*/

	@Transactional
	public void update(int showId, int seatNumber, Seat updatedSeat) {
		if (Globals.seatMap.isEmpty()) {
			populateMapFromDB();
		}

		ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>();
		map.put(showId, seatNumber);

		Integer seatId = Globals.theatreMap.get(map);

		if (null != seatId) {
			seatRepository.update(seatId, updatedSeat.getStatus(), updatedSeat.getSeatClass());
		}
	}

	public void deleteSeatById(int seatId) {
		seatRepository.deleteById(seatId);
	}

	public void deleteSeatBySeatNumber(int showId, int seatNumber) {
		if (Globals.seatMap.isEmpty()) {
			populateMapFromDB();
		}

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(showId, seatNumber);

		Integer seatId = Globals.theatreMap.get(map);

		if (null != seatId) {
			Globals.seatMap.remove(map);
			seatRepository.deleteById(seatId);
		}
	}

	public ArrayList<Seat> getSeat(int screenId,int showId) {

		return seatRepository.getSeatsByScreenId(screenId,showId);
	}

	@Transactional
	public ArrayList<String> updateSeatStatusToBooked(ArrayList<Integer> bookedSeatIdList) {
		int success = 0;
		String seatIdListString="";
		String seatNumberListString="";
		ArrayList <String> seatIdAndNumberList = new ArrayList <String> ();
		int seatNumber;
		for (int i = 0; i < bookedSeatIdList.size(); i++) {
			success = seatRepository.updateSeatStatusToBooked(bookedSeatIdList.get(i));

			if (success == 0) {
				// unable to update
				return null;
			} else {

				if (i == bookedSeatIdList.size()- 1)

				{
					seatIdListString = seatIdListString + bookedSeatIdList.get(i);

					seatNumber = getSeatNumber(bookedSeatIdList.get(i));
					
					seatNumberListString = seatNumberListString + seatNumber;

				} else {
					seatIdListString = seatIdListString + bookedSeatIdList.get(i) + ",";

					seatNumber =getSeatNumber(bookedSeatIdList.get(i));
					seatNumberListString = seatNumberListString + seatNumber + ",";

				}
			}
		}
		seatIdAndNumberList.add(seatIdListString);
		seatIdAndNumberList.add(seatNumberListString);
		return seatIdAndNumberList;
		/*String status = seatRepository.getSeatStatus(seatId);
		if ("A".equals(status)) {
			return seatRepository.updateSeatStatusToBooked(seatId);
		} else {
			return 0;
		}*/
	}

	public int getSeatNumber(int seatId) {
		return seatRepository.getSeatNumber(seatId);
	}
	
	
	@Transactional
	public void changeSeatStatusToAvailable(int seatId) {
		
		seatRepository.changeSeatStatusToAvailable(seatId);
	}
}
