package org.example.tournamentbackend.mapper;

import org.example.tournamentbackend.dto.TeamDTO;
import org.example.tournamentbackend.entity.Team;

import java.util.List;
import java.util.stream.Collectors;

public class TeamMapper {

    // 🔹 Team → TeamDTO
    public static TeamDTO toDTO(Team team) {
        return new TeamDTO(
                team.getName(),
                team.getPlayed(),
                team.getWins(),
                team.getDraws(),
                team.getLosses(),
                team.getGoalDifference(),
                team.getGoalsScored(),
                team.getGoalsConceded(),
                team.getPoints()
        );
    }

    // 🔹 List<Team> → List<TeamDTO>
    public static List<TeamDTO> toDTOList(List<Team> teams) {
        return teams.stream().map(TeamMapper::toDTO).collect(Collectors.toList());
    }

    // 🔹 TeamDTO → Team (əgər lazımdırsa)
    public static Team toEntity(TeamDTO dto) {
        return new Team(
                0, // ID auto-generated olacaq
                dto.getName(),
                dto.getPlayed(),
                dto.getWins(),
                dto.getDraws(),
                dto.getLosses(),
                dto.getGoalDifference(),
                dto.getGoalsScored(),
                dto.getGoalsConceded(),
                "", // `last5Games` boş buraxılır, çünki DTO-da yoxdur
                dto.getPoints()
        );
    }
}
