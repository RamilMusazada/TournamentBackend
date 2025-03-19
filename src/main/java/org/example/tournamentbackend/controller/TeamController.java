package org.example.tournamentbackend.controller;
import org.example.tournamentbackend.dto.TeamDTO;
import org.example.tournamentbackend.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    private final TeamService teamService;

    public TeamController() {
        this.teamService = new TeamService();
    }

    // 🔹 1. Yeni komanda əlavə et (POST)
    @PostMapping("/add")
    public String addTeam(@RequestBody TeamDTO teamDTO) {
        teamService.addTeam(teamDTO);
        return "Team added successfully!";
    }

    // 🔹 2. Bütün komandaları qaytar (GET)
    @GetMapping("/all")
    public List<TeamDTO> getAllTeams() {
        return teamService.getAllTeams();
    }

    // 🔹 3. Oyunun nəticəsini əlavə et (PUT)
    @PutMapping("/update-score")
    public String updateScore(@RequestParam String teamName,
                              @RequestParam int scoredGoals,
                              @RequestParam int concededGoals) {
        teamService.addMatchResult(teamName, scoredGoals, concededGoals);
        return "Score updated successfully!";
    }

    // 🔹 4. Qələbəyə görə sıralanmış komandalar (GET)
    @GetMapping("/sorted-by-wins")
    public List<TeamDTO> getTeamsByWins() {
        return teamService.getTeamsSortedByWins();
    }

    // 🔹 5. Xal sayına görə sıralanmış komandalar (GET)
    @GetMapping("/sorted-by-points")
    public List<TeamDTO> getTeamsByPoints() {
        return teamService.getTeamsSortedByPoints();
    }
}
