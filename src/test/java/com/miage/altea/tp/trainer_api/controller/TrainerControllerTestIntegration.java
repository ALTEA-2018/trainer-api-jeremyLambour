package com.miage.altea.tp.trainer_api.controller;

import com.miage.altea.tp.trainer_api.bo.Pokemon;
import com.miage.altea.tp.trainer_api.bo.Trainer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TrainerControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TrainerController controller;

    @Test
    void trainerController_shouldBeInstanciated(){
        assertNotNull(controller);
    }

    @Test
    void getTrainer_withNameAsh_shouldReturnAsh() {
        var ash = this.restTemplate.getForObject("http://localhost:" + port + "/trainers/Ash", Trainer.class);
        assertNotNull(ash);
        assertEquals("Ash", ash.getName());
        assertEquals(1, ash.getTeam().size());

        assertEquals(25, ash.getTeam().get(0).getPokemonType());
        assertEquals(18, ash.getTeam().get(0).getLevel());
    }

    @Test
    void getAllTrainers_shouldReturnAshAndMisty() {
        var trainers = this.restTemplate.getForObject("http://localhost:" + port + "/trainers/", Trainer[].class);
        assertNotNull(trainers);
        assertEquals(3, trainers.length);

        assertEquals("Ash", trainers[0].getName());
        assertEquals("Misty", trainers[1].getName());
    }

    @Test
    void createTrainer_shouldReturnNewTrainer() {
        var t = new Trainer();
        t.setName("TopDresseur");
        var trainer = this.restTemplate.postForEntity("http://localhost:" + port + "/trainers/",t,Trainer.class);
        assertNotNull(trainer);
        assertEquals("TopDresseur", trainer.getBody().getName());
    }

    @Test
    void updateTrainer_shouldReturnUpdatedTrainer() {
        var t = new Trainer();
        t.setName("TopDresseur");
        List<Pokemon> pokemonList = new ArrayList<>();
        Pokemon p = new Pokemon();
        p.setLevel(22);
        p.setPokemonType(56);
        pokemonList.add(p);
        t.setTeam(pokemonList);
        this.restTemplate.put("http://localhost:" + port + "/trainers/",t);
        var dresseur = this.restTemplate.getForObject("http://localhost:" + port + "/trainers/TopDresseur", Trainer.class);
        assertNotNull(dresseur);
        assertEquals(1, dresseur.getTeam().size());
    }


    @Test
    void deleteTrainer_shouldReturnUpdatedTrainer() {
        this.restTemplate.delete("http://localhost:" + port + "/trainers/"+"TopDresseur");
        var dresseur = this.restTemplate.getForObject("http://localhost:" + port + "/trainers/TopDresseur", Trainer.class);
        assertNull(dresseur);
    }
}