package Aid.SmartHome.controllers;

import Aid.SmartHome.models.Room;
import Aid.SmartHome.models.Sensor;
import Aid.SmartHome.repositorys.RoomRepository;
import Aid.SmartHome.repositorys.SensorRepository;
import Aid.SmartHome.services.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RoomController {

    private final SensorRepository sensorRepository;

    private final RoomService roomService;
    private final RoomRepository roomRepository;

    public RoomController(SensorRepository sensorRepository, RoomService roomService, RoomRepository roomRepository) {
        this.sensorRepository = sensorRepository;
        this.roomService = roomService;
        this.roomRepository = roomRepository;
    }

    @GetMapping("/rooms")
    public String room(Model model) {
        roomService.getAll();

        model.addAttribute("rooms", roomService.getAll());
        return "room";
    }


    @GetMapping("/rooms/add")
    public String roomAdd(Model model) {
        return "roomAdd";
    }

    @PostMapping("/rooms/add")
    public String addRoomToTable(@RequestParam String name, Model model) {
        Room room = new Room(name);
        roomRepository.save(room);
        return "redirect:/rooms";
    }


    @GetMapping(value = "/rooms/delete/{id}")
    public String roomDelete(@PathVariable Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new NullPointerException("Room not found, id: " + id));
        for (Sensor sensor:
        room.getSensors()) {
            sensor.setRoom(null);
            sensor.setFree(true);
        }
        roomRepository.deleteById(id);

        return "redirect:/rooms";
    }


    @GetMapping(value = "/rooms/{id}")
    public String RoomInfo(@PathVariable long id, Model model) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new NullPointerException("Room not found, id: " + id));
        List<Sensor> sensors = room.getSensors();
        List<String> data = new ArrayList<>();


        for (Sensor abc :
                sensors) {
            switch (abc.getType()) {
                case ("температура"):
                    data.add("Температура " + room.getRoomInfo().getTemp());
                    break;

                case ("влажность"):
                    data.add("влажность " + room.getRoomInfo().getVlajnost());
                    break;
                case ("освещение"):
                    data.add("освещение " + room.getRoomInfo().getOsveshennost());
                    break;
                case ("задымленность"):
                    data.add("задымленность " + room.getRoomInfo().isZadimlenie());
                    break;
                case ("протечка"):
                    data.add("протечка " + room.getRoomInfo().isProtechka());
                    break;
                default:
                    String s = "Датчиков нет";
                    break;
            }

        }
        System.out.println(data.get(0));
        model.addAttribute("room", room);
        model.addAttribute("data", data);

        return "roomData";

    }


}
