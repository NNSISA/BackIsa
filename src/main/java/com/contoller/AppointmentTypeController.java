package com.contoller;

import com.dto.AppointmentTypeDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.model.AppointmentType;
import com.service.AppointmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AppointmentTypeController {

    @Autowired
    private AppointmentTypeService ats;

    //Provera da li postoji vec takav definisan tipo pregleda
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/add-appType", method= RequestMethod.POST)
    public void addApp(@RequestBody AppointmentTypeDTO appointmentType){
        List<AppointmentType> appointmentTypes= ats.findAll();
        boolean postojiVec=false;
        for(int i=0;i<appointmentTypes.size();i++){
            if(appointmentTypes.get(i).getName().equals(appointmentType.getName())) {
                postojiVec=true;
            }
        }
        if(postojiVec==false) {
            AppointmentType appointmentType1=new AppointmentType();
            appointmentType1.setName(appointmentType.getName());
            ats.save(appointmentType1);
        }
    }

    //Pronalazenje svih tipova pregleda
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/getAppointmentTypes", method= RequestMethod.GET)
    public List<AppointmentType> getTypes(){
        return ats.findAll();
    }

    //Brisanje tipova pregleda
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/delete-type", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody
    ResponseEntity<AppointmentType> deleteType(@RequestBody AppointmentTypeDTO type){
        AppointmentType hr=ats.findByName(type.getName());
        ats.delete(hr);
        AppointmentType hRoom = new AppointmentType(type.getName());
        return new ResponseEntity<>(hRoom, HttpStatus.OK);
    }

    //Izmene informacija
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/changeTypeInfo/{name}", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<AppointmentType> changeInfo(@RequestBody AppointmentTypeDTO type, @PathVariable String name){

        AppointmentType type1 = ats.findByName(name);
        if(type1 != null){
            type1.setName(type.getName());
            ats.save(type1);
        }
        else{
            AppointmentType type2=new AppointmentType();
            type2.setName(type.getName());
            ats.save(type2);
        }
        AppointmentType type3 = new AppointmentType();
        return new ResponseEntity<>(type3, HttpStatus.OK);
    }
}
