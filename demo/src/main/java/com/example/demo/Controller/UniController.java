package com.example.demo.Controller;



import com.example.demo.Models.Uni;
import com.example.demo.accessingdatamysql.UniRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1")
public class UniController {

    @Autowired
    private UniRepository uniRepository;


    /**
     * function for add new student
     * @param uni input data
     * @param error for error
     * @return result
     */
    @PostMapping("/uni")
    public String addNewUni(@RequestBody @Valid Uni uni, Errors error) {
        if (uniRepository.findByUniName(uni.getUniName()) != null){
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \"The uni With This name Exist\"\n" +
                    "}";
        }
        if (error.hasErrors()) {
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \""+error.getFieldError().getField()+":"+error.getAllErrors().get(0).getDefaultMessage()+"\"\n" +
                    "}";
        }
        uniRepository.save(new Uni(uni.getUniName()));

        return "{\n" +
                "    \"status\": \"success\"\n" +
                "    \"message\":\"success Create New uni\"\n" +

                "}";
    }


    /**
     * function for update uni
     * @param id id uni
     * @param uni input data
     * @param error for error
     * @return result
     */
    @PutMapping("/uni/{id}")
    public String updateUni(@PathVariable("id") Long id,@RequestBody @Valid Uni uni,  Errors error) {
        if (uniRepository.findByUniName(uni.getUniName())!=null)
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \"The uni with this name exist\"\n" +
                    "}";
        uni.setId(id);
        if (error.hasErrors()) {
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \""+error.getFieldError().getField()+":"+error.getAllErrors().get(0).getDefaultMessage()+"\"\n" +
                    "}";
        }

        uniRepository.save(uni);

        return "{\n" +
                "    \"status\": \"success\"\n" +
                "    \"message\": \"success Update uni\"\n" +

                "}";
    }

    /**
     * function for delete uni
     * @param json input data
     * @param error for error
     * @return result
     */
    @DeleteMapping("/uni")
    public String deleteUni(@Valid @RequestBody String json,  Errors error) {
        JsonObject data = new Gson().fromJson(json, JsonObject.class);
        String uniName = data.get("uniName").getAsString();
        Uni uni = uniRepository.findByUniName(uniName);
        if (uni == null) {
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \"Not Find uni With this name\"\n" +

                    "}";
        }
        if (error.hasErrors()) {
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \""+error.getFieldError().getField()+":"+error.getAllErrors().get(0).getDefaultMessage()+"\"\n" +
                    "}";
        }
        uniRepository.deleteById(uni.getId());

        return "{\n" +
                "    \"status\": \"success\"\n" +
                "    \"message\": \"success delete uni\"\n" +

                "}";
    }


    /**
     * function for get all uni
     * @return all uni
     */
    @GetMapping(path = "/uni")
    public Iterable<Uni> getAllUni() {
        return uniRepository.findAll();
    }

    /**
     * function for get a uni
     * @param id of uni
     * @return a uni
     */
    @GetMapping(path = "/uni/{id}")
    public Optional<Uni> getUni(@PathVariable("id") Long id) {
        return uniRepository.findById(id);
    }

}
