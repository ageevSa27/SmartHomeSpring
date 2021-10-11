package Aid.SmartHome.services;

import Aid.SmartHome.models.Room;
import Aid.SmartHome.repositorys.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Iterable<Room> getAll(){
        return roomRepository.findAll();
    }

}
