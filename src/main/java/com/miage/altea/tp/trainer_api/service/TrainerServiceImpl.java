package com.miage.altea.tp.trainer_api.service;

import com.miage.altea.tp.trainer_api.bo.Trainer;
import com.miage.altea.tp.trainer_api.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerServiceImpl implements  TrainerService {

    private TrainerRepository repository;

    @Autowired
    public TrainerServiceImpl(TrainerRepository repository){
        this.repository = repository;
    }
    @Override
    public Iterable<Trainer> getAllTrainers() {
        return repository.findAll();
    }

    @Override
    public Trainer getTrainer(String name) {
        return (repository.findById(name).isPresent() ?repository.findById(name).get()  : null);
    }

    @Override
    public Trainer createTrainer(Trainer trainer) {
        return repository.save(trainer);
    }

    @Override
    public Trainer updateTrainer(Trainer trainer) {
        return repository.save(trainer);
    }

    @Override
    public boolean deleteTrainer(String name) {

        repository.deleteById(name);
        return repository.findById(name).isPresent();
    }
}
