package org.example.tournamentbackend.service;
import org.example.tournamentbackend.dao.TeamDAO;
import org.example.tournamentbackend.dto.TeamDTO;
import org.example.tournamentbackend.entity.Team;
import org.example.tournamentbackend.mapper.TeamMapper;

import java.util.List;

public class TeamService {
    private final TeamDAO teamDAO;
    private TeamDAO teamDao;

    public TeamService() {
        this.teamDAO = new TeamDAO();
    }

    // 🔹 1. Komanda əlavə et
    public void addTeam(TeamDTO teamDTO) {
        Team team = TeamMapper.toEntity(teamDTO);
        teamDAO.addTeam(team);
    }

    // 🔹 2. Bütün komandaları gətir (DTO qaytarırıq)
    public List<TeamDTO> getAllTeams() {
        return teamDAO.getAllTeams();
    }

    // 🔹 3. Skor əlavə et (Güncəllənmiş qalib və xalları hesablayırıq)
    public void addMatchResult(String teamName, int scoredGoals, int concededGoals) {
        Team team = teamDAO.getTeamByName(teamName);
        if (team != null) {
            team.setPlayed(team.getPlayed() + 1);
            team.setGoalsScored(team.getGoalsScored() + scoredGoals);
            team.setGoalsConceded(team.getGoalsConceded() + concededGoals);
            team.setGoalDifference(team.getGoalsScored() - team.getGoalsConceded());

            if (scoredGoals > concededGoals) {
                team.setWins(team.getWins() + 1);
                team.setPoints(team.getPoints() + 3);
            } else if (scoredGoals == concededGoals) {
                team.setDraws(team.getDraws() + 1);
                team.setPoints(team.getPoints() + 1);
            } else {
                team.setLosses(team.getLosses() + 1);
            }

            teamDAO.updateTeam(team);
        }
    }

    // 🔹 4. Qələbə sayına görə komandaları qaytar
    public List<TeamDTO> getTeamsSortedByWins() {
        return teamDAO.getAllTeams()
                .stream()
                .sorted((t1, t2) -> Integer.compare(t2.getWins(), t1.getWins()))
                .toList();
    }

    // 🔹 5. Xal sayına görə komandaları qaytar
    public List<TeamDTO> getTeamsSortedByPoints() {
        return teamDAO.getAllTeams()
                .stream()
                .sorted((t1, t2) -> Integer.compare(t2.getPoints(), t1.getPoints()))
                .toList();
    }
}
