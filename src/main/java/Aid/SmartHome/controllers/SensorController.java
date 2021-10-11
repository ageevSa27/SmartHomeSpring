package Aid.SmartHome.controllers;

import Aid.SmartHome.models.Room;
import Aid.SmartHome.models.Sensor;
import Aid.SmartHome.repositorys.RoomRepository;
import Aid.SmartHome.repositorys.SensorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class SensorController {

    private final SensorRepository sensorRepository;
    private final RoomRepository roomRepository;

    public SensorController(SensorRepository sensorRepository, RoomRepository roomRepository) {
        this.sensorRepository = sensorRepository;
        this.roomRepository = roomRepository;
    }

    @GetMapping("/sensors")
    public String sensor(Model model) {
        Iterable<Sensor> sensors = sensorRepository.findAll();
        model.addAttribute("sensors", sensors);
        return "sensor";
    }

    @GetMapping("/sensors/add")
    public String sensorAdd(Model model) {
        return "sensorAdd";
    }

    @PostMapping("/sensors/add")
    public String addRoomToTable(@RequestParam String name,@RequestParam String type ,Model model) {


        Sensor sensor = new Sensor(name,type);
        sensorRepository.save(sensor);
        return "redirect:/sensors";
    }

    @GetMapping(value = "/sensors/delete/{id}")
    public String sensorDelete(@PathVariable Long id) {
        sensorRepository.deleteById(id);
        return "redirect:/sensors";
    }


    @GetMapping(value = "/sensors/addSensorsToRoom/{id}")
    public String addToRoom(@PathVariable Long id, Model model) {
        Sensor sensors = sensorRepository.findById(id).orElseThrow(() -> new NullPointerException("Sensor not found, id: " + id));
        Iterable<Room> rooms = roomRepository.findAll();
        model.addAttribute("sensor", sensors);
        model.addAttribute("room", rooms);
        return "addSensorsToRoom";
    }

    @PostMapping("/sensors/addSensorsToRoom/{id}")
    public String addSensToRoom(@PathVariable Long id, @RequestParam Long roomId) {
        Sensor sensor = sensorRepository.findById(id).orElseThrow(() -> new NullPointerException("Sensor not found, id: " + id));
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new NullPointerException("Room not found, id: " + id));
        if (sensor.isFree()) {sensor.setRoom(room);sensor.setFree(false);sensorRepository.save(sensor);}
        return "redirect:/sensors";
    }



}
