package com.miage.altea.tp.trainer_api.controller;

import com.miage.altea.tp.trainer_api.bo.Trainer;
import com.miage.altea.tp.trainer_api.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainers")
public class TrainerController {


    private final TrainerService trainerService;

    @Autowired
    TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping("/")
    Iterable<Trainer> getAllTrainers() {
        return trainerService.getAllTrainers();
    }

    @GetMapping("/{name}")
    Trainer getTrainer(@PathVariable String name) {
        return trainerService.getTrainer(name);
    }

    @PostMapping("/")
    Trainer createTrainer(@RequestBody Trainer trainer){
        return trainerService.createTrainer(trainer);
    }

    @PutMapping("/")
    Trainer updateTrainer(@RequestBody Trainer trainer){
        return trainerService.updateTrainer(trainer);
    }

    @DeleteMapping("/")
    boolean deleteTrainer(@RequestParam String name){
        return trainerService.deleteTrainer(name);
    }
}