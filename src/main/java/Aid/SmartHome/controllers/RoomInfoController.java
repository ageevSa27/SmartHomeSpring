package Aid.SmartHome.controllers;

import Aid.SmartHome.models.Room;
import Aid.SmartHome.models.RoomInfo;
import Aid.SmartHome.repositorys.RoomInfoRepository;
import Aid.SmartHome.repositorys.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RoomInfoController {
    @Autowired
    private RoomInfoRepository roomInfoRepository;
    @Autowired
    private RoomRepository roomRepository;


    @GetMapping("/roomInfo")
    public String roomInfo(Model model) {
        Iterable<RoomInfo> roomInfos = roomInfoRepository.findAll();
        model.addAttribute("roominfo", roomInfos);
        return "roomInfo";
    }

    @GetMapping("/roomInfo/add")
    public String roomInfoAdd(Model model) {
        Iterable<Room> rooms = roomRepository.findAll();
        model.addAttribute("rooms", rooms);
        Iterable<RoomInfo> roomInfos = roomInfoRepository.findAll();
        model.addAttribute("roominfo", roomInfos);
        return "roomInfoAdd";
    }

    @PostMapping("/roomInfo/add")
    public String addRoomInfoToTable(@RequestParam(required = false) boolean protechka,@RequestParam(required = false) boolean zadimlenie,
                                     @RequestParam int vlajnost, @RequestParam int osveshennost,
                                     @RequestParam int temp, @RequestParam Long roomId, Model model) {


        Room room = roomRepository.findById(roomId).orElseThrow(() -> new NullPointerException("Room not found, id: " + roomId));
        RoomInfo roomInfo = new RoomInfo(protechka, zadimlenie, vlajnost, osveshennost, temp,room);
        room.setRoomInfo(roomInfo);
        roomInfoRepository.save(roomInfo);




//        RoomInfo roomInfo = new RoomInfo(protechka, zadimlenie, vlajnost, osveshennost, temp, room);
//       room.setRoomInfo(roomInfo);
//        roomInfoRepository.save(roomInfo);


        return "redirect:/rooms";
    }

}
